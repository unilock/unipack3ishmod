package cc.unilock.unipack.mixin.spectrum;

import com.google.common.collect.Sets;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Set;

@Mixin(targets = "de.dafuqs.spectrum.items.tools.MultiToolItem")
@Pseudo
public abstract class MultiToolItemMixin extends DiggerItem {
	public MultiToolItemMixin(float attackDamageModifier, float attackSpeedModifier, Tier tier, TagKey<Block> blocks, Properties properties) {
		super(attackDamageModifier, attackSpeedModifier, tier, blocks, properties);
		throw new IllegalStateException("MultiToolItemMixin instantiated!?");
	}

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
			return super.canPerformAction(stack, toolAction);
		}
	}
}
