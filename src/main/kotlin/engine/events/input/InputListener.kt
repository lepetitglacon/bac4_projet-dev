package engine.events.input

import engine.events.ListenerInterface

interface InputListener : ListenerInterface{

    fun onEscape()
    fun onEnter()
    fun onSpace()
    fun onClick()
}