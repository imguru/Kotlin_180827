package ex12_2

// 확장 함수의 한계(특징)
// 1. 확장 함수는 정적 바인딩을 합니다.
//   : 부모와 자식이 동일한 메소드를 확장 함수로 가지고 있을 경우,
//     타입에 의해서 어떤 함수가 호출될지 결정됩니다.

// 2. 만약 동일한 시그니쳐의 함수가 이미 클래스 안에 존재한다면,
//    확장함수로 덮어쓸 수 없습니다.

open class View {
    open fun click() = println("View clicked")       // 1
    fun showOff() = println("I'm real View")
}

class Button : View() {
    override fun click() = println("Button clicked") // 2
}

// 오버로딩의 형태로 사용하는 것은 가능합니다.
fun View.showOff(message: Int) = println("I'm View")
fun Button.showOff() = println("I'm Button")


// 바인딩: 어떤 함수를 호출하는가?
// 정적 바인딩(Static Binding)
//  : 컴파일 타임에 어떤 함수를 호출할지 결정하는 것

// 동적 바인딩(Dynamic Binding)
//  : 런타임에 현재 변수가 가지고 있는 객체의 따라 어떤 함수가 호출될지
//    변하는 것
fun main(args: Array<String>) {
    val button: View = Button()

    button.click()      // Button::click
    button.showOff()    // View::showOff
}