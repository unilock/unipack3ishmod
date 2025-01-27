package cc.unilock.unipack.command;

import cc.unilock.unipack.UniPack;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class DumpRegistriesCommand {
	private static final ResourceKey<Registry<Registry<?>>> ROOT_REGISTRY_KEY = ResourceKey.createRegistryKey(BuiltInRegistries.ROOT_REGISTRY_NAME);
	private static final DynamicCommandExceptionType UNKNOWN_REGISTRY = new DynamicCommandExceptionType(key -> Component.translatable("commands.forge.tags.error.unknown_registry", key));

	public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(Commands.literal("dump_registry").requires(stack -> stack.hasPermission(4)).then(Commands.argument("registry", ResourceKeyArgument.key(ROOT_REGISTRY_KEY)).suggests(DumpRegistriesCommand::suggestRegistries).executes(DumpRegistriesCommand::dumpRegistry)));
	}

	private static int dumpRegistry(final CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		final ResourceKey<? extends Registry<?>> registryKey = getResourceKey(ctx, "registry", ROOT_REGISTRY_KEY).orElseThrow();
		final Registry<?> registry = ctx.getSource().getServer().registryAccess().registry(registryKey).orElseThrow(() -> UNKNOWN_REGISTRY.create(registryKey.location()));

		File file = FMLPaths.GAMEDIR.get().resolve("registry.txt").toFile();
		try {
			file.delete();
			file.createNewFile();
		} catch (IOException e) {
			UniPack.LOGGER.error("IOException!", e);
			return 0;
		}
		try (FileWriter writer = new FileWriter(file)) {
			if (Registries.ENCHANTMENT.equals(registry.key())) {
				for (var entry : registry.entrySet()) {
					writer.append(entry.getKey().location().toString()).append(' ').append(Integer.toString(((Enchantment) entry.getValue()).getMaxLevel())).append('\n');
				}
			} else {
				for (var entry : registry.entrySet()) {
					writer.append(entry.getKey().location().toString()).append('\n');
				}
			}
		} catch (IOException e) {
			UniPack.LOGGER.error("IOException!", e);
			return 0;
		}

		return Command.SINGLE_SUCCESS;
	}

	@SuppressWarnings("SameParameterValue")
	private static <T> Optional<ResourceKey<T>> getResourceKey(final CommandContext<CommandSourceStack> ctx, final String name, final ResourceKey<Registry<T>> registryKey) {
		final ResourceKey<?> key = ctx.getArgument(name, ResourceKey.class);
		return key.cast(registryKey);
	}

	private static CompletableFuture<Suggestions> suggestRegistries(final CommandContext<CommandSourceStack> ctx, final SuggestionsBuilder builder) {
		ctx.getSource().registryAccess().registries()
				.map(RegistryAccess.RegistryEntry::key)
				.map(ResourceKey::location)
				.map(ResourceLocation::toString)
				.forEach(builder::suggest);
		return builder.buildFuture();
	}
}
