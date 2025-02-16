package cc.unilock.unipack.mixin.enigmaticlegacy;

import com.aizistral.enigmaticlegacy.items.CursedRing;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.curios.api.SlotContext;

@Mixin(value = CursedRing.class, remap = false)
public class CursedRingMixin {
	/**
	 * @author unilock
	 * @reason get bent
	 */
	@Overwrite
	public void curioTick(SlotContext context, ItemStack stack) {
		// NO-OP
	}
}
