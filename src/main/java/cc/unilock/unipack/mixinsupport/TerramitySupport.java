package cc.unilock.unipack.mixinsupport;

import net.mcreator.terramity.init.TerramityModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class TerramitySupport {
	public static final ResourceKey<Biome> SCORCHED_CAVES = ResourceKey.create(Registries.BIOME, new ResourceLocation("terramity", "scorched_caves"));
	public static final SurfaceRules.RuleSource SCORCHED_CAVES_RULE = SurfaceRules.ifTrue(
			SurfaceRules.isBiome(SCORCHED_CAVES),
			SurfaceRules.sequence(
					SurfaceRules.ifTrue(
							SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
							SurfaceRules.sequence(
									SurfaceRules.ifTrue(
											SurfaceRules.waterBlockCheck(-1, 0),
											SurfaceRules.state(TerramityModBlocks.IGNEOSTONE.get().defaultBlockState())
									),
									SurfaceRules.state(TerramityModBlocks.IGNEOSTONE.get().defaultBlockState())
							)
					),
					SurfaceRules.ifTrue(
							SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR),
							SurfaceRules.state(TerramityModBlocks.IGNEOSTONE.get().defaultBlockState())
					)
			)
	);
}
