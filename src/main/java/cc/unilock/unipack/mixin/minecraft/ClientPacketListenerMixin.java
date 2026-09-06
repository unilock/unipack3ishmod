package cc.unilock.unipack.mixin.minecraft;

import cc.unilock.unipack.UniPack;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateTagsPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
	@Inject(method = "handleUpdateRecipes", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V", shift = At.Shift.AFTER))
	private void handleUpdateRecipes$ensureRunningOnSameThread$after(ClientboundUpdateRecipesPacket packet, CallbackInfo ci) {
		UniPack.LOGGER.info("Received {} recipes from server", packet.getRecipes().size());
	}

	@Inject(method = "handleUpdateTags", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/util/thread/BlockableEventLoop;)V", shift = At.Shift.AFTER))
	private void handleUpdateTags$ensureRunningOnSameThread$after(ClientboundUpdateTagsPacket packet, CallbackInfo ci) {
		UniPack.LOGGER.info("Received {} tags from server", packet.getTags().size());
	}
}
