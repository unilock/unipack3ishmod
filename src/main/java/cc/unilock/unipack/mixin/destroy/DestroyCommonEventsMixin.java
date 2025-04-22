package cc.unilock.unipack.mixin.destroy;

import com.petrolpark.destroy.core.event.DestroyCommonEvents;
import net.minecraftforge.event.TickEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DestroyCommonEvents.class, remap = false)
public class DestroyCommonEventsMixin {
	@Inject(method = "Lcom/petrolpark/destroy/core/event/DestroyCommonEvents;onLevelTick(Lnet/minecraftforge/event/TickEvent$LevelTickEvent;)V", at = @At("HEAD"), cancellable = true)
	private static void onTick(TickEvent.LevelTickEvent event, CallbackInfo ci) {
		if (event.side.isClient()) ci.cancel();
	}
}
