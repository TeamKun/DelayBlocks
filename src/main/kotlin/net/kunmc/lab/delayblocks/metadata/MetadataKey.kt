package net.kunmc.lab.delayblocks.metadata

sealed class MetadataKey<T>(val value: String) {
    object IsBroken : MetadataKey<Boolean>("IsBroken")
}