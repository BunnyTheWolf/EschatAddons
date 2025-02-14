package com.bunny.eschatAddons.features;

import com.bunny.eschatAddons.eschatAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

public class WardrobeSlotBinder {
    public WardrobeSlotBinder() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void GuiOpenEvent(GuiOpenEvent event) {
        // check the name of the gui
        if (event.gui instanceof GuiChest) {
            GuiChest currentScreen = (GuiChest)event.gui;
            ContainerChest container = (ContainerChest)currentScreen.inventorySlots;
            String guiTitle = container.getLowerChestInventory().getDisplayName().getUnformattedText();
            // check if the chest is a wardrobe
            System.out.println(guiTitle);
            if (guiTitle.contains("Wardrobe")) {
                eschatAddons.checkGUI = container;



            }
        }
    }
}
