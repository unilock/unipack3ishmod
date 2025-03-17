package cc.unilock.unipack.mixin.minecraft;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SnowyDirtBlock.class)
public class SnowyDirtBlockMixin extends Block {
	public SnowyDirtBlockMixin(Properties properties) {
		super(properties);
		throw new IllegalStateException("SnowyDirtBlockMixin instantiated!?");
	}

	@Override
	public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
		if ((state.is(Blocks.MYCELIUM) || state.is(Blocks.PODZOL)) && toolAction.equals(ToolActions.HOE_TILL) && context.getLevel().getBlockState(context.getClickedPos().above()).isAir()) {
			return Blocks.FARMLAND.defaultBlockState();
		}
		return super.getToolModifiedState(state, context, toolAction, simulate);
	}
}
