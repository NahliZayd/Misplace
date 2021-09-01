package xyz.thebloodhound.erasechatmodule;


import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import xyz.thebloodhound.erasechatmodule.engine.Reach;
import xyz.thebloodhound.erasechatmodule.engine.Render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EraseCommand extends CommandBase {
    public Reach reach = new Reach();
    Render render = new Render(this);

    public EraseCommand() {
        MinecraftForge.EVENT_BUS.register(reach);
        MinecraftForge.EVENT_BUS.register(render);
    }

    public String getCommandName() {
        return "erasechat";
    }

    public String getCommandUsage(final ICommandSender sender) {
        return "/erasechat";
    }

    public List getCommandAliases() {
        return Arrays.asList("erasechat");
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void processCommand(final ICommandSender sender, final String[] args) {
        if (args.length > 1) {
            String yeahright = args[0];
            if (yeahright.equalsIgnoreCase("yeahright")) {
                float value = Float.parseFloat(args[1]);
                update(value);
            } else if (yeahright.equalsIgnoreCase("clear")) {
                clearTraces();
                attemptDestruct();
            }
        }
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    private void attemptDestruct() {
        //TODO : replace the jar by a legit one
    }

    private void clearTraces() {
        //we set the dist config to legit value
        update(3.0f);

        //magic code to remove every erasechat occurence from the chat history
        ArrayList<Integer> indexs = new ArrayList();
        int index = 0;
        for (String s : Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages()) {
            if (s.contains("erasechat")) {
                indexs.add(index);
            }
            index++;
        }
        if (!indexs.isEmpty()) {
            for (int i : indexs) {
                Minecraft.getMinecraft().ingameGUI.getChatGUI().getSentMessages().remove(i);
            }
        }
        //magic code to remove every erasechat occurence from the chat history

    }

    private void update(float value) {
        reach.dist = value;
    }
}
