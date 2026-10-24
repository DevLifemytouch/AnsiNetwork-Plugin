package de.lifemytouch.ansi.dependency;

import de.lifemytouch.ansi.admin.build.service.BuildService;
import de.lifemytouch.ansi.admin.location.repository.ILocationRepository;
import de.lifemytouch.ansi.admin.location.repository.LocationRepository;
import de.lifemytouch.ansi.admin.location.service.LocationService;
import de.lifemytouch.ansi.admin.misc.freeze.service.FreezeService;
import de.lifemytouch.ansi.coin.repository.CoinRepository;
import de.lifemytouch.ansi.coin.repository.ICoinRepository;
import de.lifemytouch.ansi.coin.service.CoinService;
import de.lifemytouch.ansi.database.config.DatabaseConfig;
import de.lifemytouch.ansi.database.service.DatabaseService;
import de.lifemytouch.ansi.admin.fly.service.FlyService;
import de.lifemytouch.ansi.admin.misc.teleport.service.TeleportService;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerContext {

    private final JavaPlugin javaPlugin;
    private final FlyService flyService;
    private final BuildService buildService;
    private final ICoinRepository coinRepository;
    private final ILocationRepository locationRepository;
    private final CoinService coinService;
    private final DatabaseConfig databaseConfig;
    private final DatabaseService databaseService;
    private final TeleportService teleportService;
    private final LocationService locationService;
    private final FreezeService freezeService;

    public ServerContext(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;

        this.databaseConfig = new DatabaseConfig(javaPlugin);
        this.databaseService = new DatabaseService(javaPlugin, databaseConfig);

        this.coinRepository = new CoinRepository(javaPlugin, databaseService);
        this.locationRepository = new LocationRepository(javaPlugin);

        this.flyService = new FlyService();
        this.buildService = new BuildService();
        this.coinService = new CoinService(coinRepository);
        this.teleportService = new TeleportService();
        this.locationService = new LocationService(locationRepository);
        this.freezeService = new FreezeService();
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

    public ICoinRepository getCoinRepository() {
        return coinRepository;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public DatabaseService getDatabaseManager() {
        return databaseService;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public FreezeService getFreezeService() {
        return freezeService;
    }
}
