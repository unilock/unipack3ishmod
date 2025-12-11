package cc.unilock.unipack.mixin.terramity;

import cc.unilock.unipack.mixin.terramity.accessor.NoiseGeneratorSettingsAccessor;
import cc.unilock.unipack.mixinsupport.TerramitySupport;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
	@Shadow
	@Final
	private Map<ResourceKey<Level>, ServerLevel> levels;

	@Inject(method = "createLevels", at = @At("RETURN"))
	private void createLevels(ChunkProgressListener listener, CallbackInfo ci, @Local Registry<LevelStem> registry) {
		for (Level level : this.levels.values()) {
			if (Level.OVERWORLD.equals(level.dimension())) {
				LevelStem ow = registry.get(LevelStem.OVERWORLD);
				if (ow != null && ow.generator() instanceof NoiseBasedChunkGenerator noiseBasedChunkGenerator) {
					NoiseGeneratorSettings chunkGeneratorSettings = noiseBasedChunkGenerator.generatorSettings().value();
					((NoiseGeneratorSettingsAccessor) (Object) chunkGeneratorSettings).setSurfaceRule(
							SurfaceRules.sequence(
									TerramitySupport.SCORCHED_CAVES_RULE,
									chunkGeneratorSettings.surfaceRule()
							)
					);
				}
			}
		}
	}
}
