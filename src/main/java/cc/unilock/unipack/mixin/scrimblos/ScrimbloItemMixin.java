package cc.unilock.unipack.mixin.scrimblos;

import cc.unilock.unipack.mixinsupport.GeckoLibSupport;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.function.Supplier;

@Mixin(targets = "flingus.scrimblos.item.ScrimbloItem")
@Pseudo
public class ScrimbloItemMixin {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lsoftware/bernie/geckolib/animatable/GeoItem;makeRenderer(Lsoftware/bernie/geckolib/animatable/GeoItem;)Ljava/util/function/Supplier;"))
	private Supplier<Object> init$makeRenderer(GeoItem item) {
		return GeckoLibSupport.makeRenderer(item);
	}
}
