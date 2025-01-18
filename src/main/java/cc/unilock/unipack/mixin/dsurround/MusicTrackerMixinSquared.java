package cc.unilock.unipack.mixin.dsurround;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.sounds.MusicManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = MusicManager.class, priority = 1500)
public class MusicTrackerMixinSquared {
	@TargetHandler(
		mixin = "org.orecruncher.dsurround.mixins.audio.MixinMusicManager",
		name = "dsurround_startPlaying"
	)
	@Redirect(method = "@MixinSquared:Handler", at = @At(value = "INVOKE", target = "Lorg/orecruncher/dsurround/lib/logging/IModLog;info(Ljava/lang/String;[Ljava/lang/Object;)V", remap = false))
	private void dsurround_startPlaying(@Coerce Object instance, String msg, Object... params) {
		// NO-OP
	}
}
