package com.gmail.sungmin0511a.mcqchat.base;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = QuickChatMod.MODID, name = QuickChatMod.NAME, version = QuickChatMod.VERSION)
public class QuickChatMod
{
    public static final String MODID = "mcqchat";
    public static final String NAME = "Quick Chat";
    public static final String VERSION = "1.0";
    public static final String CLIENT_PROXY_CLASS = "com.gmail.sungmin0511a.mcqchat.base.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.gmail.sungmin0511a.mcqchat.base.CommonProxy";

    private static Logger logger;

    @SidedProxy(clientSide=QuickChatMod.CLIENT_PROXY_CLASS,serverSide=QuickChatMod.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.Instance
    public static QuickChatMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientMouseEventHandler());
        proxy.postInit(event);

    }


}
