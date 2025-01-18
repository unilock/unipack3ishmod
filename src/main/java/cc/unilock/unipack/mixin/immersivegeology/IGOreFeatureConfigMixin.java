package cc.unilock.unipack.mixin.immersivegeology;

import com.igteam.immersivegeology.common.world.IGOreFeature;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = IGOreFeature.IGOreFeatureConfig.class, remap = false)
public class IGOreFeatureConfigMixin {
	@WrapMethod(method = "getStateToGenerate")
	private BlockState getStateToGenerate(BlockState stoneState, RandomSource random, IGOreFeature.IGOreFeatureConfig config, double noiseValue, Operation<BlockState> original) {
		try {
			return original.call(stoneState, random, config, noiseValue);
		} catch (NullPointerException ignored) {
			return null;
		}
	}
}
