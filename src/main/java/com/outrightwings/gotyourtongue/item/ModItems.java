package com.outrightwings.gotyourtongue.item;

import com.outrightwings.gotyourtongue.Main;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public static final RegistryObject<Item> BOTTLED_FEAR = ITEMS.register("bottled_fear",() -> new Item((new Item.Properties().stacksTo(16))));

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Potion> FATIGUE = POTIONS.register("fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,3600,1)));
    public static final RegistryObject<Potion> LONG_FATIGUE = POTIONS.register("long_fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,9600,1)));
    public static final RegistryObject<Potion> STRONG_FATIGUE = POTIONS.register("strong_fatigue",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,3600,2)));

    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,3600)));
    public static final RegistryObject<Potion> LONG_HASTE = POTIONS.register("long_haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,9600)));
    public static final RegistryObject<Potion> STRONG_HASTE = POTIONS.register("strong_haste",() -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED,3600,1)));

    public static void registerPotionRecipes(){
        PotionBrewing.addMix(Potions.AWKWARD,BOTTLED_FEAR.get(), FATIGUE.get());

        PotionBrewing.addMix(FATIGUE.get(), Items.REDSTONE, LONG_FATIGUE.get());
        PotionBrewing.addMix(FATIGUE.get(), Items.GLOWSTONE, STRONG_FATIGUE.get());

        PotionBrewing.addMix(STRONG_FATIGUE.get(), Items.REDSTONE, LONG_FATIGUE.get());
        PotionBrewing.addMix(LONG_FATIGUE.get(), Items.GLOWSTONE_DUST, STRONG_FATIGUE.get());

        PotionBrewing.addMix(HASTE.get(), Items.FERMENTED_SPIDER_EYE, FATIGUE.get());
        PotionBrewing.addMix(FATIGUE.get(), Items.FERMENTED_SPIDER_EYE, HASTE.get());
        PotionBrewing.addMix(STRONG_FATIGUE.get(), Items.FERMENTED_SPIDER_EYE, STRONG_HASTE.get());
        PotionBrewing.addMix(LONG_FATIGUE.get(), Items.FERMENTED_SPIDER_EYE, LONG_HASTE.get());
        PotionBrewing.addMix(STRONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, STRONG_FATIGUE.get());
        PotionBrewing.addMix(LONG_HASTE.get(), Items.FERMENTED_SPIDER_EYE, LONG_FATIGUE.get());

        PotionBrewing.addMix(STRONG_HASTE.get(), Items.REDSTONE, LONG_HASTE.get());
        PotionBrewing.addMix(LONG_HASTE.get(), Items.GLOWSTONE_DUST, STRONG_HASTE.get());
    }
}
