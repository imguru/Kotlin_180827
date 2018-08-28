package ex9

/*
// object
// 1. object - Singleton
//   => object declaration
object Cursor {
    val name: String = "Mouse cursor"

    // 초기화 블록
    init {
        println("Cursor object created")
    }

    fun move(x: Int, y: Int) {
        println("move - $x,$y")
    }
}

// 2. companion object(동반 객체)
//  : static 필드 / static 메소드를 만들 수 없습니다.

// 상수 -    C#         /  java          / kotlin     / c++
//  컴파일 상수(const)    /  static final  / const val  / const, constexpr
//  런타임 상수(readonly) /  final         / val        / const

class User {
    // val message: String = "User"
    companion object Utils {
        // val TAG = User::class.java.simpleName
        const val name = "User"
        const val TAG = "User"
        // static final String TAG = "MainActivity"
        fun foo() {
            println("foo")
        }
    }
}

fun main(args: Array<String>) {
    // val user = User()
    // println(user.message)

    println(User.TAG)
    User.foo()




    println("Program started")
    Cursor.move(10, 20)
    println(Cursor.name)
}
*/

// 3. 무명 객체(Anonymous Object)
interface OnClickListener {
    fun onClick(button: Button)
}

class Button {
    private var listener: OnClickListener? = null
    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    fun click() {
        println("Button click")
        // listener가 null이 아니면 onClick을 호출한다.
        // listener?.onClick(this)

        listener?.onClick(this)
    }
}

class Dialog {
    fun open() = println("Open")
    fun close() = println("Close")
}

fun main(args: Array<String>) {
    val button1 = Button()
    val button2 = Button()

    val dialog = Dialog()

    button1.setOnClickListener(object: OnClickListener {
        override fun onClick(button: Button) {
            dialog.open()
        }
    })

    button2.setOnClickListener(object: OnClickListener {
        override fun onClick(button: Button) {
            dialog.close()
        }
    })

    button1.click()
    button2.click()

}















