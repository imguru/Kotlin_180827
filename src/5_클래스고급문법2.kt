package ex5_2

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

interface State : Serializable
interface View {
    // fun getCurrentState(): State
    val currentState: State

    fun restoreState(state: State) {}
}

class Button : View {
    // Default? Nested class
    class ButtonState : State

    override val currentState: State
        get() = ButtonState()
}

// Checked Exception
//  : Kotlin 예외처리가 강제되지 않습니다.
fun main(args: Array<String>) {
    val button = Button()

    val fos = FileOutputStream("state.out")
    val oos = ObjectOutputStream(fos)

    oos.writeObject(button.currentState)
}
