package cc.unilock.unipack.mixin.create_destroy_tfmg;

import com.drmangotea.tfmg.base.TFMGPipes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.petrolpark.destroy.DestroyBlocks;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = FluidPropagator.class, remap = false)
public class FluidPropagatorMixin {
	@WrapOperation(method = "propagateChangedPipe(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At(value = "INVOKE", target = "Lcom/tterrag/registrate/util/entry/BlockEntry;has(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
	private static boolean propagateChangedPipe$has(BlockEntry<PumpBlock> instance, BlockState targetState, Operation<Boolean> original) {
		return original.call(instance, targetState)
				|| DestroyBlocks.CREATIVE_PUMP.has(targetState)
				|| TFMGPipes.ALUMINUM_MECHANICAL_PUMP.has(targetState) || TFMGPipes.BRASS_MECHANICAL_PUMP.has(targetState) || TFMGPipes.CAST_IRON_MECHANICAL_PUMP.has(targetState) || TFMGPipes.PLASTIC_MECHANICAL_PUMP.has(targetState) || TFMGPipes.STEEL_MECHANICAL_PUMP.has(targetState);
	}
}
