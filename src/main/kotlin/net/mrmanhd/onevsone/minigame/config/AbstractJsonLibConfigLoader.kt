package net.mrmanhd.onevsone.minigame.config

import com.google.gson.Gson
import eu.thesimplecloud.jsonlib.JsonLib
import java.io.File

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:23
 */

abstract class AbstractJsonLibConfigLoader<T : Any>(
    private val configClass: Class<T>,
    private val configFie: File,
    private val lazyDefaultObject: () -> T,
    private val saveDefaultOnFistLoad: Boolean,
    private val gsonToUse: Gson = JsonLib.GSON
) {

    fun loadConfig(): T {
        val objectFromFile = JsonLib.fromJsonFile(configFie, gsonToUse)?.getObjectOrNull(configClass)
        if (objectFromFile == null) {
            val defaultObject = lazyDefaultObject()
            if (saveDefaultOnFistLoad && !doesConfigFileExist())
                saveConfig(defaultObject)
            return defaultObject
        }
        return objectFromFile
    }

    fun saveConfig(value: T) {
        JsonLib.fromObject(value, gsonToUse).saveAsFile(configFie)
    }

    fun doesConfigFileExist(): Boolean = this.configFie.exists()

}