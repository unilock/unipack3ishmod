package cc.unilock.unipack.mixin.bloodmagic;

import cc.unilock.unipack.UniPack;
import net.minecraft.network.syncher.EntityDataSerializer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;
import wayoftime.bloodmagic.gson.Serializers;

@Mixin(value = Serializers.class, remap = false)
public class SerializersMixin {
	@Shadow @Final public static EntityDataSerializer<EnumDemonWillType> WILL_TYPE_SERIALIZER;

	@Inject(method = "<clinit>", at = @At("HEAD"), cancellable = true)
	private static void registerProperly(CallbackInfo ci) {
		UniPack.ENTITY_DATA_SERIALIZERS.register("will_type", () -> WILL_TYPE_SERIALIZER);
		ci.cancel();
	}
}
