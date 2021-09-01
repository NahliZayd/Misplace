package xyz.thebloodhound.erasechatmodule;


import net.minecraftforge.client.ClientCommandHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;


@Mod(modid = "erasechatmod", name = "EraseChatMod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class EraseChatMod {


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new EraseCommand());
    }

}
