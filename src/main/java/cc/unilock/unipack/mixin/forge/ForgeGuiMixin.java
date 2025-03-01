package cc.unilock.unipack.mixin.forge;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Mixin(ForgeGui.class)
public class ForgeGuiMixin {
	@Unique
	private static final Method renderAzureDikeMethod;

	static {
		try {
			renderAzureDikeMethod = Class.forName("de.dafuqs.spectrum.render.HudRenderers").getDeclaredMethod("renderAzureDike", GuiGraphics.class, Player.class, int.class, int.class);
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			throw new RuntimeException("Failed to reflect Spectrum's HudRenderers#renderAzureDike", e);
		}
	}

	@WrapOperation(method = "renderHealth(IILnet/minecraft/client/gui/GuiGraphics;)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeGui;renderHearts(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIIIFIIIZ)V"))
	private void renderAzureDike(ForgeGui instance, GuiGraphics guiGraphics, Player player, int left, int top, int rowHeight, int regen, float healthMax, int health, int healthLast, int absorb, boolean highlight, Operation<Void> original) {
		original.call(instance, guiGraphics, player, left, top, rowHeight, regen, healthMax, health, healthLast, absorb, highlight);
		try {
			renderAzureDikeMethod.invoke(null, guiGraphics, player, left, top);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Failed to invoke Spectrum's HudRenderers#renderAzureDike", e);
		}
	}
}
