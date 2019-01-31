package com.gmail.sungmin0511a.mcqchat.gui;

import com.gmail.sungmin0511a.mcqchat.base.QuickChatMod;

import net.minecraft.client.Minecraft;

/**
 * QuickChat
 */
public class QuickChat {
    private int category1;
    private int category2;

    public QuickChat(){
        resetCategory();
    }    

    /**
     * @return the category1
     */
    public int getCategory1() {
        return category1;
    }
    
    /**
     * @return the category2
     */
    public int getCategory2() {
        return category2;
    }

    /**
     * @param category1 the category1 to set
     */
    public void setCategory(int category) {
        if(this.category1 != 0){
            this.category1 = category;
        }else{
            this.category2 = category;
        }
    }

    public void resetCategory(){
        this.category1 = 0;
        this.category2 = 0;
    }

    /**
     * @return that the category1 is selected
     */
    public boolean isCategory1stSeleted() {
        return (this.category1 == 0);
    }


    public void sendChat() {
        QuickChatMod.logger.info("message : " + category1 + "-" + category2 );
    }
    
    
}