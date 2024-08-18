package moe.hertz.infinity_fix.test;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.CommandContextBuilder;
import net.minecraft.command.CommandSource;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.test.TestContext;
import org.apache.logging.log4j.core.jmx.Server;

public class TestUtil {

  public static void runCommand(TestContext helper, String command) {
    var server = helper.getWorld().getServer();

    ServerCommandSource commandsource = server.getCommandSource();
    CommandDispatcher<ServerCommandSource> commanddispatcher =
      server.getCommandManager().getDispatcher();

    ParseResults<ServerCommandSource> results = commanddispatcher.parse(command,commandsource);

    server.getCommandManager().execute(results,command);
  }
}
