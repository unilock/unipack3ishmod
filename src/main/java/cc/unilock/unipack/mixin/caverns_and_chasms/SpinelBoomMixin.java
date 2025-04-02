package cc.unilock.unipack.mixin.caverns_and_chasms;

import com.teamabnormals.caverns_and_chasms.common.level.SpinelBoom;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SpinelBoom.class, remap = false)
public class SpinelBoomMixin {
	@Unique
	private RandomSource random;

	@Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;DDDFLnet/minecraft/world/level/Explosion$BlockInteraction;)V", at = @At("TAIL"))
	private void initRandom(CallbackInfo ci) {
		random = RandomSource.create();
	}

	@Redirect(method = "explode", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;random:Lnet/minecraft/util/RandomSource;", opcode = Opcodes.GETFIELD))
	private RandomSource redirectExplodeRandom(Level instance) {
		return this.random;
	}

	@Redirect(method = "finalizeExplosion", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/Level;random:Lnet/minecraft/util/RandomSource;", opcode = Opcodes.GETFIELD))
	private RandomSource redirectFinalizeExplosionRandom(Level instance) {
		return this.random;
	}
}
