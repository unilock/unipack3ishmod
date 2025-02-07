package cc.unilock.unipack.mixin.neepmeat;

import com.neep.neepmeat.NeepMeat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NeepMeat.class, remap = false)
public class NeepMeatMixin {
	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/neep/neepmeat/datagen/NMRecipeGenerator;init()V"))
	private void onInitialize$recipeDataInit() {
		// NO-OP
	}

	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/neep/neepmeat/datagen/NMItemTagProvider;init()V"))
	private void onInitialize$itemTagDataInit() {
		// NO-OP
	}

	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/neep/neepmeat/datagen/NMBlockTagProvider;init()V"))
	private void onInitialize$blockTagDataInit() {
		// NO-OP
	}

	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/neep/neepmeat/datagen/NMAdvancements;init()V"))
	private void onInitialize$advancementDataInit() {
		// NO-OP
	}

	@Redirect(method = "onInitialize", at = @At(value = "INVOKE", target = "Lcom/neep/neepmeat/client/datagen/NMModelProvider;init()V"))
	private void onInitialize$modelDataInit() {
		// NO-OP
	}
}
