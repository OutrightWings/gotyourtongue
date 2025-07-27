package com.outrightwings.gotyourtongue;

import com.mojang.logging.LogUtils;
import com.outrightwings.gotyourtongue.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "gotyourtongue";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Main(IEventBus modEventBus, ModContainer modContainer)
    {
        //modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModItems.POTIONS.register(modEventBus);
        //NeoForge.EVENT_BUS.register(this);

       modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
