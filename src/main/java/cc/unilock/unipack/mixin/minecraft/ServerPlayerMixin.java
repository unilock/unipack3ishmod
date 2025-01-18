package cc.unilock.unipack.mixin.minecraft;

import cc.unilock.unipack.PVPManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {
	@Inject(method = "canHarmPlayer", at = @At("HEAD"), cancellable = true)
	void canHarmPlayer(Player attacker, CallbackInfoReturnable<Boolean> cir) {
		ServerPlayer defender = (ServerPlayer) (Object) this;

		UUID defenderUuid = defender.getUUID();
		UUID attackerUuid = attacker.getUUID();

		if (defenderUuid.equals(attackerUuid)) {
			return;
		}

		boolean defenderPvpDisabled = !PVPManager.pvpWhitelist.isWhiteListed(defender.getGameProfile());
		boolean attackerPvpDisabled = !PVPManager.pvpWhitelist.isWhiteListed(attacker.getGameProfile());

		if (defenderPvpDisabled || attackerPvpDisabled) {
			cir.setReturnValue(false);
		}
	}
}
