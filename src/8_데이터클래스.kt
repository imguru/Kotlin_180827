package ex8

import java.util.*

// Java - DTO(Data Transfer Object)
//        VO(Value Object)
//        DAO(Database Access Object)

// Kotlin -> Data class 로 작성하면 됩니다.

/*
class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User(name=$name, age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (other === null)
            return false
        if (other === this)
            return true

        if (other.javaClass !== javaClass)
            return false

        other as User
        return other.name == name && other.age == age
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}
*/

// data class
// 1. 객체를 문자열로 표현하는 toString() 을 자동으로 생성한다.
// 2. 객체의 동등성을 판단하는 두 개의 메소드를 자동으로 생성한다.
//    equals() / hashCode()

// Primary Constructor
data class User(val name: String, val age: Int)

// 아래의 클래스는 data class가 될 수 없습니다.
// data class Hello


fun main(args: Array<String>) {
    val user = User("Tom", 42)
    val other = User("Tom", 42)

    println(user)
    // user != null && user.equals(other)
    if (user == other) {
        println("Same user")
    } else {
        println("Diff user")
    }

    // copy()를 통해서 객체를 생성하는 방법을 제공한다.
    val user2 = user.copy()

    // 특정 필드의 값을 변경하는 것도 가능합니다.
    val user3 = user.copy(name = "Bob")
    println(user3)






}