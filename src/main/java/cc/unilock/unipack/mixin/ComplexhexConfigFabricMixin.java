package cc.unilock.unipack.mixin;

import dev.kineticcat.complexhex.fabric.ComplexhexConfigFabric;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ComplexhexConfigFabric.class, remap = false)
public class ComplexhexConfigFabricMixin {
	/**
	 * @author unilock
	 * @reason Fabric code on Forge
	 */
	@Redirect(method = "init", at = @At(value = "INVOKE", target = "Ldev/architectury/platform/Platform;getEnv()Lnet/fabricmc/api/EnvType;"))
	private static EnvType getEnv() {
		return FabricLoader.getInstance().getEnvironmentType();
	}
}
