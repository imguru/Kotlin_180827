package ex19

import java.time.LocalDate
import java.time.LocalDateTime

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 것을 의미한다.
/*
fun sum(x: Int, y: Int) = x + y
// sum(10, 20)
// => sum(10)(20)

fun sum(x: Int): (Int) -> Int = { y: Int ->
    x + y
}

// 1) 함수의 실행을 지연할 수 있다.
fun main(args: Array<String>) {
    val result = sum(10, 20)
    println(result)

    val fn = sum(10)
    println(fn(20))
}
*/

// 2)
fun compute(logger: (String) -> Unit) {
    logger("Start!!")

    println("Processing...\n")

    logger("Finish")
}

enum class Level { INFO, WARN, ERROR }

// 현재 사용하는 로깅 라이브러리
// fun log(level: Level)(appendable: Appendable)(message: String) : Unit {
// }
fun log(level: Level, appendable: Appendable, message: String) {
    appendable.appendln("[${level.name}:${LocalDateTime.now()}]: $message")
}

//fun sum(x: Int): (Int) -> Int = { y: Int ->
//    x + y
//}


fun sum(x: Int, y: Int) = x + y
// 인자가 3개인 함수에 대해서 curring 지원 기능을 추가해봅시다.
// ::log.curried()
fun <P1, P2, R> ((P1, P2) -> R).curried() : (P1) -> (P2) -> R  = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}


fun main(args: Array<String>) {
    /*
    println(sum(10, 20))

    // (Int) -> (Int) -> Int
    val a: (Int) -> (Int) -> Int = ::sum.curried()

    // (Int) -> Int
    val b: (Int) -> Int = a(10)

    // Int
    val c = b(20)
    println(c)
    */


    log(Level.INFO, System.out, "Main 함수 시작")

    // 1. 람다(Lambda)를 이용하는 방법
    compute { message ->
        log(Level.WARN, System.out, message)
    }

    // 2. 커링의 방법을 이용합니다.
    // (Level) -> (Appendable) -> (String) -> Unit
    val a = ::log.curried()

    // (Appendable) -> (String) -> Unit
    val b = a(Level.INFO)

    // (String) -> Unit
    val c = b(System.out)

    // * 부분 적용: 인자가 많은 함수의 파라미터 값을 동적으로 고정해서
    //           재사용 가능하게 하는 방법
    val logger = ::log.curried()(Level.INFO)(System.out)
    compute(logger)



    // log(Level.WARN, System.out, message)
    // -> val logger: (String) -> Unit = log(Level.WARN)(System.out)
    //


    // compute(logger)
}
























