package cc.unilock.unipack.mixin.corail_backpack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.cursegame.minecraft.backpack.util.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.Optional;

@Mixin(value = Utils.class, remap = false)
public class UtilsMixin {
	/**
	 * @author unilock
	 * @reason reflection? really?
	 */
	@Overwrite
	public static Optional<ItemStack> capItem(LivingEntity entity, TagKey<Item> criteria) {
		return CuriosApi.getCuriosInventory(entity).resolve().map(curiosItemHandler -> {
			for (ICurioStacksHandler curioStacksHandler : curiosItemHandler.getCurios().values()) {
				IDynamicStackHandler dynamicStackHandler = curioStacksHandler.getStacks();
				for (int i = 0; i < dynamicStackHandler.getSlots(); i++) {
					ItemStack stack = dynamicStackHandler.getStackInSlot(i);
					if (stack.is(criteria)) {
						return stack;
					}
				}
			}
			return null;
		});
	}
}
