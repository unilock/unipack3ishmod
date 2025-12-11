package cc.unilock.unipack.mixin.terramity;

import cc.unilock.unipack.mixinsupport.TerramitySupport;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {
	@Inject(method = "addBiomes", at = @At("HEAD"))
	private void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
		consumer.accept(Pair.of(new Climate.ParameterPoint(Climate.Parameter.span(0.7F, 1.0F), Climate.Parameter.span(-0.5F, 0.6F), Climate.Parameter.span(0.25F, 0.5F), Climate.Parameter.span(-0.3F, 0.3F), Climate.Parameter.span(0.2F, 0.9F), Climate.Parameter.span(-0.8F, 1.0F), 0L), TerramitySupport.SCORCHED_CAVES));
	}
}
