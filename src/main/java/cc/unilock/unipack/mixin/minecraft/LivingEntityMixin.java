package cc.unilock.unipack.mixin.minecraft;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;

	// PAIN
	@WrapMethod(method = "m_21023_(Lnet/minecraft/world/effect/MobEffect;)Z", remap = false)
	private boolean hasEffect(MobEffect effect, Operation<Boolean> original) {
		return this.activeEffects != null && original.call(effect);
	}
}
