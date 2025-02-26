package cc.unilock.unipack.mixin.minecraft;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Shadow @Final private Map<MobEffect, MobEffectInstance> activeEffects;

	@Inject(method = "hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z", at = @At("HEAD"), cancellable = true)
	private void hasEffect(MobEffect effect, CallbackInfoReturnable<Boolean> cir) {
		if (this.activeEffects == null) cir.setReturnValue(false);
	}
}
