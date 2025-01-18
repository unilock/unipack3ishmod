package cc.unilock.unipack.mixin.apotheosis;

import com.llamalad7.mixinextras.sugar.Local;
import dev.shadowsoffire.apotheosis.adventure.boss.ApothBoss;
import net.minecraft.world.entity.Mob;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ApothBoss.class, remap = false)
public class ApothBossMixin {
	@Inject(method = "createBoss(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;FLdev/shadowsoffire/apotheosis/adventure/loot/LootRarity;)Lnet/minecraft/world/entity/Mob;", at = @At(value = "FIELD", target = "Ldev/shadowsoffire/apotheosis/adventure/boss/ApothBoss;nbt:Lnet/minecraft/nbt/CompoundTag;", opcode = Opcodes.GETFIELD, ordinal = 2), cancellable = true)
	private void createBoss$loadEntityRecursive(CallbackInfoReturnable<Mob> cir, @Local Mob entity) {
		if (entity == null) {
			cir.setReturnValue(null);
		}
	}
}
