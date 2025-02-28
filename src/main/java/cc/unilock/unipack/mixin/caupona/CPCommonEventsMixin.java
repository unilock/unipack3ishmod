package cc.unilock.unipack.mixin.caupona;

import com.teammoeg.caupona.CPCommonEvents;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CPCommonEvents.class, remap = false)
public class CPCommonEventsMixin {
	@Inject(method = "onBlockClick(Lnet/minecraftforge/event/entity/player/PlayerInteractEvent$RightClickBlock;)V", at = @At("HEAD"), cancellable = true)
	private static void onBlockClick(PlayerInteractEvent.RightClickBlock event, CallbackInfo ci) {
		if (event.getSide().isClient()) ci.cancel();
	}
}
