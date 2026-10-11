package de.lifemytouch.ansi.dependency;

import de.lifemytouch.ansi.build.BuildService;
import de.lifemytouch.ansi.coin.CoinRepository;
import de.lifemytouch.ansi.coin.CoinService;
import de.lifemytouch.ansi.database.DatabaseConfig;
import de.lifemytouch.ansi.database.DatabaseManager;
import de.lifemytouch.ansi.fly.FlyService;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerContext {

    private final JavaPlugin javaPlugin;
    private final FlyService flyService;
    private final BuildService buildService;
    private final CoinRepository coinRepository;
    private final CoinService coinService;
    private final DatabaseConfig databaseConfig;
    private final DatabaseManager databaseManager;

    public ServerContext(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.databaseConfig = new DatabaseConfig(javaPlugin);
        this.databaseManager = new DatabaseManager(javaPlugin, databaseConfig);

        this.coinRepository = new CoinRepository(javaPlugin, databaseManager);

        this.flyService = new FlyService();
        this.buildService = new BuildService();
        this.coinService = new CoinService(coinRepository);
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

    public CoinRepository getCoinRepository() {
        return coinRepository;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

}
