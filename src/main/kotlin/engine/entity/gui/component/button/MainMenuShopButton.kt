package engine.entity.gui.component.button

import engine.EngineState
import engine.GameEngine
import java.awt.event.WindowEvent
import java.time.Instant

class MainMenuShopButton: Button("Gear shop") {
    override fun onClick() {
        if (GameEngine.mainMenuShop.initTime == 0.toLong()) {
            GameEngine.mainMenuShop.initTime = Instant.now().toEpochMilli()
        }
        GameEngine.state = EngineState.MAIN_MENU_SHOP
    }
}