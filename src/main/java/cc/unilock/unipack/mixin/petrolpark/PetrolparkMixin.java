package cc.unilock.unipack.mixin.petrolpark;

import com.petrolpark.Petrolpark;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Petrolpark.class, remap = false)
public class PetrolparkMixin {
	@Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/petrolpark/PetrolparkItemDisplayContexts;register()V", shift = At.Shift.AFTER), cancellable = true)
	private static void clinit$preMENU(CallbackInfo ci) {
		ci.cancel();
	}
}
