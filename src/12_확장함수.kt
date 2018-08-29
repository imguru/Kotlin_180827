package ex13

// => 코틀린은 JAVA API를 재작성하지 않아도 코틀린이 제공하는 기능을
//    추가할 수 있었다.
// => Extension Function

// 객체 지향 5대 원칙 - SOLID
//  => 로버트 C 마틴
// SRP: 단일 책임의 원칙
// => 모듈은 단 하나의 책임을 가져야 한다.
// OCP: 개방 폐쇄의 원칙
// => 새로운 기능이 추가되어도 기존 코드는 수정되면 안된다.
// LSP: 리스코프 대체 원칙
// => 자식 클래스는 부모 클래스로 대체 가능해야 한다.
// => 상속의 목적은 '다형성' 이다.
// ISP: 인터페이스 분리 원칙
// => 범용 인터페이스 보다는 세분화된 인터페이스가 낫다.
// DIP: 의존관계 역전 원칙
// => 구체적인 타입에 의존하는 것이 아니라, 인터페이스나 추상 클래스에 의존해야
//    한다.


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
    private var age: Int = 42
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

// * private / protected 는 접근이 불가능하다.
// fun User.foo(): Int = this.age
fun String.lastChar(): Char = this[this.length - 1]

/*
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
*/

/*
fun <T> xjoinToString(collection: Collection<T>,
                     separator: String,
                     prefix: String,
                     postfix: String): String {

    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(separator)

        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
*/

// Collection<String>에 대해서만 확장하고 싶다.
//fun <T> Collection<T>.xjoinToString(
//                      separator: String = ", ",
//                      prefix: String = "[",
//                      postfix: String = "]"): String {
fun Collection<String>.xjoinToString(
        separator: String = ", ",
        prefix: String = "[",
        postfix: String = "]"): String {

    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0)
            result.append(separator)

        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main(args: Array<String>) {
    val names = listOf("Tom", "Bob", "Alice")
    val x = listOf(10, 20, 30)
    val result = names.xjoinToString()

    // x.xjoinToString()

    // names.xjoinToString(", ", "[", "]")
    // joinToString(names, ", ", "[", "]")

    println(result)
}















