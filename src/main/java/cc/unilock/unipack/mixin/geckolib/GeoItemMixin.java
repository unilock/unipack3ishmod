package cc.unilock.unipack.mixin.geckolib;

import dev.su5ed.sinytra.connectorextras.geckolibcompat.GeckolibFabricCompatSetup;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(value = GeoItem.class, remap = false)
public interface GeoItemMixin extends SingletonGeoAnimatable {
	@Override
	default void createRenderer(Consumer<Object> consumer) {
		consumer.accept(GeckolibFabricCompatSetup.get((ItemLike) this));
	}

	@Override
	default Supplier<Object> getRenderProvider() {
		return () -> GeckolibFabricCompatSetup.get((ItemLike) this);
	}
}
