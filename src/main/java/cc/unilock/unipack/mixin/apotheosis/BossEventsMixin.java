package cc.unilock.unipack.mixin.apotheosis;

import com.llamalad7.mixinextras.sugar.Local;
import dev.shadowsoffire.apotheosis.adventure.boss.BossEvents;
import net.minecraft.world.entity.Mob;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BossEvents.class, remap = false)
public class BossEventsMixin {
	@Inject(method = "naturalBosses", at = @At(value = "FIELD", target = "Ldev/shadowsoffire/apotheosis/adventure/AdventureConfig;bossAutoAggro:Z", opcode = Opcodes.GETSTATIC), cancellable = true)
	private void naturalBosses$createBoss(CallbackInfo ci, @Local Mob boss) {
		if (boss == null) {
			ci.cancel();
		}
	}
}
