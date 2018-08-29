package ex20_2

/*
open class Context {
    fun startActivity(context: Context, activityClass: Class<out Activity>) {
        val newActivity = activityClass.newInstance()
        newActivity.onCreate()
    }
}

open class Activity : Context() {
    open fun onCreate() {
        println("${this.javaClass.simpleName} - onCreate")
    }
}

// Generic
//  1. JVM의 제네릭은 타입 소거(Type erasure)를 사용해 구현됩니다.
//  => 실행 시점에 제네릭 타입에 대한 인자 정보가 존재하지 않습니다.

//  2. 코틀린 또한 제네릭 타입 인자 정보는 런타임에 존재하지 않습니다.

// 핵심: 인라인 함수는 실체화된 타입 파라미터를 사용할 수 있다.
inline fun <reified T : Activity> Context.startActivity() {
    startActivity(this, T::class.java)
}

class StoreActivity : Activity()
class MainActivity : Activity() {
    override fun onCreate() {
        super.onCreate()
        // ...
        // startActivity(this, StoreActivity::class.java)

        // Anko
        startActivity<StoreActivity>()
    }
}

fun main(args: Array<String>) {
    val mainActivity = MainActivity()
    mainActivity.onCreate()
}
*/


// List<String>


inline fun<reified T> isA(value: Any) = value is T

fun main(args: Array<String>) {
    val list1: List<String> = listOf("A", "B")  // List
    val list2: List<Double> = listOf(1.0, 3.14) // List

    println(isA<String>("hello"))
    // value is String

    println(isA<Int>(1.0))
    // value is Int
}






























