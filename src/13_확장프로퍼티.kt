// 프로퍼티
// 1. '필드'가 존재하는 프로퍼티 - 불가능합니다.
// 2. 필드가 존재하지 않는 프로퍼티 - 확장이 가능합니다.

// 프로퍼티를 확장하는 방법
val String.lastChar: Char
    get() = get(length - 1)
// get() = this[length - 1]

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

fun main(args: Array<String>) {
    println("Hello".lastChar)

    val sb = StringBuilder("Hello, world?")
    sb.lastChar = '!'

    println(sb.toString())
}