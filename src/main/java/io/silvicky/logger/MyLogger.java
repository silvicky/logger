package io.silvicky.logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static final String MOD_ID = "MyLogger";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);
    public static final Logger chatLogger = createIsolatedLogger("chat");
    public static final Logger loginLogger = createIsolatedLogger("login");
    public static final Logger cmdLogger = createIsolatedLogger("cmd");
    public static Logger createIsolatedLogger(String loggerName) {
        try {
            Logger logger = Logger.getLogger(loggerName);
            logger.setUseParentHandlers(false);
            Path logDir = FabricLoader.getInstance().getGameDir().resolve("logs").resolve(MOD_ID);
            logDir.toFile().mkdirs();
            FileHandler fileHandler = new FileHandler(
                    logDir.resolve(loggerName + ".log").toString(),
                    1024 * 1024,
                    1024,
                    true
            );
            fileHandler.setFormatter(new MyFormatter());
            logger.addHandler(fileHandler);
            return logger;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onInitialize() {
        LOGGER.info("Loading MyLogger...");
    }
}
