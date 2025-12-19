package cc.unilock.unipack;

import com.bawnorton.mixinsquared.api.MixinCanceller;

import java.util.List;
import java.util.Set;

public class UniPackMixinCanceller implements MixinCanceller {
	private static final Set<String> CANCEL = Set.of(
			"com.drmangotea.tfmg.mixin.FluidPropagatorMixin",
			"de.dafuqs.spectrum.mixin.client.BackgroundRendererMixin",
			"de.dafuqs.spectrum.mixin.client.GameRendererMixin",
			"de.dafuqs.spectrum.mixin.client.LightmapTextureManagerMixin",
			"de.dafuqs.spectrum.mixin.client.RenderSystemMixin"
	);

	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return CANCEL.contains(mixinClassName);
	}
}
