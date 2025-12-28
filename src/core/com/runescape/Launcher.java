package com.runescape;

import com.runescape.adapter.protocol.ClientConfigurationAdapter;
import com.runescape.io.XMLSession;
import com.runescape.link.LinkServer;
import com.runescape.utility.Configuration;
import com.runescape.utility.Logging;
import com.runescape.utility.StringParser;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Launcher {
    private static final Logger logger = Logging.log();

    public static void main(String[] args) {
        if (args.length < 1) {
            showHelp();
            return;
        }

        try {
            String applicationSwitch = args[0].startsWith("-")
                    ? args[0].substring(1).trim()
                    : args[0].trim();

            Static.parser = new StringParser();
            Static.parser.registerVariable("WORK_DIR", Constants.DATA_FOLDER);
            Static.parser.registerVariable("APP_TYPE", applicationSwitch);
            Static.parser.registerVariable("BASIC_APP_TYPE", applicationSwitch.equals("link") ? "link" : "game");
            Static.conf = new Configuration(Static.parseString(Constants.CONFIGURATION_FILE));
            Static.xml = new XMLSession();
            Static.xml.loadAliases(Static.parseString(Constants.DATA_FOLDER + "/world/aliases.xml"));
            PropertyConfigurator.configure(Static.conf.getString("logging_config_file"));
            Static.clientConf = new ClientConfigurationAdapter();
            logger.info("RT5D is now loading");
            String[] appArgs = new String[args.length - 1];
            if (args.length > 1) {
                System.arraycopy(args, 1, appArgs, 0, args.length - 1);
            }
            Application app = null;
            switch (applicationSwitch) {
                case "game":
                    app = new GameServer();
                    break;
                case "lobby":
                    app = new LobbyServer();
                    break;
                case "link":
                    app = new LinkServer();
                    break;
                default:
                    logger.fatal("Invalid application switch set: \"" + applicationSwitch + "\"");
                    showHelp();
                    return;
            }
            Static.app = app;
            if (app != null) {
                app.main(appArgs);
            }
            System.gc();
            System.runFinalization();

        } catch (Throwable e) {
            System.err.println();
            System.err.println("FATAL: Unable to initiate RT5D!");
            e.printStackTrace();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
            System.exit(1);
        }
    }

    public static void showHelp() {
        System.out.println("USAGE: Launcher [application switch] [world id] [protocol mode]");
        System.out.println();
        System.out.println("EXAMPLES:");
        System.out.println("   Game Server:  -game 1 dual-proto");
        System.out.println("   Lobby Server: -lobby");
        System.out.println("   Link Server:  -link");
        System.out.println();
        System.out.println("Working directory is set to ./data");
    }
}
