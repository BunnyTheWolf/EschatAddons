package com.bunny.eschatAddons.GUI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiSlider;
import com.bunny.eschatAddons.config.ConfigHandler;

import net.minecraft.util.StringUtils;

public class GuiPartyCommands extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiButton PartyCommandsEnabled;
    private GuiButton PartyInvitesEnabled;
    private GuiButton PartyTransferEnabled;
    private GuiButton PartyWarpEnabled;
    private GuiButton PartyAllInvEnabled;
    private GuiButton PartyCoinflipEnabled;
    private GuiButton PartyRollsEnabled;

    public GuiPartyCommands(GuiScreen parent){
        this.parentScreen = parent;
    }

    @Override
    public void initGui() {
        buttonList.clear();

        // Toggle Buttons
        PartyCommandsEnabled = new GuiButton(0, width / 2 - 100, height / 2 - 25, 200, 20, getPartyCommandsEnabledText());
        buttonList.add(PartyCommandsEnabled);

        PartyInvitesEnabled = new GuiButton(1, width / 2 - 100, height / 2, 200, 20, getPartyInvitesEnabledText());
        buttonList.add(PartyInvitesEnabled);

        PartyTransferEnabled = new GuiButton(2, width / 2 - 100, height / 2 + 25, 200, 20, getPartyTransferEnabledText());
        buttonList.add(PartyTransferEnabled);

        PartyWarpEnabled = new GuiButton(3, width / 2 - 100, height / 2 + 50, 200, 20, getWarpEnabledText());
        buttonList.add(PartyWarpEnabled);

        PartyAllInvEnabled = new GuiButton(4, width / 2 - 100, height / 2 + 75, 200, 20, getAllInvEnabledText());
        buttonList.add(PartyAllInvEnabled);

        PartyCoinflipEnabled = new GuiButton(5, width / 2 - 100, height / 2 + 100, 200, 20, getCoinFlipEnabledText());
        buttonList.add(PartyCoinflipEnabled);

        PartyRollsEnabled = new GuiButton(6, width / 2 - 100, height / 2 + 125, 200, 20, getRollsEnabledText());
        buttonList.add(PartyRollsEnabled);

        buttonList.add(new GuiButton(20, width / 2 - 100, height / 2 + 150, 200, 20, "Back"));

    }

    @Override
    protected void actionPerformed(GuiButton button){
        if (button.id == 0) {

            ConfigHandler.PartyCommandsEnabled = !ConfigHandler.PartyCommandsEnabled;
            PartyCommandsEnabled.displayString = getPartyCommandsEnabledText();

        } else if (button.id == 1) {

            ConfigHandler.PartyInvEnabled = !ConfigHandler.PartyInvEnabled;
            PartyInvitesEnabled.displayString = getPartyInvitesEnabledText();

        } else if (button.id == 2) {

            ConfigHandler.PartyTransferEnabled = !ConfigHandler.PartyTransferEnabled;
            PartyTransferEnabled.displayString = getPartyTransferEnabledText();

        } else if (button.id == 3) {

            ConfigHandler.PartyWarpEnabled = !ConfigHandler.PartyWarpEnabled;
            PartyWarpEnabled.displayString = getWarpEnabledText();

        } else if (button.id == 4) {

            ConfigHandler.PartyAllInvEnabled = !ConfigHandler.PartyAllInvEnabled;
            PartyAllInvEnabled.displayString = getAllInvEnabledText();

        } else if (button.id == 5) {

            ConfigHandler.PartyCoinFlipEnabled = !ConfigHandler.PartyCoinFlipEnabled;
            PartyCoinflipEnabled.displayString = getCoinFlipEnabledText();

        } else if (button.id == 6) {

            ConfigHandler.PartyRollsEnabled = !ConfigHandler.PartyRollsEnabled;
            PartyRollsEnabled.displayString = getRollsEnabledText();

        } else if (button.id == 20) {
            mc.displayGuiScreen(parentScreen); // Return to the previous menu
        }
    }

    private String getPartyCommandsEnabledText(){
        return "Party Commands: " + (ConfigHandler.PartyCommandsEnabled ? "Enabled" : "Disabled");
    }

    private String getPartyInvitesEnabledText(){
        return "Private Party Invites: " + (ConfigHandler.PartyInvEnabled ? "Enabled" : "Disabled");
    }

    private String getPartyTransferEnabledText(){
        return "Party Transfer: " + (ConfigHandler.PartyTransferEnabled ? "Enabled" : "Disabled");
    }

    private String getWarpEnabledText(){
        return "Party Warps: " + (ConfigHandler.PartyWarpEnabled ? "Enabled" : "Disabled");
    }

    private String getAllInvEnabledText(){
        return "Party All Invites: " + (ConfigHandler.PartyAllInvEnabled ? "Enabled" : "Disabled");
    }

    private String getCoinFlipEnabledText(){
        return "Coinflips: " + (ConfigHandler.PartyCoinFlipEnabled ? "Enabled" : "Disabled");
    }

    private String getRollsEnabledText(){
        return "Rolls: " + (ConfigHandler.PartyRollsEnabled ? "Enabled" : "Disabled");
    }

    @Override
    public void onGuiClosed() {
        ConfigHandler.saveConfig();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}