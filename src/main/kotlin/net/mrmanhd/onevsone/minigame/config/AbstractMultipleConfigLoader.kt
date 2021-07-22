package net.mrmanhd.onevsone.minigame.config

import com.google.gson.Gson
import eu.thesimplecloud.jsonlib.JsonLib
import net.mrmanhd.onevsone.minigame.utils.Nameable
import java.io.File

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:20
 */

abstract class AbstractMultipleConfigLoader<T : Nameable>(
    private val clazz: Class<T>,
    private val directory: File,
    private val defaultValues: List<T>,
    private val saveDefaultOnFirstLoad: Boolean,
    private val gsonToUse: Gson = JsonLib.GSON
) {

    fun save(value: T) {
        JsonLib.fromObject(value, gsonToUse).saveAsFile(getFileByObject(value))
    }

    fun delete(value: T) {
        getFileByObject(value).delete()
    }

    fun loadAll(): Set<T> {
        if (!directory.exists() && saveDefaultOnFirstLoad) saveDefaults()
        return this.directory.listFiles()?.mapNotNull { JsonLib.fromJsonFile(it, gsonToUse)?.getObject(clazz) }?.toSet()
            ?: emptySet()
    }

    private fun saveDefaults() {
        defaultValues.forEach { save(it) }
    }

    private fun getFileByObject(value: T): File {
        return File(this.directory, value.getName() + ".json")
    }
}