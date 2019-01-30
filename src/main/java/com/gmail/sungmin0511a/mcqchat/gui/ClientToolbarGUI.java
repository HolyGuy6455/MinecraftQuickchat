package com.gmail.sungmin0511a.mcqchat.gui;

import java.io.IOException;

import com.gmail.sungmin0511a.mcqchat.base.ClientMouseEventHandler;
import com.gmail.sungmin0511a.mcqchat.base.QuickChatMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

/**
 * ClientToolbarGUI
 */
public class ClientToolbarGUI extends GuiScreen {

    private static ResourceLocation resourceLocation = new ResourceLocation(QuickChatMod.MODID, "textures/gui/toolbar.png");
    final private int SIDE_LENGTH = 28;
    final private int SHT = 29; // short reach
    final private int LNG = 40; // long reach
    final private int DRAW_LOCATION_X[] = {-SHT, 0  , SHT,-LNG, LNG,-SHT, 0  , SHT};
    final private int DRAW_LOCATION_Y[] = {-SHT,-LNG,-SHT, 0  , 0  , SHT, LNG, SHT};
    private QuickChat quickchat;

    public ClientToolbarGUI(){
        quickchat = new QuickChat();
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2,height/2,0);
        GlStateManager.scale(2,2,2);
        
        int mouseX_width = (mouseX-width/2)/2;
        int mouseY_height = (mouseY-height/2)/2;
        double mouse_diagonal = Math.sqrt((mouseX_width*mouseX_width)+(mouseY_height*mouseY_height));
        double acos = Math.acos(mouseX_width/mouse_diagonal);
        if(mouseY_height > 0){
            acos = Math.PI*2 - acos;
        }
        int result = (int)Math.round(acos/Math.PI*4);
        final int index[] = {4,2,1,0,3,5,6,7,4};
        
        for (int i = 0; i < 8; i++) {
            if( mouse_diagonal > 10 && i == index[result] ){
                // selected button 
                drawTexturedModalRect(DRAW_LOCATION_X[i]-14, DRAW_LOCATION_Y[i]-14, 30, 0, SIDE_LENGTH, SIDE_LENGTH);
                drawTexturedModalRect(DRAW_LOCATION_X[i]-14, DRAW_LOCATION_Y[i]-14, 29 + i*SIDE_LENGTH, 57, SIDE_LENGTH, SIDE_LENGTH);
            }else{
                // not selected
                drawTexturedModalRect(DRAW_LOCATION_X[i]-14, DRAW_LOCATION_Y[i]-14, 0 , 0, SIDE_LENGTH, SIDE_LENGTH);
                drawTexturedModalRect(DRAW_LOCATION_X[i]-14, DRAW_LOCATION_Y[i]-14, 29 + i*SIDE_LENGTH, 57, SIDE_LENGTH, SIDE_LENGTH);
            }
        }

        if( mouse_diagonal > 10){
            String str = I18n.format("quickchat.2_"+((index[result]<4)?index[result]+1:index[result]+2));
            fontRenderer.drawString(str, -fontRenderer.getStringWidth(str)/2, 0 , 0xffffff);
        }

        GlStateManager.popMatrix();
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
        //QuickChatMod.logger.info("state : {}",state);
        if(state == ClientMouseEventHandler.MOUSE_EVENT_WHEEL_CLICK){
            //quit
            Minecraft.getMinecraft().displayGuiScreen(null);
        }else{
            //sendChatMessage("wow rywly?");
        }
        
    }
}