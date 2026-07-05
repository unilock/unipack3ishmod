package cc.unilock.unipack.mixinsupport;

import com.google.common.base.Suppliers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class GeckoLibSupport {
	public static Supplier<Object> makeRenderer(GeoItem item) {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
			return () -> null;

		return Suppliers.memoize(() -> {
			AtomicReference<Object> renderProvider = new AtomicReference<>();
			item.createRenderer(renderProvider::set);
			return renderProvider.get();
		});
	}
}
