package ex17_2

// 고차함수2 - 함수를 반환하는 함수
// => 실행 시간에 함수를 생성하는 기술
//   : 합성 함수, 커링

// foo()는 문자열을 인자로 받아서 거꾸로 뒤집는 함수를 반환한다.
/*
fun foo(): (String) -> String = { str ->
    str.reversed()
}
*/

/*
// 단일 표현식을 사용하는 것이 더 좋다.
fun foo(): (String) -> String {
    return { str ->
        str.reversed()
    }
}

fun main(args: Array<String>) {
    val reverse = foo()

    val str = "Hello"
    println(reverse(str))
}
*/

// Filter에 함수를 생성하는 부분을 적용해봅시다.
// 1. Lambda - 익명 코드 블록
// 2. Closure
//   : 블록 외부의 변수에 암묵적으로 참조 가능한 기술
fun modulo(k: Int, r: Int): (Int) -> Boolean = { value: Int ->
    value % k == r
}

// fun isEven(value: Int) = value % 2 == 0

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5)

    // (Int) -> Boolean
    // isEven, isOdd: 동적으로 생성된 정책 함수
    // => 별도의 코드를 작성하지 않아도 된다.

    val isEven = modulo(2, 0)
    var result = list.filter(isEven)
    println(result)

    val isOdd = modulo(2, 1)
    result = list.filter(isOdd)
    println(result)
}






