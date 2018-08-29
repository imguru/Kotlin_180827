package ex14

// Pair<String, Any>
infix fun Any.pair(value: Any) = Pair(this, value)

// infix: 중위 함수
// -> 인자가 하나뿐인 메소드나 인자가 하나뿐인 확장 함수에
//    대해서 중위 호출을 사용할 수 있습니다.

// Pair<A, B>
fun main(args: Array<String>) {
//    val pair1 = Pair("name", "Tom")  // Pair<String, String>
//    val pair2 = Pair("age", 42)      // Pair<String, Int>

//    val pair1 = pair("name", "Tom")  // Pair<String, String>
//    val pair2 = pair("age", 42)      // Pair<String, Int>

//    val pair1 = "name".pair("Tom")
//    val pair2 = "age".pair(42)

    val pair1 = "name" pair "Tom"
    val pair2 = "age" pair 42

    val pair3 = "name" to "Tom"
    val pair4 = "age" to 42

    val map = mapOf("name" to "Tom",
            "age" to 42)

    // Pair는 구조 분해(비구조화) 선언(destructuring declaration)을 지원한다.
    val (key, value) = pair1
}














