package net.mrmanhd.onevsone.minigame.game.kit

import eu.thesimplecloud.jsonlib.GsonCreator
import net.mrmanhd.onevsone.minigame.config.AbstractMultipleConfigLoader
import net.mrmanhd.onevsone.minigame.game.map.GameMap
import java.io.File

/**
 * Created by MrManHD
 * Class create at 23.07.2021 14:10
 */

class GameKitLoader : AbstractMultipleConfigLoader<GameKit>(
    GameKit::class.java,
    File("plugins/1vs1/kits"),
    emptyList(),
    true,
    GsonCreator().registerBukkitTypeAdapter().create()
)