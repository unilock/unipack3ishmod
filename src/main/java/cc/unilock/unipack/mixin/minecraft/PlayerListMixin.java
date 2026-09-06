package cc.unilock.unipack.mixin.minecraft;

import cc.unilock.unipack.UniPack;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateTagsPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerList.class)
public class PlayerListMixin {
	@WrapOperation(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V"))
	private void placeNewPlayer$send(ServerGamePacketListenerImpl instance, Packet<?> packet, Operation<Void> original) {
		original.call(instance, packet);

		if (packet instanceof ClientboundUpdateRecipesPacket pkt) {
			UniPack.LOGGER.info("Sent {} recipes to client", pkt.getRecipes().size());
		}

		if (packet instanceof ClientboundUpdateTagsPacket pkt) {
			UniPack.LOGGER.info("Sent {} tags to client", pkt.getTags().size());
		}
	}
}
