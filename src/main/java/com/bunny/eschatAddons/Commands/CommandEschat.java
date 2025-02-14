package com.bunny.eschatAddons.Commands;

import com.bunny.eschatAddons.GUI.GuiEschat;
import com.bunny.eschatAddons.eschatAddons;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommandEschat extends CommandBase {

    @Override
    public String getCommandName() {
        return "eschat";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/eschat - Opens the Eschat settings GUI";
    }

    @Override
    @SideOnly(Side.CLIENT) // Ensures this runs only on the client
    public void processCommand(ICommandSender sender, String[] args) {
        eschatAddons.screenToOpenNextTick = new GuiEschat();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // Allow all players to use the command
    }
}