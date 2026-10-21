package de.lifemytouch.ansi.rank.model;

import java.util.UUID;

public record RankData(UUID uuid, String name, Rank rank, Rank previousRank, long expiresAt) {

    public static RankData defaults(UUID uuid, String name) {
        return new RankData(uuid, name, Rank.DEFAULT, null, 0L);
    }

    public boolean isTemporary() {
        return expiresAt > 0;
    }

    public boolean isExpired(long now) {
        return expiresAt > 0 && now >= expiresAt;
    }

    public Rank effectiveRank(long now) {
        if (isExpired(now)) return  previousRank != null ? previousRank : Rank.DEFAULT;

        return rank;
    }

}
