package cc.unilock.unipack.mixin.fabric_transfer_api_v1;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ItemStorage.class, remap = false)
public class ItemStorageMixin {
	@ModifyExpressionValue(method = "lambda$static$2", at = @At(value = "INVOKE", target = "Ljava/lang/ThreadLocal;get()Ljava/lang/Object;"))
	private static Object lambda$getComputingCapabilityLock(Object original, @Local(argsOnly = true) BlockState state, @Local(argsOnly = true) BlockEntity blockEntity) {
		String namespace = null;
		if (blockEntity != null) {
			ResourceLocation rl = ForgeRegistries.BLOCK_ENTITY_TYPES.getKey(blockEntity.getType());
			if (rl != null) {
				namespace = rl.getNamespace();
			}
		}
		return (Boolean) original && (state.is(Blocks.COMPOSTER) || "minecraft".equals(namespace));
	}
}
