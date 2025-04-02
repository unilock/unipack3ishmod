package cc.unilock.unipack;

import cc.unilock.unipack.command.DumpRegistriesCommand;
import cc.unilock.unipack.command.PVPCommand;
import com.mojang.logging.LogUtils;
import net.gigabit101.shrink.items.ShrinkItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
import wayoftime.bloodmagic.gson.Serializers;

@Mod(UniPack.MOD_ID)
public class UniPack {
    public static final String MOD_ID = "unipack";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATA_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, MOD_ID);

    public UniPack() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ENTITY_DATA_SERIALIZERS.register(modEventBus);
        if (ModList.get().isLoaded("bloodmagic")) {
            ENTITY_DATA_SERIALIZERS.register("will_type", () -> Serializers.WILL_TYPE_SERIALIZER);
        }
        if (ModList.get().isLoaded("shrink")) {
            MinecraftForge.EVENT_BUS.addListener(this::itemTooltip);
        }
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);

        ForgeMod.enableMilkFluid();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("unilock was here");
    }

    private void itemTooltip(final ItemTooltipEvent event) {
        if (event.getItemStack().is(ShrinkItems.SHRINKING_DEVICE.get())) {
            event.getToolTip().add(Component.literal("doesn't require power").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        DumpRegistriesCommand.registerCommand(event.getDispatcher());
        PVPCommand.registerCommand(event.getDispatcher());
    }
}
