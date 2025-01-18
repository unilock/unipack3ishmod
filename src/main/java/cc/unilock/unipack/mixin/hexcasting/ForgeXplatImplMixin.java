package cc.unilock.unipack.mixin.hexcasting;

import at.petrak.hexcasting.forge.xplat.ForgeXplatImpl;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ForgeXplatImpl.class, remap = false)
public class ForgeXplatImplMixin {
	/**
	 * @author unilock
	 * @reason Fabric code on Forge
	 */
	@Overwrite
	public boolean isModPresent(String id) {
		return FabricLoader.getInstance().isModLoaded(id);
	}
}
