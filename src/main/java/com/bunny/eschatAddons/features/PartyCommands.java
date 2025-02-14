package com.bunny.eschatAddons.features;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import com.bunny.eschatAddons.config.ConfigHandler;
import net.minecraft.util.StringUtils;
import org.lwjgl.Sys;
import scala.collection.parallel.ParIterableLike;

public class PartyCommands {

    public PartyCommands() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {

        if (ConfigHandler.PartyCommandsEnabled) {

            // Get plain chat message without color codes or hidden characters
            String rawMessage = StringUtils.stripControlCodes(event.message.getUnformattedText());

            rawMessage = rawMessage.replace("[MVP++]", "").replace("[MVP+]", "").replace("[MVP]", "").replace("[VIP+]", "").replace("[VIP]", "")
                    .replaceAll("\\s+", " "); // Remove extra spaces
            System.out.println("Formatted Message: " + rawMessage);

            // Regex to match the pattern for "!inv"
            if (rawMessage.matches("^From\\s+(\\w+): !inv$")) { // Updated regex for space after 'From'
                // Extract username from formattedMessage
                String[] parts = rawMessage.split(" ");
                String playerName = parts[1].replace(":", ""); // Remove colon from the username

                System.out.println("Sending Command: /p " + playerName);

                Minecraft.getMinecraft().thePlayer.sendChatMessage("/p " + playerName);
            } else

            if (ConfigHandler.PartyTransferEnabled && rawMessage.matches("Party > ([^:]+): !ptme$")){

                rawMessage = rawMessage.replace(">", "").replaceAll("\\s+", " ");

                String[] parts = rawMessage.split(" ");
                String playerName = parts[1].replace(":", "");

                System.out.println("Sending Command: /p transfer " + playerName);
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party transfer " + playerName);

            } else if (ConfigHandler.PartyWarpEnabled && rawMessage.matches("Party > ([^:]+): !warp")){

                System.out.println("Sending Command: /p warp");
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party warp");

            } else if (ConfigHandler.PartyAllInvEnabled && rawMessage.matches("Party > ([^:]+): !allinv")){

                System.out.println("Sending Command: /party settings allinvite");
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party settings allinvite");

            } else if (ConfigHandler.PartyCoinFlipEnabled && rawMessage.matches("Party > ([^:]+): !cf")) {

                new Thread(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int randomNum = (Math.random() < 0.5) ? 0 : 1;
                    String result = (randomNum == 0) ? "Heads" : "Tails";

                    System.out.println("Landed On: " + result);
                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/party chat Landed on: " + result);

                }).start();

            } else if (ConfigHandler.PartyRollsEnabled && rawMessage.matches("Party > ([^:]+): !roll (\\d+)")) {

                rawMessage = rawMessage.replace(">", "").replace("!", "").replaceAll("\\s+", " ");

                String[] parts = rawMessage.split(" ");
                String playerName = parts[1].replace(":", "");
                int roll = Integer.parseInt(parts[3]);

                new Thread(() -> {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int MaxRoll = roll;

                    int randomNum = (int) (Math.random() * MaxRoll) + 1;

                    System.out.println("Rolled: " + randomNum);

                    Minecraft.getMinecraft().thePlayer.sendChatMessage("/party chat Rolled: " + randomNum);

                }).start();

            }
        }
    }
}
