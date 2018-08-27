package ex5

/*
// Interface
// abstract class Mp3Player {
//     abstract fun play()
//     abstract fun stop()
// }

// Interface: 교체 가능한 유연한 설계를 가능하게 해준다.
//  치명적인 문제점?
//   : 변화에 대응하기 어렵다.
//   => 인터페이스가 기본 구현을 제공하는 것이 더 좋다.

interface Mp3Player {
    fun play()
    fun stop()

    // 깨지기 쉬운 하위 클래스 문제
    fun playOneMinute() {
        play()
        //...
        stop()
    }
}


// class Apple extends AbstractMp3  - 상속
//  => class Apple : AbstractMp3()

// class Apple implements Mp3Player - Interface 구현
//  => class Apple : Mp3Player

class Apple: Mp3Player {
    override fun play() {
        println("Play mp3 with iPod")
    }

    override fun stop() {
        println("Stop")
    }
}

class Person {
    // 강한 결합
    //  : 구체적인 타입을 의존하는 것
    fun playMusic(mp3: Apple) {

    }

    // 약한 결합(느슨한 결합)
    //  : 인터페이스나 추상 클래스에 의존하는 것
    fun playMusic(mp3: Mp3Player) {

    }
}
*/

/*
// 코틀린의 인터페이스는 프로퍼티도 정의할 수 있습니다.
interface Clickable {
    val name: String
        get() = "Hello"
    // val: getter
    // var: getter + setter

    fun click()

    fun showOff() {
        println("I'm Clickable")
    }
}

interface Focusable {
    val name: String
    fun showOff() {
        println("I'm Focusable")
    }
}

// 동일한 기본 구현을 제공하는 인터페이스를 다중으로 구현할 경우
// 다이아몬드 상속의 문제가 발생할 수 있습니다.
class Button : Clickable, Focusable {
    // 부모(클래스 또는 인터페이스)가 정의한 프로퍼티를 재정의하는 방법
    override val name: String
        get() {
            return super<Clickable>.name
            // return "Button"
        }

    override fun click() {
        // ...
    }

    override fun showOff() {
        // super<Clickable>.showOff()
        // super<Focusable>.showOff()
        // println("I'm Button")
    }
}
*/



// Effective Java
//  => 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예기치 않게 변경될 수 있다.
//  => 상속을 위한 문서를 갖추거나, 그럴 수 없다면 상속을 금지하라.
open class Car {  // Base
    constructor() {
        initialize()
    }

    // 생성자 안에서 호출되는 메소드는 반드시 final 이거나
    // private 이어야 한다.
    private fun initialize() {

    }

    // final void go() {}
    // 자식이 메소드를 함부로 오버라이드 하는 것을 방지하도록,
    // default가 오버라이드 금지 입니다.
    open fun go() {
        println("Car go")
    }
}

// final class Car {}
// open: 해당 클래스를 상속 가능하도록 한다.

// interface, abstract class -> open

class Truck : Car() { // Derived

    fun initialize() {

    }

    override fun go() {

    }
}

// is-a 관계가 성립될 때만 '상속'을 이용해야 합니다.
// => Derived <is a> Base
//    Truck is a Car

// 현대적인 객체 지향 설계에서는 상속을 지양해야 합니다.
//  => 상속의 목적?
//    : '다형성'


































