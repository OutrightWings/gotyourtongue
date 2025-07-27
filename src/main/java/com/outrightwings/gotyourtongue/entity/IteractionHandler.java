package com.outrightwings.gotyourtongue.entity;

import com.outrightwings.gotyourtongue.Main;
import com.outrightwings.gotyourtongue.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Random;

@EventBusSubscriber(modid = Main.MODID)
public class IteractionHandler {
    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event){
        if(event.getLevel().isClientSide) return;
        if(event.getTarget() instanceof ElderGuardian entity && entity instanceof Silencable mixinType){
            ServerLevel level = (ServerLevel) event.getLevel();
            //Silence him!
            if(event.getItemStack().getItem() == Items.GLASS_BOTTLE && !mixinType.isSilenced()){
                mixinType.setSilenced(true);

                event.getItemStack().shrink(1);
                ItemStack give = new ItemStack(ModItems.BOTTLED_FEAR.get());
                Player player = event.getEntity();
                if(!player.getInventory().add(give)){
                    player.drop(give,false);
                }

                //Look fancy
                level.sendParticles(ParticleTypes.ANGRY_VILLAGER,
                        entity.getX(),entity.getY()+1,entity.getZ(), //pos
                        10, // count
                        1, 1, 1, //spread
                        0.05 //speed
                );
                level.playSound(null,
                        entity.blockPosition(),
                        SoundEvents.BOTTLE_FILL,
                        SoundSource.PLAYERS
                );

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
            //Feed him!
            else if(event.getItemStack().getItem() == Items.COD && mixinType.isSilenced()){
                Random r = new Random();
                float chanceToRestore = r.nextFloat()*4;
                //Success
                if(chanceToRestore < 1){
                    mixinType.setSilenced(false);

                    //Look fancy
                    level.sendParticles(ParticleTypes.WAX_ON,
                            entity.getX(),entity.getY()+1,entity.getZ(), //pos
                            10, // count
                            2, 2, 2, //spread
                            0.05 //speed
                    );
                }
                //Fail
                else {
                    level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                            entity.getX(),entity.getY()+1,entity.getZ(), //pos
                            10, // count
                            2, 2, 2, //spread
                            0.05 //speed
                    );

                }
                level.playSound(null,
                        entity.blockPosition(),
                        SoundEvents.DOLPHIN_EAT,
                        SoundSource.HOSTILE
                );
                event.getItemStack().shrink(1);
            }
        }
    }
}
