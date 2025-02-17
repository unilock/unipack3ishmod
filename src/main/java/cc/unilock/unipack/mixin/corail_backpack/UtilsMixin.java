package cc.unilock.unipack.mixin.corail_backpack;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.cursegame.minecraft.backpack.util.InventoryUtils;
import org.cursegame.minecraft.backpack.util.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.curios.api.CuriosCapability;

import java.util.Optional;

@Mixin(value = Utils.class, remap = false)
public class UtilsMixin {
	/**
	 * @author unilock
	 * @reason reflection? really?
	 */
	@Overwrite
	public static Optional<ItemStack> capItem(LivingEntity entity, TagKey<Item> criteria) {
		return entity.getCapability(CuriosCapability.INVENTORY).resolve().flatMap(curiosItemHandler -> curiosItemHandler.getCurios().values().stream().filter(curiosStacksHandler -> curiosStacksHandler instanceof ItemStackHandler).map(curiosStacksHandler -> (ItemStackHandler) curiosStacksHandler).map(itemStackHandler -> InventoryUtils.getFirstMatching(itemStackHandler, criteria)).findFirst());
	}
}
