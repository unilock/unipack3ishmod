package cc.unilock.unipack.mixin.create;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = FilteringBehaviour.class, remap = false)
public class FilteringBehaviourMixin {
	@WrapOperation(method = "createBoard(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/BlockHitResult;)Lcom/simibubi/create/foundation/blockEntity/behaviour/ValueSettingsBoard;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getMaxStackSize()I", remap = true))
	private int createBoard$getMaxStackSize(ItemStack instance, Operation<Integer> original) {
		return Math.min(original.call(instance), 64);
	}
}
