package com.gmail.sungmin0511a.mcqchat.base;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * ClientMouseEventHandler
 */
public class ClientMouseEventHandler {

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent e) {
        if(e.getEntity() instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer)e.getEntity();
            player.inventory.addItemStackToInventory(new ItemStack(Items.IRON_SHOVEL));
        }
    }

    @SubscribeEvent
    public void livingDrops(LivingDropsEvent e) {
        if(e.getEntity() instanceof EntityMob){
            EntityMob mob = (EntityMob)e.getEntity();
            e.getDrops().add(new EntityItem(mob.world,mob.posX,mob.posY,mob.posZ, new ItemStack(Items.SPIDER_EYE)));
        }
    }

}