package com.gmail.sungmin0511a.mcqchat.gui;

import java.io.IOException;

import com.gmail.sungmin0511a.mcqchat.base.ClientMouseEventHandler;
import com.gmail.sungmin0511a.mcqchat.base.QuickChatMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * ClientToolbarGUI
 */
public class ClientToolbarGUI extends GuiScreen {

    private ResourceLocation resourceLocation;
    final private int GUI_WIDTH = 28;
    final private int GUI_HEIGHT = 28;
    final private int SHT = 29; // short reach
    final private int LNG = 60; // long reach
    final private int DRAW_LOCATION_X[] = {-SHT, 1  , SHT,-LNG, LNG,-SHT, 0  , SHT};
    final private int DRAW_LOCATION_Y[] = {-SHT,-LNG,-SHT, 0  , 0  , SHT, LNG, SHT};

    public ClientToolbarGUI(){
        resourceLocation = new ResourceLocation(QuickChatMod.MODID, "textures/gui/toolbar.png");
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        GlStateManager.translate(width/2,height/2,0);
        GlStateManager.scale(2,2,2);
        
        for (int i = 0; i < 8; i++) {
            drawTexturedModalRect(DRAW_LOCATION_X[i]-14, DRAW_LOCATION_Y[i]-14,0, 0, GUI_WIDTH, GUI_HEIGHT);
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        QuickChatMod.logger.info("state : {}",state);
        if(state == ClientMouseEventHandler.MOUSE_EVENT_WHEEL_CLICK){
            //quit
            //Minecraft.getMinecraft().displayGuiScreen(null);
        }else{
            sendChatMessage("wow rywly?");
        }
        
    }
}