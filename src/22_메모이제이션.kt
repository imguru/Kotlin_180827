package ex22

import com.google.gson.GsonBuilder
import org.junit.jupiter.api.Test

// 순수 함수(Pure Function)
// : 함수의 입력 값이 동일하다면, 함수의 결과도 동일하다.
//  => 함수의 입력 값에 의해 결과값이 결정된다.
//  => 이전에 한번 수행했던 함수라면, 결과를 캐시를 통해 제공이 가능하다.
//     (메모이제이션) - Dynamic Programming

// Kotlin은 메모이제이션을 쉽게 적용할 수 있습니다.

// 1 1 2 3 5 8
/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/

/*
val map = mutableMapOf<Int, Long>()      // Map<Int, Long>
fun fib(k: Int): Long = map.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}


class FibTests {
    @Test
    fun fib10() {
        println(fib(10))
    }

    @Test
    fun fib30() {
        println(fib(30))
    }

    @Test
    fun fib50() {
        println(fib(50))
    }
}
*/


class User(val name: String, val age: Int)

fun toJson(user: User): String {
    println("toJson")
    val gson = GsonBuilder().setPrettyPrinting().create()
    return gson.toJson(user)
}

// 내가 만든 라이브러리가 아닌 함수에 메모이제이션을 적용하는 방법 - 확장함수
// => Closure를 이용해서, 지역 객체의 참조가 유지되게 하는 기법.
fun <P, R> ((P) -> R).memoized(): (P) -> R {
    val map = mutableMapOf<P, R>()
    return { k ->
        map.getOrPut(k) {
            this(k)
        }
    }
}

fun main(args: Array<String>) {
    val users = listOf(
            User("Tom", 42),
            User("Tom", 42),
            User("Tom", 42)
    )

    val toJson = ::toJson.memoized()

    for (e in users) {
        println(toJson(e))
    }
}





































