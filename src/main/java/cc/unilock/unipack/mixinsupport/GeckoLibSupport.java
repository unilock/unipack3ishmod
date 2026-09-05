package cc.unilock.unipack.mixinsupport;

import com.google.common.base.Suppliers;
import net.minecraftforge.fml.loading.FMLLoader;
import software.bernie.geckolib.animatable.GeoItem;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class GeckoLibSupport {
	public static Supplier<Object> makeRenderer(GeoItem item) {
		if (FMLLoader.getDist().isDedicatedServer())
			return () -> null;

		return Suppliers.memoize(() -> {
			AtomicReference<Object> renderProvider = new AtomicReference<>();
			item.createRenderer(renderProvider::set);
			return renderProvider.get();
		});
	}
}
