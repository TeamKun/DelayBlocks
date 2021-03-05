package net.kunmc.lab.delayblocks.listener

import net.kunmc.lab.delayblocks.DelayBlocks
import net.kunmc.lab.delayblocks.ext.getMeta
import net.kunmc.lab.delayblocks.ext.setMeta
import net.kunmc.lab.delayblocks.metadata.MetadataKey
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.scheduler.BukkitRunnable

class BlockListener(private val plugin: DelayBlocks) : Listener {
    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val block = event.block
        event.isCancelled = true
        if (!block.getMeta(MetadataKey.IsBroken, false)) {
            block.setMeta(plugin, MetadataKey.IsBroken, true)
            object : BukkitRunnable() {
                override fun run() {
                    event.block.breakNaturally()
                }
            }.runTaskLater(plugin, plugin.seconds * 20L)
        }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val blockType = event.block.type
        val blockData = event.block.blockData.clone()
        event.block.type = Material.AIR
        object : BukkitRunnable() {
            override fun run() {
                setBlock(event.block, blockType, blockData)
            }
        }.runTaskLater(plugin, plugin.seconds * 20L)
    }

    private fun setBlock(block: Block, previousBlockType: Material, previousBlockData: BlockData) {
        block.type = previousBlockType
        block.blockData = previousBlockData
    }
}