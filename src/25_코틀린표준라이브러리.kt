package ex25

import java.io.File
import java.util.*

// 1. 조건 확인 함수
//  : 코틀린은 예외 처리가 강제되지 않습니다.
//     : Checked Exception 없습니다.
//  => 인자로 받은 표현식이 참이 아닌 경우 예외를 발생시키는 함수

// check    -> IllegalStateException
// require  -> IllegalArgumentException
/*
fun logMessage(filename: String, message: String) {
    val file = File(filename)

//    check(file.exists())   // IllegalStateException
//    if (!file.exists()) {
//        throw IllegalArgumentException()
//    }

    require(message.length > 5)  // IllegalArgumentException
}

fun log(message: String?) {
    // Null 인지 체크해서, Null 이면 예외를 발생시킵니다.
    // Null 이 아니면, Null 아닌 값을 반환합니다.
    val value = requireNotNull(message)

    println(value)
}


fun main(args: Array<String>) {
    logMessage("file.log", "hello")
}
*/

// void
//  Kotlin: Unit: 결과가 없는 반환값
//          Nothing: 함수가 정상적으로 반환될 수 없음.

fun foo(): Nothing {
    println("foo")

    throw IllegalStateException()
}


// 2. 명시적인 실행 종료 함수
fun main(args: Array<String>) {
    // TODO("아직 작성중입니다.")
    val scanner = Scanner(System.`in`)
    val cmd = scanner.nextInt()

    if (cmd < 0) {
        // throw IllegalStateException()
        error("실행할 수 없습니다.")
    }
}















