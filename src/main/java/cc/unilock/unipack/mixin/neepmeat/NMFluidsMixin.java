package cc.unilock.unipack.mixin.neepmeat;

import com.neep.neepmeat.init.NMFluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NMFluids.class, remap = false)
public class NMFluidsMixin {
	@Redirect(method = "initialise", at = @At(value = "INVOKE", target = "Lio/github/tropheusj/milk/Milk;enableCauldron()V"))
	private static void initialise$enableCauldron() {
		// NO-OP
	}

	@Redirect(method = "initialise", at = @At(value = "INVOKE", target = "Lio/github/tropheusj/milk/Milk;enableMilkFluid()V"))
	private static void initialise$enableMilkFluid() {
		// NO-OP
	}
}
