package ex8

import java.util.*

// Java - DTO(Data Transfer Object)
//        VO(Value Object)
//        DAO(Database Access Object)

// Kotlin -> Data class 로 작성하면 됩니다.

/*
class User(val name: String, val age: Int) {

    // 연산자 오버로딩 함수 - 비구조화 기능
    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int {
        return age
    }

    operator fun component3(): String {
        return "$name - $age"
    }


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
data class User(val name: String, val age: Int) {
    operator fun component3(): String {
        return "$name - $age"
    }
}

// 아래의 클래스는 data class가 될 수 없습니다.
// data class Hello

// 자바의 자원
//  1. 메모리 자원: new -> JVM이 관리한다.
//  2. 비메모리 자원: File, Thread, Process (운영체제 자원)
//    : 반드시 명시적인 종료 메소드를 통해 해제해야 한다.

// finalize()는 제공되지 않습니다.
// 1. 호출 시점이 명확하지 않다.
// 2. 호출이 보장되지 않는다.
// 3. 자식이 finalize를 오버라이드 하면 부모의 finalize가 제대로 호출되지 않는다.

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

    // 비구조화 기능
    val users = listOf(user)
    // List<User>

    // for (User e : users)
    for (e in users) {
        println(e.name)
        println(e.age)
    }

    println(users[0])

    // 비구조화 순회
    for ((_, _, all) in users) {
        println(all)
    }
}











