package net.kunmc.lab.delayblocks.command

import net.kunmc.lab.delayblocks.DelayBlocks
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class SettingCommand(private val plugin: DelayBlocks) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val seconds = args[0].toIntOrNull()
        seconds?.let {
            plugin.seconds = it
        }
        plugin.server.broadcastMessage("${plugin.seconds}秒に変更しました")
        return true
    }
}