package engine.entity.gui.component

import engine.entity.Entity
import engine.entity.gui.Gui

abstract class GuiComponent : Entity()
{
    abstract val gui: Gui
    abstract val parent: GuiComponent?

    abstract fun draw()
    abstract fun drawAbsolute()
    abstract fun drawRelativeToParent()
}