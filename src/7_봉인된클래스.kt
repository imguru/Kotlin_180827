package ex7


// 아래 코드의 문제점
// : 누군가 새로운 Expr 기반의 클래스를 생성할 경우, 문제가 발생합니다.

// 자신 이외의 다른 사람이 상속을 못하도록 하고 싶다.
//  => 봉인된 클래스
/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else ->
            throw IllegalArgumentException("Unknown expression")
    }
}

fun main(args: Array<String>) {
    val a = Num(10)
    val b = Num(32)
    val s = Sum(a, b)

    println(eval(s))
}
*/

// 1.0: 오로지 중첩된 클래스로 사용해야 했다.
// 1.1: 같은 파일 내에서 사용할 수 있다.
sealed class Expr

class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
    }
}

fun main(args: Array<String>) {
    val a = Num(10)
    val b = Num(32)
    val s = Sum(a, b)

    println(eval(s))
}







