package com.gmail.sungmin0511a.mcqchat.base;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * ClientMouseEventHandler
 */
public class ClientMouseEventHandler {
    private static int MOUSE_EVENT_MOVE = -1;
    private static int MOUSE_EVENT_WHEEL_CLICKED = -1;

    @SubscribeEvent
    public void onMouseEvent(MouseEvent e) {
        if(e.getButton() == MOUSE_EVENT_MOVE){
            return;
        }
        QuickChatMod.logger.info("on mouse event listen");
        QuickChatMod.logger.info("event is : {}",e.getButton());
        QuickChatMod.logger.info("state is : {}",e.isButtonstate());
    }

}