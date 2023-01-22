package engine

import engine.entity.gui.ButtonMenu
import engine.entity.gui.ShopMenu
import engine.entity.gui.component.WindowComponent
import engine.entity.gui.component.button.*
import engine.entity.gui.component.upgrade.persistent.PersistentDoubleProjectilesUpgrade
import engine.entity.gui.component.upgrade.persistent.PersistentMaxHpUpgrade
import engine.entity.gui.component.upgrade.persistent.PersistentUpgrade
import engine.entity.mob.enemy.EnemyType
import engine.entity.registrer.EnemyRegistrer
import engine.entity.registrer.EnemyRegistrerType
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerManager
import engine.event.input.InputListenerType
import engine.event.movement.hero.HeroMovementListenerManager
import engine.game.Game
import engine.resource.ResourceManager
import engine.resource.SpriteFactory
import engine.sound.SoundManager
import engine.window.Window
import server.ServerConnector
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine : JPanel(), InputListener {
    // press "x" to display debug mode
    var debug = true

    // socket
    var server = ServerConnector

    // vars
    var ticksCounter = 0
    var state: EngineState = EngineState.MAIN_MENU
    val upgrades: MutableList<PersistentUpgrade> = mutableListOf()
    var behelits = 0

    // game loop
    val timer: Timer = Timer(1000/60) { run() }

    // objects
    var game: Game? = null
    var window: Window = Window()
    val enemyRegistrer = EnemyRegistrer()

    // event managers
    val inputListenerManager = InputListenerManager()
    val heroMovementListenerManager = HeroMovementListenerManager()

    // gui
    var mainMenu: ButtonMenu = ButtonMenu("Menu", mutableListOf(NewGameButton(), MainMenuShopButton(), ExitGameButton()), WindowComponent(150,150), mutableListOf(EngineState.MAIN_MENU))
    var mainMenuShop: ButtonMenu = ButtonMenu("Gear", mutableListOf(PersistentMaxHpUpgrade(), PersistentDoubleProjectilesUpgrade()), WindowComponent(window.WIDTH,window.HEIGHT), mutableListOf(EngineState.MAIN_MENU_SHOP))
    var optionMenu: ButtonMenu = ButtonMenu("Pause", mutableListOf(ResumeButton(), GoToMainMenuButton(), NewGameButton(), ExitGameButton()), WindowComponent(150,150), mutableListOf(EngineState.OPTIONS))
    var shopMenu: ShopMenu = ShopMenu("Shop", window = WindowComponent(window.WIDTH,window.HEIGHT), listeningState = mutableListOf(EngineState.SHOP))
    var gameOverMenu: ButtonMenu = ButtonMenu("Game over", mutableListOf(NewGameButton(), NewGameButton()), WindowComponent(150,150), mutableListOf(EngineState.GAME_OVER))

    init
    {
        // reload engine with properties file
        val oldProperties = ResourceManager.getProperties()
        behelits = oldProperties.getProperty("engine_behelits", 0.toString()).toInt()

        oldProperties.getProperty("engine_upgrades").split(",").forEach {
            var upgrade: PersistentUpgrade? = null
            when (it) {
                "PersistentDoubleProjectilesUpgrade" -> upgrade = PersistentDoubleProjectilesUpgrade()
                "PersistentMaxHpUpgrade" -> upgrade = PersistentMaxHpUpgrade()
            }

            if (upgrade != null) upgrades.add(upgrade)
        }

        // sounds
        SoundManager.loadFiles()
        SoundManager.play("main song")

        // resources
        SpriteFactory.registerSprites()

        // TODO getFile
        // enemy registration
        enemyRegistrer.add(EnemyRegistrerType("warrior_0", EnemyType.WARRIOR, 0, 100, 100, .6, .5, 25, 48, 48))
        enemyRegistrer.add(EnemyRegistrerType("warrior_1", EnemyType.WARRIOR, 2, 150, 150, .5, .5, 50, 32, 32))
        enemyRegistrer.add(EnemyRegistrerType("warrior_2", EnemyType.WARRIOR, 5, 400, 400, .4, .5, 100, 64, 64))

        SwingUtilities.invokeLater {
            window.init()
            timer.start()

            // event binding
            inputListenerManager.sub(shopMenu)
            inputListenerManager.sub(mainMenu)
            inputListenerManager.sub(mainMenuShop)
            inputListenerManager.sub(optionMenu)
            inputListenerManager.sub(this)

            println("engine running")
        }
    }

    fun run()
    {
        window.updateTitle()
        window.getKeyboardMovementInput()

        when (state)
        {
            EngineState.MAIN_MENU -> {
                // reset shop
                if (mainMenuShop.initTime != 0.toLong()) {
                    mainMenuShop.initTime = 0.toLong()
                    mainMenuShop.error = ""
                }
            }
            EngineState.MAIN_MENU_SHOP -> {}
            EngineState.PLAY -> {
                SoundManager.changeVolume("main song", 1f)
                game?.update()
            }
            EngineState.SHOP -> {
                if (shopMenu.upgrades.size < 3) {
                    shopMenu.buildUpgrades()
                }
            }
            EngineState.OPTIONS -> {
                SoundManager.changeVolume("main song", 0.5f)
            }
            EngineState.GAME_OVER -> {
                SoundManager.stop("main song")
                SoundManager.play("death")
            }


        }

        repaint()
        ticksCounter++
    }

    fun gameEnd() {
        behelits += game!!.behelits
        inputListenerManager.unsub(game!!)
        game = null
        state = EngineState.MAIN_MENU
    }

    fun engineExit() {
        ResourceManager.saveProperties()
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D
        g.font = g.font.deriveFont(Font.BOLD)

        game?.draw(g)
        if (state == EngineState.SHOP) shopMenu.draw(g)
        if (state == EngineState.MAIN_MENU) {
            mainMenu.draw(g)

            // draw behelit counter
            g.drawImage(SpriteFactory.get("object_behelit").get(), null, 25, 25)
            g.color = Color.black
            g.drawString(behelits.toString(), 25 + 32, 25 + 16)
        }
        if (state == EngineState.MAIN_MENU_SHOP) {
            mainMenuShop.draw(g)

            // draw behelit counter
            g.drawImage(SpriteFactory.get("object_behelit").get(), null, 25, 25)
            g.color = Color.black
            g.drawString(behelits.toString(), 25 + 32, 25 + 16)
        }
        if (state == EngineState.OPTIONS) optionMenu.draw(g)
    }

    override fun onInputEvent(e: InputEvent) {
        super.onInputEvent(e)
        if (e.type == InputListenerType.X) debug = !debug
        when (state) {
            EngineState.MAIN_MENU -> {}
            EngineState.PLAY -> if (e.type == InputListenerType.ESCAPE) state = EngineState.OPTIONS
            EngineState.SHOP -> {}
            EngineState.OPTIONS -> if (e.type == InputListenerType.ESCAPE) state = EngineState.PLAY
            EngineState.GAME_OVER -> {}
            EngineState.MAIN_MENU_SHOP -> if (e.type == InputListenerType.ESCAPE) state = EngineState.MAIN_MENU
        }
    }
}