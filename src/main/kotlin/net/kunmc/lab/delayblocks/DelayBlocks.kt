package net.kunmc.lab.delayblocks

import net.kunmc.lab.delayblocks.command.SettingCommand
import net.kunmc.lab.delayblocks.listener.BlockListener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.command.Command
import org.bukkit.plugin.java.annotation.command.Commands
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.author.Author

@Plugin(name = "DelayBlocks", version = "1.0-SNAPSHOT")
@Author("ReyADayer")
@ApiVersion(ApiVersion.Target.v1_15)
@Commands(
        Command(
                name = PluginCommands.SETTING,
                desc = "db command",
                usage = "/<command>",
                permission = PluginPermissions.ADMIN,
                permissionMessage = "You don't have <permission>"
        )
)
class DelayBlocks : JavaPlugin() {
    var seconds = 60

    override fun onEnable() {
        server.pluginManager.registerEvents(BlockListener(this), this)
        getCommand(PluginCommands.SETTING)?.setExecutor(SettingCommand(this))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}