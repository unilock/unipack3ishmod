package cc.unilock.unipack.mixin.estrogen;

import dev.mayaqq.estrogen.forge.client.EstrogenForgeClient;
import net.minecraftforge.client.event.ModelEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EstrogenForgeClient.class, remap = false)
public class EstrogenForgeClientMixin {
	@Inject(method = "modifyBakeResult", at = @At("HEAD"), cancellable = true)
	private void modifyBakeResult(ModelEvent.ModifyBakingResult event, CallbackInfo ci) {
		ci.cancel();
	}
}
