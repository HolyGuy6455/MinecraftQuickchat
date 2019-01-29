package com.gmail.sungmin0511a.mcqchat.base;

import com.gmail.sungmin0511a.mcqchat.gui.ClientToolbarGUI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * ClientMouseEventHandler
 */
public class ClientMouseEventHandler {
    public static int MOUSE_EVENT_MOVE = -1;
    public static int MOUSE_EVENT_WHEEL_CLICK = 2;

    @SubscribeEvent
    public void onMouseEvent(MouseEvent e) {
        if(e.getButton() == MOUSE_EVENT_MOVE){
            return;
        }else if(e.getButton() == MOUSE_EVENT_WHEEL_CLICK){
            Minecraft.getMinecraft().displayGuiScreen(new ClientToolbarGUI());
        }
        QuickChatMod.logger.info("on mouse event listen");
        QuickChatMod.logger.info("event is : {}",e.getButton());
        QuickChatMod.logger.info("state is : {}",e.isButtonstate());
    }

}