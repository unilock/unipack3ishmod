package cc.unilock.unipack.mixin.spectrum;

import com.google.common.collect.Sets;
import de.dafuqs.spectrum.items.tools.MultiToolItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Set;

@Mixin(value = MultiToolItem.class, remap = false)
public abstract class MultiToolItemMixin implements IForgeItem {
	@Shadow public abstract boolean canTill(ItemStack stack);

	@Unique
	private static final Set<ToolAction> DEFAULT_TOOL_ACTIONS = Sets.newIdentityHashSet();
	@Unique
	private static final Set<ToolAction> SPECIAL_TOOL_ACTIONS = Sets.newIdentityHashSet();

	static {
		DEFAULT_TOOL_ACTIONS.add(ToolActions.AXE_DIG);
		DEFAULT_TOOL_ACTIONS.add(ToolActions.HOE_DIG);
		DEFAULT_TOOL_ACTIONS.add(ToolActions.PICKAXE_DIG);
		DEFAULT_TOOL_ACTIONS.add(ToolActions.SHOVEL_DIG);

		SPECIAL_TOOL_ACTIONS.add(ToolActions.SHOVEL_FLATTEN);
		SPECIAL_TOOL_ACTIONS.add(ToolActions.AXE_SCRAPE);
		SPECIAL_TOOL_ACTIONS.add(ToolActions.AXE_STRIP);
		SPECIAL_TOOL_ACTIONS.add(ToolActions.AXE_WAX_OFF);
		SPECIAL_TOOL_ACTIONS.add(ToolActions.HOE_TILL);
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
		if (DEFAULT_TOOL_ACTIONS.contains(toolAction)) {
			return true;
		} else if (canTill(stack) && SPECIAL_TOOL_ACTIONS.contains(toolAction)) {
			return true;
		} else {
			return IForgeItem.super.canPerformAction(stack, toolAction);
		}
	}
}
