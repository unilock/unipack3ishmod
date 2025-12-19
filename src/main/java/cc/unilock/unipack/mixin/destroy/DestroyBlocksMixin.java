package cc.unilock.unipack.mixin.destroy;

import com.petrolpark.destroy.DestroyBlocks;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mixin(value = DestroyBlocks.class, remap = false)
public class DestroyBlocksMixin {
	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/data/TagGen;tagBlockAndItem([Ljava/lang/String;)Lcom/tterrag/registrate/util/nullness/NonNullFunction;"))
	private static <T extends Block, P>  NonNullFunction<BlockBuilder<T, P>, ItemBuilder<BlockItem, BlockBuilder<T, P>>> tagBlockAndItemFix(String[] strings) {
		return TagGen.tagBlockAndItem(Arrays.stream(strings).collect(Collectors.toMap(s -> TagKey.create(Registries.BLOCK, new ResourceLocation("forge", s)), s -> TagKey.create(Registries.ITEM, new ResourceLocation("forge", s)))));
	}
}
