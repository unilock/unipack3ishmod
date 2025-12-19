package cc.unilock.unipack.mixin.tfmg;

import com.drmangotea.tfmg.content.decoration.pipes.TFMGPipes;
import com.drmangotea.tfmg.registry.TFMGBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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
		return original.call(instance, targetState) || TFMGPipes.TFMG_PIPES.get(TFMGPipes.PipeMaterial.ALUMINUM).get(3).has(targetState) || TFMGPipes.TFMG_PIPES.get(TFMGPipes.PipeMaterial.BRASS).get(3).has(targetState) || TFMGPipes.TFMG_PIPES.get(TFMGPipes.PipeMaterial.CAST_IRON).get(3).has(targetState) || TFMGPipes.TFMG_PIPES.get(TFMGPipes.PipeMaterial.PLASTIC).get(3).has(targetState) || TFMGPipes.TFMG_PIPES.get(TFMGPipes.PipeMaterial.STEEL).get(3).has(targetState) || TFMGBlocks.ELECTRIC_PUMP.has(targetState);
	}
}
