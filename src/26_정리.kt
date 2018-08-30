package ex26

import ex13.User as User13
import ex1.User as User1

// import ex1.C as MyClass

// class A { fun go(); }
// class C { fun go(); }

open class Car

class Sedan : Car() {
    fun go() = println("Sedan go")
}

class Truck : Car() {
    fun move() = println("Truck move")
}

// 아래코드의 문제점
fun foo(car: Car) {
    // car이 truck의 move를 호출하고 싶다.

    val truck = car as? Truck
    // 잘못된 타입을 경우, null을 반환합니다.
    truck?.move()

    // car.move()  // Smart Cast : car -> truck


    // Reflection
    if (car.javaClass == Truck::class.java) {
        car as Truck
        car.move()
    }

    // is == instanceOf
    if (car !is Truck) {
        // car.move()
    } else {
        car.move()
    }
}

// typedef
//  => typealias

typealias Predicator = (String) -> Boolean
fun filter(data: List<String>, predicate: Predicator) : List<String> {
    return data
}


fun main(args: Array<String>) {
    // val car: Car = Truck()
    // val car: Car = Sedan()
    // foo(car)

    // val list = listOf(1, 2, 3, 4, 5)

    // !in
    // 'a' -> 'a'..'c'
    if ('a' !in 'a'..'c') {
        println("포함되어 있지 않습니다.")
    }


}












