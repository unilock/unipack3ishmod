package cc.unilock.unipack.mixin.biomemakeover;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.mutable.MutableInt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import party.lemons.biomemakeover.init.BMEntities;
import party.lemons.biomemakeover.util.EntityUtil;

import java.util.Collection;
import java.util.List;

@Mixin(value = EntityUtil.class, remap = false)
public class EntityUtilMixin {
	/**
	 * @author unilock
	 * @reason fix
	 */
	@Overwrite
	public static void applyProjectileResistance(Iterable<ItemStack> equipment, MutableInt resistance) {
		List<ItemStack> list = ImmutableList.copyOf(equipment);
		for (int i = 0; i < 4; i++) {
			ItemStack stack = list.get(i);
			if (!stack.isEmpty()) {
				EquipmentSlot slot = EquipmentSlot.byTypeAndIndex(EquipmentSlot.Type.ARMOR, i);
				if (stack.getAttributeModifiers(slot).containsKey(BMEntities.ATT_PROJECTILE_RESISTANCE.get())) {
					Collection<AttributeModifier> modifiers = stack.getAttributeModifiers(slot).get(BMEntities.ATT_PROJECTILE_RESISTANCE.get());
					for (AttributeModifier mod : modifiers) {
						resistance.add(mod.getAmount());
					}
				}
			}
		}
	}
}
