package cc.unilock.unipack.mixin.reputation;

import atonkish.reputation.ReputationPlugin;
import org.spongepowered.asm.mixin.Mixin;
import snownee.jade.api.WailaPlugin;

@Mixin(value = ReputationPlugin.class, remap = false)
@WailaPlugin
public class ReputationPluginMixin {
}
