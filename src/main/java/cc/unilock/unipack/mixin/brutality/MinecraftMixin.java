package cc.unilock.unipack.mixin.brutality;

import cc.unilock.unipack.UniPack;
import net.goo.brutality.network.PacketHandler;
import net.goo.brutality.network.ServerboundBetterCombatAttackStartListenerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow
	@Nullable
	public LocalPlayer player;

	@Shadow
	protected int missTime;

	@Inject(method = "continueAttack", at = @At("HEAD"))
	private void continueAttack(boolean leftClick, CallbackInfo ci) {
		if (!UniPack.BETTERCOMBAT && leftClick && this.missTime <= 0 && !this.player.isUsingItem()) {
			PacketHandler.sendToServer(new ServerboundBetterCombatAttackStartListenerPacket(this.player.getItemInHand(InteractionHand.MAIN_HAND), 0));
		}
	}

	@Inject(method = "startAttack", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/event/InputEvent$InteractionKeyMappingTriggered;shouldSwingHand()Z"))
	private void startAttack(CallbackInfoReturnable<Boolean> cir) {
		if (!UniPack.BETTERCOMBAT) {
			PacketHandler.sendToServer(new ServerboundBetterCombatAttackStartListenerPacket(this.player.getItemInHand(InteractionHand.MAIN_HAND), 0));
		}
	}
}
