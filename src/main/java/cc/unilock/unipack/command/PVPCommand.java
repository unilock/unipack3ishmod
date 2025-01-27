package cc.unilock.unipack.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.UserWhiteList;
import net.minecraft.server.players.UserWhiteListEntry;
import net.minecraftforge.fml.loading.FMLPaths;

public class PVPCommand {
	public static final UserWhiteList pvpWhitelist = new UserWhiteList(FMLPaths.CONFIGDIR.get().resolve("pvp.json").toFile());

	public static void registerCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
		dispatcher.register(
				Commands.literal("pvp").executes(PVPCommand::status)
						.then(Commands.literal("enable").executes(PVPCommand::enable))
						.then(Commands.literal("disable").executes(PVPCommand::disable))
		);
	}

	private static int status(CommandContext<CommandSourceStack> ctx) {
		if (!ctx.getSource().isPlayer()) {
			ctx.getSource().sendSystemMessage(Component.literal("This command must be executed by a player"));
			return 0;
		}

		ctx.getSource().sendSystemMessage(Component.literal("PVP is "+(pvpWhitelist.isWhiteListed(ctx.getSource().getPlayer().getGameProfile())?"enabled":"disabled")));
		return Command.SINGLE_SUCCESS;
	}

	private static int enable(CommandContext<CommandSourceStack> ctx) {
		if (!ctx.getSource().isPlayer()) {
			ctx.getSource().sendSystemMessage(Component.literal("This command must be executed by a player"));
			return 0;
		}

		pvpWhitelist.add(new UserWhiteListEntry(ctx.getSource().getPlayer().getGameProfile()));
		ctx.getSource().sendSystemMessage(Component.literal("PVP enabled"));
		return Command.SINGLE_SUCCESS;
	}

	private static int disable(CommandContext<CommandSourceStack> ctx) {
		if (!ctx.getSource().isPlayer()) {
			ctx.getSource().sendSystemMessage(Component.literal("This command must be executed by a player"));
			return 0;
		}

		pvpWhitelist.remove(ctx.getSource().getPlayer().getGameProfile());
		ctx.getSource().sendSystemMessage(Component.literal("PVP disabled"));
		return Command.SINGLE_SUCCESS;
	}
}
