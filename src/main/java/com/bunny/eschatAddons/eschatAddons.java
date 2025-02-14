package com.bunny.eschatAddons;

import com.bunny.eschatAddons.Commands.CommandEschat;
import com.bunny.eschatAddons.Commands.NoDT;
import com.bunny.eschatAddons.config.ConfigHandler;
import com.bunny.eschatAddons.features.*;
import com.bunny.eschatAddons.HUD.NoDTHUD;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.Sys;
import scala.collection.parallel.ParIterableLike;

@Mod(modid = eschatAddons.MODID, version = eschatAddons.VERSION)
public class eschatAddons {
    public static final String MODID = "eschatAddons";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static eschatAddons instance; // Ensure instance is available

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("[eschatAddons] Pre-initialization started...");
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    public static GuiScreen screenToOpenNextTick = null;
    public static ContainerChest checkGUI = null;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;
        if (screenToOpenNextTick != null) {
            Minecraft.getMinecraft().displayGuiScreen(screenToOpenNextTick);
            screenToOpenNextTick = null;
        }
        if (checkGUI != null) {
            for (int i = 0; i< 54; i++) {
                System.out.println(checkGUI.getLowerChestInventory().getStackInSlot(i));
            }
            checkGUI = null;
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("[eschatAddons] Registering event handlers...");

        // Register keybinds

        // Register other event handlers
        MinecraftForge.EVENT_BUS.register(new WardrobeSlotBinder());
        MinecraftForge.EVENT_BUS.register(new NoDTListener());
        MinecraftForge.EVENT_BUS.register(new RevTradeListener());
        MinecraftForge.EVENT_BUS.register(new PartyCommands());
        MinecraftForge.EVENT_BUS.register(new NoDTHUD());
        MinecraftForge.EVENT_BUS.register(new DungeonFloorCommands());
        MinecraftForge.EVENT_BUS.register(this);

        // register commands
        ClientCommandHandler.instance.registerCommand(new CommandEschat());
        ClientCommandHandler.instance.registerCommand(new NoDT());
    }
}
