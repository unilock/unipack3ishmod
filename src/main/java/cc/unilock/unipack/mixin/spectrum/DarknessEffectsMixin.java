package cc.unilock.unipack.mixin.spectrum;

import de.dafuqs.spectrum.deeper_down.DarknessEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DarknessEffects.class, remap = false)
public class DarknessEffectsMixin {
	@Inject(method = "clientTick", at = @At("HEAD"), cancellable = true)
	private static void clientTick(CallbackInfo ci) {
		ci.cancel();
	}
}
