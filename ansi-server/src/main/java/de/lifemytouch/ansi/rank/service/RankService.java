package de.lifemytouch.ansi.rank.service;

import de.lifemytouch.ansi.rank.model.Rank;
import de.lifemytouch.ansi.rank.model.RankData;
import de.lifemytouch.ansi.rank.repository.RankRepository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class RankService {

    public static final long POLL_OVERLAP_MILLIS = 2_000L;

    private final RankRepository rankRepository;

    private final Map<UUID, RankData> cache = new ConcurrentHashMap<>();
    private final List<Consumer<UUID>> changeListeners = new CopyOnWriteArrayList<>();

    private volatile long lastSeenMillis;
    private volatile boolean pollFailing;
    private volatile boolean expireFailing;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
        this.lastSeenMillis = rankRepository.databaseTimeMillis();
    }

    public void addChangeListener(Consumer<UUID> listener) {
        changeListeners.add(listener);
    }

    public void loadPlayer(UUID uuid, String name) {
        rankRepository.ensurePlayer(uuid, name);
        cache.put(uuid, rankRepository.find(uuid).orElse(RankData.defaults(uuid, name)));
    }

    public void unloadPlayer(UUID uuid) {
        cache.remove(uuid);
    }

    public Rank getRank(UUID uuid) {
        RankData rankData = cache.get(uuid);
        return rankData == null ? Rank.DEFAULT : rankData.effectiveRank(System.currentTimeMillis());
    }

    public Optional<RankData> find(UUID uuid) {
        return rankRepository.find(uuid);
    }

    public Optional<RankData> findByName(String name) {
        return rankRepository.findByName(name);
    }

    public void setRank(UUID uuid, Rank rank) {
        rankRepository.setRank(uuid, rank);
        refresh(uuid);
    }

    public boolean setTemporaryRank(UUID uuid, Rank rank, Duration duration) {
        boolean updated = rankRepository.setTemporaryRank(uuid, rank, System.currentTimeMillis() + duration.toMillis());
        if (updated) refresh(uuid);
        return updated;
    }

    public void refresh(UUID uuid) {
        RankData oldRankData = cache.get(uuid);
        if (oldRankData == null) return;

        RankData newRankData = rankRepository.find(uuid).orElse(null);
        if (newRankData == null) return;

        if (cache.replace(uuid, oldRankData, newRankData) && !newRankData.equals(oldRankData)) notifyChange(uuid);
    }

    public void notifyChange(UUID uuid) {

    }
}
