// Kotlin
//   OOP + Functional + DSL

// 함수형 프로그래밍
//  => 함수가 일급 시민으로 취급된다.
//     (First class citizens)
//  1) 변수에 담을 수 있어야 한다.
//  2) 인자에 전달할 수 있어야 한다.
//  3) 반환값으로 사용할 수 있어야 한다.
//  4) 실행 시간에 생성이 가능해야 한다.
//  5) 익명으로 생성이 가능해야 한다.

/*
fun add(a: Int, b: Int): Int {
    return a + b
}
*/

// 1. 단일 표현식 함수: 반환 타입 추론이 가능합니다.
// 2. 함수의 타입
//   : 함수의 시그니쳐(Signature)에 의해서 결정됩니다.
//   => 함수의 타입은 함수의 인자의 타입과 개수, 반환 타입에 대한 정도를 통해서
//      결정됩니다.

fun add(a: Int, b: Int) = a + b
fun sub(a: Int, b: Int) = a - b

class Dialog {
    fun close() {
        println("close")
    }
}

fun main(args: Array<String>) {
    var a: Int = 10
    val b = 20

    // 중요: 함수의 타입을 표현하는 방법
    // (Int, Int) -> Int
    var fp: (Int, Int) -> Int = ::add
    fp = ::sub


    // fp = Dialog::close


}





