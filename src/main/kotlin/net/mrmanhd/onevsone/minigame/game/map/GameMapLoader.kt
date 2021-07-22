package net.mrmanhd.onevsone.minigame.game.map

import eu.thesimplecloud.jsonlib.GsonCreator
import net.mrmanhd.onevsone.minigame.config.AbstractMultipleConfigLoader
import java.io.File

/**
 * Created by MrManHD
 * Class create at 21.07.2021 20:24
 */

class GameMapLoader : AbstractMultipleConfigLoader<GameMap>(
    GameMap::class.java,
    File("plugins/1vs1/maps"),
    emptyList(),
    true,
    GsonCreator().registerBukkitTypeAdapter().create()
)