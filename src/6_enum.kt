package ex6

// 1. enum 키워드는 soft keyword 입니다.
// enum Color
/*
enum class Color {
    RED, ORANGE, YELLOW, GREEN, INDIGO
}
*/

// 1. 객체이기 때문에 프로퍼티나 메소드를 가지는 것이 가능합니다.
// 2. 상수로 쓰기에는 메모리 소모량이 있습니다.

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    INDIGO(75, 0, 130);

    // 메소드를 정의하기 위해서는 반드시 메소드와 요소간의 경계 토큰이 필요합니다.
    // => ;

    fun rgb() = (r * 255 + g) * 256 + b

    // switch 가 없습니다.
    //  => when 을 통해서 대체 가능합니다.

    // when
    //  => 문(statement)이 아닌 식(expression)입니다.
    //   : 문은 결과가 없지만, 식은 결과가 존재합니다.
    /*
    switch (this) {
        case RED: return "RED"
        case ORANGE: return "ORANGE"
    }

    return when (this) {
        case RED: "RED"
        case ORANGE: "ORANGE"
    }
    */
    fun getName(): String {
        return when (this) {
            // case RED
            RED -> "Red"
            ORANGE -> "ORANGE"
            YELLOW -> "YELLOW"
            GREEN -> "GREEN"
            INDIGO -> "INDIGO"
        }
    }

    fun getName2(): String {
        return when (this) {
            // case RED
            RED, ORANGE, YELLOW -> "붉은 계통"
            GREEN, INDIGO -> "푸른 계통"
        }
    }


}

// when 식의 인자로 상수 뿐 아니라 임의의 객체도 허용합니다. )
fun mix(c1: Color, c2: Color): Color {
    // setOf(c1, c2) -> Set<Color>
    return when (setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        else -> throw Exception("이상한 색")
    }
}

fun main(args: Array<String>) {
    // => class 키워드랑 함께 사용될 때만 키워드로 취급됩니다.
    val enum = 42

    val red = Color.RED
    println(red.rgb())

    // if 도 '문'이 아니라 '식'입니다.
    val age = 30
    // if 가 식(expression)이면,
    //    val name = if (age > 30) {
    //        "30대"
    //    } else {
    //        "미상"
    //    }

    // if 가 문(statement)이면
    var name: String? = null
    if (age > 30) {
        name = "30대"
    } else {
        name = "미상"
    }
}















