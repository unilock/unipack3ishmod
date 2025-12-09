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
	@Inject(method = "doStatic", at = @At(value = "INVOKE", target = "Lat/petrak/hexcasting/api/utils/NBTHelper;putString(Lnet/minecraft/world/item/ItemStack;Ljava/lang/String;Ljava/lang/String;)V"), cancellable = true)
	private static void doStatic(CallbackInfoReturnable<ItemStack> cir, @Local HexPattern pat) {
		if (pat == null) cir.setReturnValue(ItemStack.EMPTY);
	}
}
