package cc.unilock.unipack;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

@Mod(UniPack.MOD_ID)
public class UniPack {
    public static final String MOD_ID = "unipack";
    private static final Logger LOGGER = LogUtils.getLogger();

    public UniPack(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("unilock was here");
    }
}
