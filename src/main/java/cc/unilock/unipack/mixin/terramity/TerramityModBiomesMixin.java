package cc.unilock.unipack.mixin.terramity;

import net.mcreator.terramity.init.TerramityModBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TerramityModBiomes.class, remap = false)
public class TerramityModBiomesMixin {
	@Inject(method = "onServerAboutToStart", at = @At("HEAD"), cancellable = true)
	private static void onServerAboutToStart(CallbackInfo ci) {
		ci.cancel();
	}
}
