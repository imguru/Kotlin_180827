package ex18

// Function Composition(함수 합성)
// f(x) -> y  -> g(y) -> z

// compose(f(x), g(y)) -> g(f(x))

// String::length : (String) -> Int
// Any::hashCode  : (Any) -> Int

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    val y = f(x)
    val z = g(y)
    z
}

fun main(args: Array<String>) {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // 문자열의 길이를 통해 해시 값을 구하는 함수를 합성한다.
    val fog: (String) -> Int = compose(f, g)

    val s1 = "Tom"
    val s2 = "Bob"
    println(fog(s1))
    println(fog(s2))
}
*/

// f(A) -> B -> g(B) -> C
infix fun<A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C = { x ->
    g(this(x))
}

fun main(args: Array<String>) {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // 문자열의 길이를 통해 해시 값을 구하는 함수를 합성한다.
    // val fog: (String) -> Int = compose(f, g)
    // val fog: (String) -> Int = f.compose(g)
    val fog: (String) -> Int = f compose g

    val s1 = "Tom"
    val s2 = "Bob"
    println(fog(s1))
    println(fog(s2))
}
















