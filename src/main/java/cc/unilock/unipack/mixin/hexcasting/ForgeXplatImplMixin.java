package cc.unilock.unipack.mixin.hexcasting;

import at.petrak.hexcasting.forge.xplat.ForgeXplatImpl;
import net.minecraftforge.fml.loading.LoadingModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = ForgeXplatImpl.class, remap = false)
public class ForgeXplatImplMixin {
	/**
	 * @author unilock
	 * @reason Works pre-Forge mod init
	 */
	@Overwrite
	public boolean isModPresent(String id) {
		return LoadingModList.get().getModFileById(id) != null;
	}
}
