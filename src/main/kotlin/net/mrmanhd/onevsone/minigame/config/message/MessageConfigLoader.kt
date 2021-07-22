package net.mrmanhd.onevsone.minigame.config.message

import eu.thesimplecloud.jsonlib.GsonCreator
import net.mrmanhd.onevsone.minigame.config.AbstractJsonLibConfigLoader
import net.mrmanhd.onevsone.minigame.config.default.DefaultConfig
import java.io.File

/**
 * Created by MrManHD
 * Class create at 21.07.2021 21:05
 */

class MessageConfigLoader : AbstractJsonLibConfigLoader<MessageConfig>(
    MessageConfig::class.java,
    File("plugins/1vs1/messages.json"),
    { DefaultMessageConfig.get() },
    true,
    GsonCreator().registerBukkitTypeAdapter().create()
)