package cc.unilock.unipack.mixin.spectrum;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "de.dafuqs.spectrum.deeper_down.DarknessEffects")
@Pseudo
public class DarknessEffectsMixin {
	@Inject(method = "clientTick", at = @At("HEAD"), cancellable = true)
	private static void clientTick(CallbackInfo ci) {
		ci.cancel();
	}
}
