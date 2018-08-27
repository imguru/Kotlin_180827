package ex3

import java.util.*


// Java에서 패키지를 사용하기 위해서는 반드시
// 해당 디렉토리 구조와 동일하게 사용해야 합니다.

// kr.co.imguru.User
//  -> kr/co/imguru/User.java
// 코틀린에서는 위의 제약 사항이 더이상 존재하지 않습니다.
//  -> 위의 제약사항을 지키는 것이 권장됩니다.

// 1. 함수를 만드는 방법
// fun 함수이름(파라미터 이름: 타입): 반환 타입
//  1) 전역함수를 만드는 것이 가능합니다.
//  2) 별도의 파일에 전역 함수를 모아서 관리합니다.

/*
fun add(a: Int, b: Int) : Int {
    return a + b
}
*/

//  3) 함수를 '단일 표현식'으로 표현하는 것도 가능합니다.
//   => 반환 타입을 추론하는 것이 가능합니다.

//  4) 함수형 언어(Functional Programming)
//    * 함수가 일급 시민(First class/citizen)으로 취급된다.
//    : 함수를 일반 타입과 동일하게 취급할 수 있다.
//     함수를 인자로 전달하는 것도 가능하고, 함수를 반환하는 것도 가능하다. => 고차(계)함수

//    * 순수 함수(Pure Function)
//    : 함수가 참조적 투명성을 갖는다.
//     함수의 결과가 인자에 의해서 결정된다.
//     인자가 동일하면 함수의 결과도 동일하다.
//     void 반환 타입은 코틀린에서는 존재하지 않습니다.
//     => Unit
/*
fun add(a: Int, b: Int) = a + b
fun foo(): Unit {
    println("foo")
}

fun main(args: Array<String>) {
    println(foo())
    println(add(10, 32))
}
*/

// 5. 타입 시스템
//   * Java
//    - Primitive Type(int, char, double, ...)
//     1) Collection에 저장하는 것이 불가능하다.
//     2) 필드와 메소드를 가질 수 없다.
//    - Reference Type(String, Integer, Char, Double, ...)

//   * Kotlin
//    - 모든 것은 객체(상태(필드) + 기능(메소드))이다.
/*
fun main(args: Array<String>) {
    val n1 = 42           // 암시적 타입 추론
    val n2: Int = 42      // 명시적 타입 지정

    val n3: Long = n2.toLong()

    println(n2)

    val list: List<Int> = listOf(n1, n2)
}
 */


// 6. 변수를 선언하는 방법
//   * var: 변수
//   * val: final 변수 (상수) - let
/*
fun main(args: Array<String>) {
    var user = User("Tom", 42)
    // User user = new User();

    // user2의 참조를 다른 객체로 변경하는 것이 불가능합니다.
    val user2 = User("Tom", 42)
    // final User user2 = new User();
}
*/

// 7. 클래스 문법
/*
class User1 {
    val name: String
    val age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

class User2 constructor(val name: String, val age: Int)
class User3(val name: String, val age: Int)

fun main(args: Array<String>) {
    val user1 = User1("Tom", 42)
    val user2 = User2("Tom", 42)
    val user3 = User3("Tom", 42)
}
*/

/*
// 객체의 equals를 재정의 하였다면, 반드시 hashCode도 재정의 해야 한다.
//  => Effective Java
class User(val name: String, val age: Int) {
    // equals를 재정의하면 됩니다.
    // @Override
    // boolean equals(other: Object) {
    // Any: Object의 타입의 이름이 변경되었습니다.
    // Any?: Nullable
    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true

        // other: Any?
        if (other === null)
            return false

        // other: Any

        // User.class != other.getClass()
        if (javaClass != other.javaClass)
            return false

        other as User
        // other: Any -> User
        // 여기서부터 other의 타입이 User로 취급됩니다. - 스마트 캐스트

        return name == other.name && age == other.age
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}

//  Java: 참조 동등성(==), 객체 동등성(equals)
//  코틀린: 참조 동등성(===), 객체 동등성 + Null Check(==)
fun main(args: Array<String>) {
    val u1 = User("Tom", 42)
    val u2 = User("Tom", 42)

    println(u1 == u2)
    println(u1 === u2)

    // Int  타입은 null을 가질 수 없다.
    // Int? 타입은 null을 가질 수 있다.
    // val n: Int? = null
}
*/

















































