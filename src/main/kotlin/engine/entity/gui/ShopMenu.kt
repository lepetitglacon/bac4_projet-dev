package engine.entity.gui

import engine.EngineState
import engine.GameEngine
import engine.entity.gui.component.WindowComponent
import engine.entity.gui.component.upgrade.DoubleProjectileUpgrade
import engine.entity.gui.component.upgrade.ReduceGunCooldownUpgrade
import engine.entity.gui.component.upgrade.ThrewProjectileUpgrade
import engine.entity.gui.component.upgrade.Upgrade
import engine.entity.gui.component.upgrade.weapon.grenadelauncher.GrenadeLauncherWeaponUpgrade
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerType
import java.awt.Color
import java.awt.Graphics2D

class ShopMenu(
    var title: String = "",
    val possibleUpgrades: MutableList<Upgrade> = mutableListOf(),
    val upgrades: MutableList<Upgrade> = mutableListOf(),
    val window: WindowComponent = WindowComponent(),
    var listeningState: MutableList<EngineState> = mutableListOf()
) : InputListener {

    init {
        registerUpgrades()
    }

    var currentButton: Int = 0
    val btnPadding: Int = 25

    fun draw(g: Graphics2D) {
        window.draw(g)

        // title
        val titleWidth = (g.fontMetrics.getStringBounds(title, g)).width
        val titleX = (window.x + (window.width / 2 - titleWidth / 2)).toInt()
        val titleY = window.y + 25
        g.color = Color.WHITE
        g.drawString(title, titleX, titleY)

        // btns
        val btnStartingX: Int = window.x + window.width / 4
        val btnStartingY: Int = titleY + btnPadding
        upgrades.forEachIndexed { i, btn ->
            val btnX = btnStartingX
            val btnY = btnStartingY + btnPadding * i

            g.drawString(btn.string, btnX, btnY)
            if (i == currentButton) {
                g.drawString(">", btnX - btnPadding, btnY)
            }
        }
    }

    override fun onInputEvent(e: InputEvent) {

        if (listeningState.contains(GameEngine.state)) {
            println(e)
            super.onInputEvent(e)
            when (e.type) {
                InputListenerType.ENTER -> upgrades[currentButton].onClick()
                InputListenerType.ESCAPE -> {}
                InputListenerType.SPACE -> {}
                InputListenerType.UP -> if (currentButton == 0) currentButton = upgrades.size - 1 else currentButton--
                InputListenerType.LEFT -> {}
                InputListenerType.DOWN -> if (currentButton == upgrades.size - 1) currentButton = 0 else currentButton++
                InputListenerType.RIGHT -> {}
                InputListenerType.UNDEFINED -> {}
                InputListenerType.X -> {}
            }
        }
    }

    fun buildUpgrades() {
        for (i in 0..3) {
            var up = possibleUpgrades.random()

            while(upgrades.contains(up)) {
                up = possibleUpgrades.random()
            }

            if (GameEngine.game?.upgrades?.contains(up)!!) {
                if (up.maxLevel < GameEngine.game?.upgrades?.get(up)!!) {
                    upgrades.add(up)
                } else {
                    possibleUpgrades.remove(up)
                }
            } else {
                upgrades.add(up)
            }

        }
    }

    fun registerUpgrades() {
        // general
        possibleUpgrades.add(DoubleProjectileUpgrade())
        possibleUpgrades.add(ThrewProjectileUpgrade())
        possibleUpgrades.add(ReduceGunCooldownUpgrade())

        // weapons
        possibleUpgrades.add(GrenadeLauncherWeaponUpgrade())
    }
}