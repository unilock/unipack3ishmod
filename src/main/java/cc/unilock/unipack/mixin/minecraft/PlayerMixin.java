package cc.unilock.unipack.mixin.minecraft;

import cc.unilock.unipack.command.PVPCommand;
import com.mojang.authlib.GameProfile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
	@Shadow public abstract GameProfile getGameProfile();

	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
		throw new IllegalStateException("PlayerMixin instantiated!?");
	}

	@Inject(method = "canHarmPlayer", at = @At("HEAD"), cancellable = true)
	private void canHarmPlayer(Player other, CallbackInfoReturnable<Boolean> cir) {
		if (this.is(other)) {
			return;
		}

		boolean thisPvpDisabled = !PVPCommand.pvpWhitelist.isWhiteListed(this.getGameProfile());
		boolean otherPvpDisabled = !PVPCommand.pvpWhitelist.isWhiteListed(other.getGameProfile());

		if (thisPvpDisabled || otherPvpDisabled) {
			cir.setReturnValue(false);
		}
	}
}
