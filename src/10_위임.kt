package ex10

// Delegation Pattern(위임)
//  1) 클래스 위임

// 객체 지향 설계에서 기존 클래스를 재사용하는 방법 2가지
// 1. 상속
//  : 깨지기 쉬운 기반 클래스의 문제

// 2. 위임
// : 아래 코드는 많이 사용하지만, 작성해야 하는 코드가 많습니다.

/*
class Panel2(x1: Int, x2: Int, y1: Int, y2: Int)
    : Rectangle(x1, x2, y1, y2)
*/


interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

open class Rectangle(val x1: Int,
                val x2: Int,
                val y1: Int,
                val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

/*
class Panel(val rectangle: Rectangle) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

// UIElement 인터페이스가 노출한 메소드에 대한 호출을 이를 구현한 Rectangle 객체로
// 위임한다.
//  => 반드시 프로퍼티로 만들 필요가 없다.
//  => 합성을 상속만큼 편리하게 사용할 수 있도록 해준다.
class Panel(rectangle: Rectangle) : UIElement by rectangle {
    override fun getWidth(): Int {
        return 42
    }
}


fun main(args: Array<String>) {
    val panel = Panel(Rectangle(10, 100, 30, 100))
    println(panel.getHeight())
    println(panel.getWidth())
}

























