package cc.unilock.unipack.mixin.petrolpark;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.petrolpark.recipe.condition.ConfigBooleanCondition;
import com.simibubi.create.foundation.config.ConfigBase;
import com.simibubi.create.foundation.config.ui.ConfigHelper;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.lang.reflect.InvocationTargetException;

@Mixin(value = ConfigBooleanCondition.Serializer.class, remap = false)
public class ConfigBooleanConditionSerializerMixin {
	/**
	 * @author unilock
	 * @reason pain
	 */
	@Overwrite
	public ConfigBooleanCondition read(JsonObject json) {
		if (!json.has("value")) throw new JsonSyntaxException("Must specify a config boolean");
		if (!json.has("mod")) throw new JsonSyntaxException("Must specify a mod ID");
		String path = GsonHelper.getAsString(json, "value");
		String modId = GsonHelper.getAsString(json, "mod");
		ForgeConfigSpec forgeConfigSpec = ConfigHelper.findForgeConfigSpecFor(ModConfig.Type.COMMON, modId);
		if (forgeConfigSpec == null) {
			if ("destroy".equals(modId)) {
				try {
					forgeConfigSpec = ((ConfigBase) Class.forName("com.petrolpark.destroy.config.DestroyAllConfigs").getMethod("byType", ModConfig.Type.class).invoke(null, ModConfig.Type.COMMON)).specification;
				} catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					throw new JsonSyntaxException("Error reflecting Destroy config", e);
				}
			} else {
				throw new JsonSyntaxException("Couldn't find ForgeConfigSpec for "+modId);
			}
		}
		ForgeConfigSpec.ConfigValue<?> configValue =  forgeConfigSpec.getValues().get(ImmutableList.copyOf(Splitter.on(".").split(path)));
		if (!(configValue instanceof ForgeConfigSpec.BooleanValue booleanValue)) throw new JsonSyntaxException("The config must be a boolean type.");
		return new ConfigBooleanCondition(modId, booleanValue);
	}
}
