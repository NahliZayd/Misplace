package xyz.thebloodhound.erasechatmodule.engine;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

public class Reach {
    //our settings
    public double dist = 3.0;

    //the new entity position
    public double nX,nY,nZ;

    //Minecraft instance
    Minecraft mc = Minecraft.getMinecraft();


    //this void is called every mc ticks
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent e) {
        //if the player is not in a world we dont want our mod to work
        if (mc.theWorld == null || mc.thePlayer == null) {
            return;
        }

        //if dist is an legit value we do nothing
        if (!(dist <= 3.0)) {
            //iterate trough every entity
            for (Entity entity : mc.theWorld.getLoadedEntityList()) {
                //if the entity != the player and is closer than 12blocks then we apply the misplace
                if (entity != mc.thePlayer && entity.getDistanceToEntity(mc.thePlayer) < 12f) {
                    //the actual position
                    double x = entity.posX;
                    double y = entity.posY;
                    double z = entity.posZ;

                    //the distance
                    double f = (dist - 3.0);

                    //the entity must be out the legit range
                    if (mc.thePlayer.getDistanceToEntity(entity) > 2.9) {

                        //dewgz code mainly
                        double c = Math.hypot(mc.thePlayer.posX - x, mc.thePlayer.posZ - z);
                        if (f > c) {
                            f -= c;
                        }
                        float r = a(x, z);
                        if (a(Minecraft.getMinecraft().thePlayer.rotationYaw, r) > 90.0D) {
                            return;
                        }
                        double aa = Math.cos(Math.toRadians(r + 90.0f));
                        double bb = Math.sin(Math.toRadians(r + 90.0f));
                        x -= (aa * f);
                        z -= (bb * f);
                        //dewgz code mainly

                        //the max bound for the new hitbox
                        double maxX = x + entity.width / 2;
                        double maxY = y + entity.height;
                        double maxZ = z + entity.width / 2;

                        //the new entity pos
                        nX = x;
                        nY = y;
                        nZ = z;
                        //the new entity pos

                        //the new hitbox
                        AxisAlignedBB nBounding = new AxisAlignedBB(x - entity.width / 2, y, z - entity.width / 2, maxX, maxY, maxZ);
                        entity.setEntityBoundingBox(nBounding);
                        //the new hitbox
                    }
                }
            }

        }
    }

    //dewgz code
    private double a(float a, float b) {
        float d = Math.abs(a - b) % 360.0F;
        if (d > 180.0F) {
            d = 360.0F - d;
        }
        return d;
    }

    private float a(double ex, double ez) {
        double x = ex - Minecraft.getMinecraft().thePlayer.posX;
        double z = ez - Minecraft.getMinecraft().thePlayer.posZ;
        float y = (float) Math.toDegrees(-Math.atan(x / z));
        if ((z < 0.0D) && (x < 0.0D)) {
            y = (float) (90.0D + Math.toDegrees(Math.atan(z / x)));
        } else if ((z < 0.0D) && (x > 0.0D)) {
            y = (float) (-90.0D + Math.toDegrees(Math.atan(z / x)));
        }

        return y;
    }
    //dewgz code
}
