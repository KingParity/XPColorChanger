package com.kingparity.xpcc.client;

import com.kingparity.xpcc.Config;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents
{
    @SubscribeEvent
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        if(event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR)
        {
            IngameGui gui = Minecraft.getInstance().ingameGUI;
            mc.getTextureManager().bindTexture(IngameGui.GUI_ICONS_LOCATION);
            
            ClientPlayerEntity player = Minecraft.getInstance().player;
            
            if(!player.isRidingHorse())
            {
                int scaledWidth = mc.getMainWindow().getScaledWidth();
                int scaledHeight = mc.getMainWindow().getScaledHeight();
                MatrixStack matrixStack = new MatrixStack();
                RenderSystem.enableBlend();
                if(!mc.gameSettings.hideGUI)
                {
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    gui.setBlitOffset(-90);
                    renderExperience(scaledWidth, matrixStack, event, mc, scaledHeight, gui);
                }
            }
        }
    }
    
    protected void renderExperience(int scaledWidth, MatrixStack matrixStack, RenderGameOverlayEvent event, Minecraft mc, int scaledHeight, IngameGui gui)
    {
        Minecraft.getInstance().getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
        
        if(mc.playerController.gameIsSurvivalOrAdventure())
        {
            this.renderExperience(matrixStack, scaledWidth / 2 - 91, mc, scaledHeight, scaledWidth, gui);
        }
        RenderSystem.enableBlend();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
    
    public void renderExperience(MatrixStack matrixStack, int p_238454_2_, Minecraft mc, int scaledHeight, int scaledWidth, IngameGui gui)
    {
        mc.getProfiler().startSection("expBar");
        mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
        int i = mc.player.xpBarCap();
        if(i > 0)
        {
            int j = 182;
            int k = (int)(mc.player.experience * 183.0F);
            int l = scaledHeight - 32 + 3;
            gui.blit(matrixStack, p_238454_2_, l, 0, 64, 182, 5);
            if(k > 0)
            {
                gui.blit(matrixStack, p_238454_2_, l, 0, 69, k, 5);
            }
        }
        
        mc.getProfiler().endSection();
        if(mc.player.experienceLevel > 0)
        {
            mc.getProfiler().startSection("expLevel");
            String s = "" + mc.player.experienceLevel;
            int i1 = (scaledWidth - gui.getFontRenderer().getStringWidth(s)) / 2;
            int j1 = scaledHeight - 31 - 4;
            gui.getFontRenderer().drawString(matrixStack, s, (float)(i1 + 1), (float)j1, 0);
            gui.getFontRenderer().drawString(matrixStack, s, (float)(i1 - 1), (float)j1, 0);
            gui.getFontRenderer().drawString(matrixStack, s, (float)i1, (float)(j1 + 1), 0);
            gui.getFontRenderer().drawString(matrixStack, s, (float)i1, (float)(j1 - 1), 0);
    
            String red = Integer.toHexString(Config.CLIENT.xpBarRed.get());
            String green = Integer.toHexString(Config.CLIENT.xpBarGreen.get());
            String blue = Integer.toHexString(Config.CLIENT.xpBarBlue.get());
            String colorStr = red + green + blue;
            int color = Integer.parseInt(colorStr, 16);
            gui.getFontRenderer().drawString(matrixStack, s, (float)i1, (float)j1, color);
            mc.getProfiler().endSection();
        }
    }
}