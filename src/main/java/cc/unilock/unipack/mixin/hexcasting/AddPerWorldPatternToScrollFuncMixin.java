package cc.unilock.unipack.mixin.hexcasting;

import at.petrak.hexcasting.api.casting.math.HexPattern;
import at.petrak.hexcasting.common.loot.AddPerWorldPatternToScrollFunc;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AddPerWorldPatternToScrollFunc.class, remap = false)
public class AddPerWorldPatternToScrollFuncMixin {
	@Inject(method = "doStatic(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/storage/loot/LootContext;)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "NEW", target = "()Lnet/minecraft/nbt/CompoundTag;"), cancellable = true)
	private static void doStatic(CallbackInfoReturnable<ItemStack> cir, @Local HexPattern pat) {
		if (pat == null) cir.setReturnValue(ItemStack.EMPTY);
	}
}
