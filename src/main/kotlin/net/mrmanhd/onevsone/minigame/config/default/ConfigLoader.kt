package net.mrmanhd.onevsone.minigame.config.default

import eu.thesimplecloud.jsonlib.GsonCreator
import eu.thesimplecloud.jsonlib.JsonLib
import net.mrmanhd.onevsone.minigame.config.AbstractJsonLibConfigLoader
import java.io.File

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:31
 */

class ConfigLoader : AbstractJsonLibConfigLoader<Config>(
    Config::class.java,
    File("plugins/1vs1/config.json"),
    { DefaultConfig.get() },
    true,
    GsonCreator().registerBukkitTypeAdapter().create()
)