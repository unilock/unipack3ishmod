package cc.unilock.unipack.module;

import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "unipack", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class Mc122477Fix {
	private boolean freeze = false;
	private byte ticks = 0;

	@SubscribeEvent
	public void opening(final ScreenEvent.Opening event) {
		if (event.getNewScreen() instanceof ChatScreen) {
			freeze = true;
			ticks = 0;
		}
	}
	
	@SubscribeEvent
	public void closing(final ScreenEvent.Closing event) {
		if (event.getScreen() instanceof ChatScreen) {
			freeze = false;
			ticks = 0;
		}
	}

	@SubscribeEvent
	public void renderTick(TickEvent.RenderTickEvent event) {
		if (freeze && event.phase == TickEvent.Phase.END) {
			if (++ticks > 2) {
				freeze = false;
			}
		}
	}

	@SubscribeEvent
	public void characterTypedPre(final ScreenEvent.CharacterTyped.Pre event) {
		if (freeze) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public void keyPressedPre(final ScreenEvent.KeyPressed.Pre event) {
		if (freeze) {
			event.setCanceled(true);
		}
	}
}
