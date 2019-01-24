package com.gmail.sungmin0511a.mcqchat.gui;

import java.io.IOException;

import com.gmail.sungmin0511a.mcqchat.base.QuickChatMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * ClientToolbarGUI
 */
public class ClientToolbarGUI extends GuiScreen {

    private ResourceLocation resourceLocation;
    final private int GUI_WIDTH = 186;
    final private int GUI_HEIGHT = 256;

    @Override
    public void initGui() {
        super.initGui();
        resourceLocation = new ResourceLocation(QuickChatMod.MODID, "textures/gui/toolbar.png");
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
        drawTexturedModalRect(0, 0, 0, 0, GUI_WIDTH, GUI_HEIGHT);
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
}