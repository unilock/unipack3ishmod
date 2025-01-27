package cc.unilock.unipack;

import cc.unilock.unipack.command.DumpRegistriesCommand;
import cc.unilock.unipack.command.PVPCommand;
import com.mojang.logging.LogUtils;
import net.gigabit101.shrink.items.ShrinkItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.UUID;

@Mod(UniPack.MOD_ID)
public class UniPack {
    public static final String MOD_ID = "unipack";
    public static final Logger LOGGER = LogUtils.getLogger();

    public UniPack() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.addListener(this::itemTooltip);
        MinecraftForge.EVENT_BUS.addListener(this::livingHurt);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("unilock was here");
    }

    private void itemTooltip(final ItemTooltipEvent event) {
        if (event.getItemStack().is(ShrinkItems.SHRINKING_DEVICE.get())) {
            event.getToolTip().add(Component.literal("doesn't require power").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }

    private void livingHurt(final LivingHurtEvent event) {
        if (event.getEntity() instanceof Player defender && event.getSource().getEntity() instanceof Player attacker) {
            UUID defenderUuid = defender.getUUID();
            UUID attackerUuid = attacker.getUUID();

            if (defenderUuid.equals(attackerUuid)) {
                return;
            }

            boolean defenderPvpDisabled = !PVPCommand.pvpWhitelist.isWhiteListed(defender.getGameProfile());
            boolean attackerPvpDisabled = !PVPCommand.pvpWhitelist.isWhiteListed(attacker.getGameProfile());

            if (defenderPvpDisabled || attackerPvpDisabled) {
                event.setCanceled(true);
            }
        }
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        DumpRegistriesCommand.registerCommand(event.getDispatcher());
        PVPCommand.registerCommand(event.getDispatcher());
    }
}
