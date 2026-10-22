package de.lifemytouch.ansi.dependency;

import de.lifemytouch.ansi.build.service.BuildService;
import de.lifemytouch.ansi.coin.repository.CoinRepository;
import de.lifemytouch.ansi.coin.service.CoinService;
import de.lifemytouch.ansi.database.config.DatabaseConfig;
import de.lifemytouch.ansi.database.service.DatabaseService;
import de.lifemytouch.ansi.fly.service.FlyService;
import de.lifemytouch.ansi.misc.teleport.service.TeleportService;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerContext {

    private final JavaPlugin javaPlugin;
    private final FlyService flyService;
    private final BuildService buildService;
    private final CoinRepository coinRepository;
    private final CoinService coinService;
    private final DatabaseConfig databaseConfig;
    private final DatabaseService databaseService;
    private final TeleportService teleportService;

    public ServerContext(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.databaseConfig = new DatabaseConfig(javaPlugin);
        this.databaseService = new DatabaseService(javaPlugin, databaseConfig);

        this.coinRepository = new CoinRepository(javaPlugin, databaseService);

        this.flyService = new FlyService();
        this.buildService = new BuildService();
        this.coinService = new CoinService(coinRepository);
        this.teleportService = new TeleportService();
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public FlyService getFlyService() {
        return flyService;
    }

    public BuildService getBuildService() {
        return buildService;
    }

    public CoinService getCoinService() {
        return coinService;
    }

    public TeleportService getTeleportService() {
        return teleportService;
    }

    public CoinRepository getCoinRepository() {
        return coinRepository;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public DatabaseService getDatabaseManager() {
        return databaseService;
    }

}
