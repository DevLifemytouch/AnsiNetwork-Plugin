package de.lifemytouch.ansi.coin.repository;

import java.util.UUID;

public interface ICoinRepository {
    long getCoins(UUID uuid);
    void setCoins(UUID uuid, long amount);
    boolean isHidden(UUID uuid);
    void setHidden(UUID uuid, boolean hidden);
    UUID findUuidByName(String name);
    void updatePlayerName(UUID uuid, String name);
}
