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

fun foo(a: Int, b: Double) : String = "foo"

// (Array<String>) -> Unit
/*
fun main(args: Array<String>) {
    val f: (Array<String>) -> Unit = ::main

    var a: Int = 10
    val b = 20

    // 중요: 함수의 타입을 표현하는 방법
    // (Int, Int) -> Int
    // var fp: ((Int, Int) -> Int) = ::add
    var fp: (Int, Int) -> Int = ::add
    fp = ::sub

    println(fp(10, 20))

    // KFunction2<ArgType1, ArgType2, ReturnType>
    //  : 2개의 인자를 받는다.
    //  : JVM 내부의 타입이다.
    //   -> 코틀린에서는 사용이 불가능합니다.

    var fp2: (Int, Double) -> String = ::foo
    // println(fp2.name)
    fp2(10, 3.14)
    fp2.invoke(10, 3.14)

    var fp3 = ::add
    println(fp3.name)
    fp3(10, 20)
    fp3.invoke(10, 20)


    // fp = Dialog::close
}
*/

/*
fun calculateArea(width: Int, height: Int) = width * height

fun printArea(width: Int, height: Int) {
    val area = calculateArea(width, height)
    println("The area is $area")
}
*/

/*
fun printArea(width: Int, height: Int) {
    // 5. 지역 함수를 만드는 것도 가능합니다.
    fun calculateArea(width: Int, height: Int) = width * height

    val area = calculateArea(width, height)
    println("The area is $area")
}
*/

// Implicit: 별도의 코드를 작성하지 않아도
// <-> Explicit: 명시적인 코드를 작성해야만
fun printArea(width: Int, height: Int) {
    // 5. 지역 함수를 만드는 것도 가능합니다.
    // : 지역 함수를 감싸고 있는 함수의 매개변수나 변수에 암묵적으로 접근하는 것이 가능합니다.
    //  => Closure(클로져)
    fun calculateArea() = width * height

    val area = calculateArea()
    println("The area is $area")
}

fun main(args: Array<String>) {
    printArea(100, 30)
}





