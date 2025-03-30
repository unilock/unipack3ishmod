package cc.unilock.unipack.mixin.bloodmagic;

import net.minecraft.network.syncher.EntityDataSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import wayoftime.bloodmagic.gson.Serializers;

@Mixin(Serializers.class)
public class SerializersMixin {
	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/syncher/EntityDataSerializers;registerSerializer(Lnet/minecraft/network/syncher/EntityDataSerializer;)V"))
	private static void cancelSerializerRegistration(EntityDataSerializer<?> serializer) {
		// NO-OP
	}
}
