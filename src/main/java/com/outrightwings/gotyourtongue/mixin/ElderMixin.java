package com.outrightwings.gotyourtongue.mixin;

import com.outrightwings.gotyourtongue.entity.Silencable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElderGuardian.class)
public class ElderMixin extends Guardian implements Silencable {
    private static final EntityDataAccessor<Boolean> SILENCED = SynchedEntityData.defineId(ElderGuardian.class, EntityDataSerializers.BOOLEAN);

    public ElderMixin(EntityType<? extends ElderGuardian> p_32460_, Level p_32461_) {
        super(p_32460_, p_32461_);
    }

    public boolean isSilenced(){return this.entityData.get(SILENCED);}
    public void setSilenced(boolean _s){this.entityData.set(SILENCED,_s);}

    protected void defineSynchedData(){
        super.defineSynchedData();
        this.entityData.define(SILENCED,false);
    }
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("gotyourtongue_silenced", isSilenced());
    }
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("gotyourtongue_silenced", 1)) {
            setSilenced(tag.getBoolean("gotyourtongue_silenced"));
        }
    }
    @Inject(method = "customServerAiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectUtil;addEffectToPlayersAround(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;DLnet/minecraft/world/effect/MobEffectInstance;I)Ljava/util/List;"),cancellable = true)
    private void aiMixin(CallbackInfo ci){
        if(isSilenced()) {
            ci.cancel();
        }
    }
}
