package com.outrightwings.gotyourtongue.item;

import com.outrightwings.gotyourtongue.Main;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.common.Mod;
@EventBusSubscriber(modid = Main.MODID)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Main.MODID);
    public static final DeferredHolder<Item, Item> BOTTLED_FEAR = ITEMS.register("bottled_fear",() -> new Item((new Item.Properties().stacksTo(16))));

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Main.MODID);

    public static final DeferredHolder<Potion,Potion> FATIGUE = POTIONS.register("fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,3600,1)));
    public static final DeferredHolder<Potion,Potion> LONG_FATIGUE = POTIONS.register("long_fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,9600,1)));
    public static final DeferredHolder<Potion,Potion> STRONG_FATIGUE = POTIONS.register("strong_fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,3600,2)));

    public static final DeferredHolder<Potion,Potion> HASTE = POTIONS.register("haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,3600)));
    public static final DeferredHolder<Potion,Potion> LONG_HASTE = POTIONS.register("long_haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,9600)));
    public static final DeferredHolder<Potion,Potion> STRONG_HASTE = POTIONS.register("strong_haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,3600,1)));

    @SubscribeEvent
    public static void registerPotionRecipes(RegisterBrewingRecipesEvent event){
        event.getBuilder().addMix(Potions.AWKWARD,BOTTLED_FEAR.get(), FATIGUE);

        event.getBuilder().addMix(FATIGUE, Items.REDSTONE, LONG_FATIGUE);
        event.getBuilder().addMix(FATIGUE, Items.GLOWSTONE, STRONG_FATIGUE);

        event.getBuilder().addMix(STRONG_FATIGUE, Items.REDSTONE, LONG_FATIGUE);
        event.getBuilder().addMix(LONG_FATIGUE, Items.GLOWSTONE_DUST, STRONG_FATIGUE);

        event.getBuilder().addMix(HASTE, Items.FERMENTED_SPIDER_EYE, FATIGUE);
        event.getBuilder().addMix(FATIGUE, Items.FERMENTED_SPIDER_EYE, HASTE);
        event.getBuilder().addMix(STRONG_FATIGUE, Items.FERMENTED_SPIDER_EYE, STRONG_HASTE);
        event.getBuilder().addMix(LONG_FATIGUE, Items.FERMENTED_SPIDER_EYE, LONG_HASTE);
        event.getBuilder().addMix(STRONG_HASTE, Items.FERMENTED_SPIDER_EYE, STRONG_FATIGUE);
        event.getBuilder().addMix(LONG_HASTE, Items.FERMENTED_SPIDER_EYE, LONG_FATIGUE);

        event.getBuilder().addMix(HASTE, Items.REDSTONE, LONG_HASTE);
        event.getBuilder().addMix(HASTE, Items.GLOWSTONE, STRONG_HASTE);
        event.getBuilder().addMix(STRONG_HASTE, Items.REDSTONE, LONG_HASTE);
        event.getBuilder().addMix(LONG_HASTE, Items.GLOWSTONE_DUST, STRONG_HASTE);
    }
}
