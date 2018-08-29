package ex16

// SAM(Single Abstract Method)
// => Java @Functional Interface

// 자바의 Functional Interface를 Kotlin에서 람다로 사용하는 것을
// 허용해줍니다.

// 코틀린에서는 SAM을 지원하지 않습니다.
// => 함수 타입에서만 람다를 사용할 수 있으므로, 주의해야 합니다.
/*
interface OnClickListener2 {
    fun onClick()
}
*/

/*
fun main(args: Array<String>) {
    val button = Button()

    button.setOnClickListener {
        println("Lambda onClicked")
    }

    button.setOnClickListener(object : OnClickListener {
        override fun onClick() {
            println("Anonymous onClicked")
        }
    })
}
*/

// filter
fun main(args: Array<String>) {
    val list = listOf(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
    println(list.filter { it % 2 == 1 })

    // val sorted = list.sorted()
    val sorted = list.sortedWith(object : Comparator<Int> {
        override fun compare(o1: Int, o2: Int): Int {
            return o2 - o1
        }
    })

    // SAM이 Generic 형태로 되어 있다면, 람다를 사용할 때, 타입 정보를
    // 추가적으로 명시해야 합니다.
    val sorted2 = list.sortedWith(Comparator<Int> { o1, o2 -> o2 - o1 })
    println(sorted2)
}


















