package cc.unilock.unipack;

import com.bawnorton.mixinsquared.canceller.MixinCancellerRegistrar;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class UniPackMixin implements IMixinConfigPlugin {
	@Override
	public void onLoad(String mixinPackage) {
		MixinCancellerRegistrar.register(new UniPackMixinCanceller());
	}

	@Override
	public String getRefMapperConfig() {
		// NO-OP
		return null;
	}

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		String id = mixinClassName.replace("cc.unilock.unipack.mixin.", "").split("\\.")[0];

		return FabricLoader.getInstance().isModLoaded(id);
	}

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
		// NO-OP
	}

	@Override
	public List<String> getMixins() {
		// NO-OP
		return null;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
		// NO-OP
	}
}
