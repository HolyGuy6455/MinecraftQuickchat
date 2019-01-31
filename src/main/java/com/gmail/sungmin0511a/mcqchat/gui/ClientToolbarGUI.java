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

    private final static ResourceLocation resourceLocation = new ResourceLocation(QuickChatMod.MODID, "textures/gui/toolbar.png");
    private final int SIDE_LENGTH = 28;
    private final int SHT = 37; // short reach
    private final int LNG = 52; // long reach
    private final int DRAW_LOCATION_X[] = {-SHT, 0  , SHT,-LNG, LNG,-SHT, 0  , SHT};
    private final int DRAW_LOCATION_Y[] = { SHT, LNG, SHT, 0  , 0  ,-SHT,-LNG,-SHT};
    /*
     * Draw Location with index
     * 5 6 7
     * 3   4
     * 0 1 2
     * */
    private QuickChat quickchat;
    private int selectedItem;

    public ClientToolbarGUI(){
        quickchat = new QuickChat();
        selectedItem = -1;
    }

    @Override
    public void initGui() {
        super.initGui();
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        // Matrix setting
        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2,height/2,0);
        GlStateManager.scale(1.6f,1.6f,1.6f);
        int mouseX_width = (mouseX-width/2)/2;
        int mouseY_height = (mouseY-height/2)/2;
        double mouse_diagonal = Math.sqrt((mouseX_width*mouseX_width)+(mouseY_height*mouseY_height));

        // draw not selected button
        for (int i = 0; i < 8; i++) {
            drawTexturedModalRect(
                DRAW_LOCATION_X[i]-14, 
                DRAW_LOCATION_Y[i]-14, 
                228, 
                0, 
                SIDE_LENGTH, 
                SIDE_LENGTH);
        }

        if( mouse_diagonal > 10){
            // find degree to mouse for selected item with acos
            double acos = Math.acos(mouseX_width/mouse_diagonal);

            /* 
             * the acos value when mouse above center (it means mouse location Y is negative)
             * is same when mouse below center (mouse location Y is positive)
             * because mouse location Y is not affect the acos value
             * so check that mouse above center is true, change the degree value suitable to upside
             */
            if(mouseY_height > 0)   acos = Math.PI*2 - acos;

            final int drawLocationIndex[] = {4,7,6,5,3,0,1,2,4};
            selectedItem = drawLocationIndex[(int)Math.round(acos/Math.PI*4)];

            // draw selected button 
            drawTexturedModalRect(
                DRAW_LOCATION_X[selectedItem]-14, 
                DRAW_LOCATION_Y[selectedItem]-14, 
                228, 
                27, 
                SIDE_LENGTH, 
                SIDE_LENGTH);

            // render title of selected item
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("quickchat.item_title.");
            strBuilder.append(quickchat.getCategory1());
            strBuilder.append("_");
            strBuilder.append( (selectedItem<4) ? selectedItem+1 : selectedItem+2 );
            String str = I18n.format(strBuilder.toString().trim());

            fontRenderer.drawString(str, -fontRenderer.getStringWidth(str)/2, 0, 0xffffff);
            Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        }else{
            selectedItem = -1;
        }

        int category1 = quickchat.getCategory1();
        if(category1 > 5) category1 -= 1;

        // draw icon
        for (int i = 0; i < 8; i++) {
            drawTexturedModalRect(
                DRAW_LOCATION_X[i]-14, 
                DRAW_LOCATION_Y[i]-14, 
                i*SIDE_LENGTH,
                category1 * SIDE_LENGTH , 
                SIDE_LENGTH,
                SIDE_LENGTH);
        }

        // reset matrix
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
        switch (state) {
            case ClientMouseEventHandler.MOUSE_EVENT_WHEEL_CLICK:
                //quit
                Minecraft.getMinecraft().displayGuiScreen(null);
                
                break;

            case ClientMouseEventHandler.MOUSE_EVENT_LEFT_CLICK:
                // select item
                if(selectedItem != -1){
                    quickchat.setCategory( (selectedItem<4) ? selectedItem+1 : selectedItem+2 );
                }
                
                break;

            case ClientMouseEventHandler.MOUSE_EVENT_RIGHT_CLICK:
                // select item
                quickchat.resetCategory();
                
                break;
                
            default:
                //sendChatMessage("wow rywly?");
                break;
        }
    }
}