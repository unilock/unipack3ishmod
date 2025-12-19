package cc.unilock.unipack.mixin.projectile_damage;

import net.minecraftforge.fml.loading.LoadingModList;
import net.projectile_damage.forge.PlatformImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = PlatformImpl.class, remap = false)
public class PlatformImplMixin {
	/**
	 * @author unilock
	 * @reason Works pre-Forge mod init
	 */
	@Overwrite
	public static boolean isModLoaded(String modid) {
		return LoadingModList.get().getModFileById(modid) != null;
	}
}
