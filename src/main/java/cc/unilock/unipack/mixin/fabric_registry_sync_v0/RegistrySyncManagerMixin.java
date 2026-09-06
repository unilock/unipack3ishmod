package cc.unilock.unipack.mixin.fabric_registry_sync_v0;

import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = RegistrySyncManager.class, remap = false)
public class RegistrySyncManagerMixin {
	@ModifyConstant(method = "receivePacket", constant = @Constant(longValue = 30L))
	private static long receivePacket$modifyTimeout(long constant) {
		return 240L;
	}
}
