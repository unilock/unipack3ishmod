package cc.unilock.unipack.mixin.wearablebackpacks;

import com.nyfaria.wearablebackpacks.backpack.BackpackInfoMenu;
import com.nyfaria.wearablebackpacks.backpack.BackpackMenu;
import com.nyfaria.wearablebackpacks.block.entity.BackpackBlockEntity;
import com.nyfaria.wearablebackpacks.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BackpackMenu.class, remap = false)
public abstract class BackpackMenuMixin extends BackpackInfoMenu {
	@Shadow
	@Final
	private boolean isItem;

	@Shadow
	@Final
	private BlockPos pos;

	@Shadow
	@Final
	private int ownerId;

	protected BackpackMenuMixin(MenuType<?> pMenuType, int pContainerId) {
		super(pMenuType, pContainerId);
		throw new AssertionError();
	}

	/**
	 * @author unilock
	 * @reason check casts
	 */
	@Overwrite(remap = true)
	public void removed(Player pPlayer) {
		if (pPlayer.level() instanceof ServerLevel serverLevel) {
			if (isItem) {
				if (serverLevel.getEntity(ownerId) instanceof LivingEntity livingEntity) {
					ItemStack chest = livingEntity.getItemBySlot(EquipmentSlot.CHEST);
					ItemStack hand = livingEntity.getItemBySlot(livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
					if (chest.is(ItemInit.BACKPACK.get())) {
						chest.getOrCreateTag().putBoolean("accessed", false);
					} else if (hand.is(ItemInit.BACKPACK.get())) {
						hand.getOrCreateTag().putBoolean("accessed", false);
					} else {
						if (serverLevel.getBlockEntity(pos) instanceof BackpackBlockEntity backpackBlockEntity) {
							backpackBlockEntity.setAccessed(true);
						}
					}
				}
			} else {
				BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
				if (blockEntity instanceof BackpackBlockEntity backpackBlockEntity) {
					backpackBlockEntity.setAccessed(true);
					backpackBlockEntity.updateBlock();
				}
			}
		}
		super.removed(pPlayer);
	}
}
