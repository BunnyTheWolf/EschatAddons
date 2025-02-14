package com.bunny.eschatAddons.config;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class ConfigHandler {

    private static Configuration config;

    // No Downtime Config
    public static boolean NoDTEnabled;
    public static int NoDTDelay;
    public static boolean NoDTHUD;
    public static int NoDTHUD_X;
    public static int NoDTHUD_Y;

    // --------------------------------------------------------------

    // Party Commands

    public static boolean PartyCommandsEnabled;
    public static boolean PartyInvEnabled;
    public static boolean PartyTransferEnabled;
    public static boolean PartyWarpEnabled;
    public static boolean PartyAllInvEnabled;
    public static boolean PartyCoinFlipEnabled;
    public static boolean PartyRollsEnabled;


    public static void loadConfig(File file) {
        config = new Configuration(file);

        try {
            config.load();
        } catch (Exception e) {
            System.err.println("Failed to load configuration file: " + file.getName());
            e.printStackTrace();
        }

        // No DT

        NoDTEnabled = config.get("NoDT", "Enabled", false, "Is No Downtime Enabled?").getBoolean();
        NoDTDelay = config.get("NoDT", "Delay", 0, "How long is the delay before entering a new run?").getInt();
        NoDTHUD = config.get("NoDT", "HUD", false, "No Downtime Enabled/Disabled HUD").getBoolean();
        NoDTHUD_X = config.get("NoDT", "X_Position", 50, "The X position of the NoDT HUD").getInt();
        NoDTHUD_Y = config.get("NoDT", "Y_Position", 50, "The Y position of the NoDT HUD").getInt();

        // -----------------------------------------------------------------

        // Party Commands

        PartyCommandsEnabled = config.get("PartyCommands", "Enabled", false, "Are the party commands Enabled?").getBoolean();
        PartyInvEnabled = config.get("PartyCommands", "InvEnabled", true, "Is !inv enabled?").getBoolean();
        PartyTransferEnabled = config.get("PartyCommands", "TransferEnabled", true, "Is !ptme enabled?").getBoolean();
        PartyWarpEnabled = config.get("PartyCommands", "WarpEnabled", true, "Is !warp Enabled?").getBoolean();
        PartyAllInvEnabled = config.get("PartyCommands", "AllInvEnabled", true, "Is !allinv Enabled?").getBoolean();
        PartyCoinFlipEnabled = config.get("PartyCommands", "CoinflipEnabled", true, "Is !cf Enabled?").getBoolean();
        PartyRollsEnabled = config.get("PartyCommands", "RollsEnabled", true, "Is !roll Enabled?").getBoolean();

    }

    public static void saveConfig() {
        if (config == null) {
            System.err.println("Config not initialized. Call loadConfig() first.");
            return;
        }

        // ---------------------------------------------------------------------

        // NoDT

        config.get("NoDT", "Enabled", NoDTEnabled, "Is No Downtime Enabled?").set(NoDTEnabled);
        config.get("NoDT", "Delay", NoDTDelay, "How long is the delay before entering a new run?").set(NoDTDelay);
        config.get("NoDT", "HUD", NoDTHUD, "No Downtime Enabled/Disabled HUD").set(NoDTHUD);
        config.get("NoDT", "X_Position", NoDTHUD_X, "The X position of the NoDT HUD").set(NoDTHUD_X);
        config.get("NoDT", "Y_Position", NoDTHUD_Y, "The Y position of the NoDT HUD").set(NoDTHUD_Y);

        // ---------------------------------------------------------------------

        // Party Commands

        config.get("PartyCommands", "Enabled", false, "Are the party commands Enabled?").set(PartyCommandsEnabled);
        config.get("PartyCommands", "InvEnabled", true, "Is !inv enabled?").set(PartyInvEnabled);
        config.get("PartyCommands", "TransferEnabled", true, "Is !ptme Enabled?").set(PartyTransferEnabled);
        config.get("PartyCommands", "WarpEnabled", true, "Is !warp Enabled?").set(PartyWarpEnabled);
        config.get("PartyCommands", "AllInvEnabled", true, "Is !allinv Enabled?").set(PartyAllInvEnabled);
        config.get("PartyCommands", "CoinflipEnabled", true, "Is !cf Enabled?").set(PartyCoinFlipEnabled);
        config.get("PartyCommands", "RollsEnabled", true, "Is !roll Enabled?").set(PartyRollsEnabled);

        // Force writing the changes
        config.save();
        System.out.println("Configuration saved successfully.");
    }
}
