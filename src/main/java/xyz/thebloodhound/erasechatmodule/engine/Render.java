package xyz.thebloodhound.erasechatmodule.engine;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.thebloodhound.erasechatmodule.EraseCommand;

public class Render {
    Minecraft mc = Minecraft.getMinecraft();
    float partialTicks = 0;
    EraseCommand instance;

    public Render(EraseCommand eraseCommand) {
        instance = eraseCommand;
    }


    //we use this void to update the partialTicks
    @SubscribeEvent
    public void updateTicks(RenderWorldLastEvent e) {
        if (mc.thePlayer != null) {
            partialTicks = e.partialTicks;
        }
    }



    //this void is called before an entity rendering
    @SubscribeEvent
    public void onRender(RenderLivingEvent.Pre e) {
        if (mc.thePlayer != null) {
            //we check if the entity == the player
            if (e.entity != mc.thePlayer) {
                //if not we check if reach is active
                if (!(instance.reach.dist <= 3.0)) {
                    //if yes we cancel the entity render
                    e.setCanceled(true);

                    //and we render it on the new position
                    e.renderer.doRender(e.entity, instance.reach.nX, instance.reach.nY, instance.reach.nZ, e.entity.rotationYaw, partialTicks);
                }
            }
        }
    }
}
