package net.mrmanhd.onevsone.minigame.config.arena

import eu.thesimplecloud.jsonlib.GsonCreator
import net.mrmanhd.onevsone.minigame.config.AbstractJsonLibConfigLoader
import java.io.File

/**
 * Created by MrManHD
 * Class create at 23.07.2021 10:08
 */

class ArenaConfigLoader : AbstractJsonLibConfigLoader<ArenaConfig>(
    ArenaConfig::class.java,
    File("plugins/1vs1/arenaSettings.json"),
    { DefaultArenaConfig.get() },
    true,
    GsonCreator().registerBukkitTypeAdapter().create()
)