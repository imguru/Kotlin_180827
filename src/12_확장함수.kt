package ex13
// => 코틀린은 JAVA API를 재작성하지 않아도 코틀린이 제공하는 기능을
//    추가할 수 있었다.
// => Extension Function




// 확장 함수
//  : 수평 확장

// 상속: 수직 확장
// 문제점
//  1) 깨지기 쉬운 기반 클래스 문제
//  2) 상속 금지된 클래스에 대해서는 확장 조차도 불가능하다.

// 수평 확장
//  1) C#: Extension Method
//  2) Swift: Extension
//  3) ObjC: Category
//  4) Javascript: Prototype
//  5) Kotlin: Extension Function

class User {
    // val name = "Hello"

    // Instance method
    // name? 타입은 무엇인가요?
    // (Unit)->String

    // fun name(this: User) = thiscall
    fun name(): String {
        return "Hello"
    }
}

// fun lastChar(text: String): Char = text[text.length - 1]

fun String.lastChar(): Char = this[this.length - 1]

fun main(args: Array<String>) {
    val s = "Hello"
    // println(lastChar(s))
    // lastChar(s)
    println(s.lastChar())

    val user = User()

    val f1: (User) -> String = User::name
    println(f1(user))

    // 바운드 참조
    val f2: () -> String = user::name
    println(f2())
}






















