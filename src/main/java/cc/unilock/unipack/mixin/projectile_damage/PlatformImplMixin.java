package cc.unilock.unipack.mixin.projectile_damage;

import net.fabricmc.loader.api.FabricLoader;
import net.projectile_damage.forge.PlatformImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PlatformImpl.class, remap = false)
public class PlatformImplMixin {
	/**
	 * @author unilock
	 * @reason Fabric code on Forge
	 */
	@Overwrite
	public static boolean isModLoaded(String modid) {
		return FabricLoader.getInstance().isModLoaded(modid);
	}
}
