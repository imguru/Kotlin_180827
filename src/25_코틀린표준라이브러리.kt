package ex25

import java.io.File
import java.util.*
import kotlin.streams.toList

// 1. 조건 확인 함수
//  : 코틀린은 예외 처리가 강제되지 않습니다.
//     : Checked Exception 없습니다.
//  => 인자로 받은 표현식이 참이 아닌 경우 예외를 발생시키는 함수

// check    -> IllegalStateException
// require  -> IllegalArgumentException
/*
fun logMessage(filename: String, message: String) {
    val file = File(filename)

//    check(file.exists())   // IllegalStateException
//    if (!file.exists()) {
//        throw IllegalArgumentException()
//    }

    require(message.length > 5)  // IllegalArgumentException
}

fun log(message: String?) {
    // Null 인지 체크해서, Null 이면 예외를 발생시킵니다.
    // Null 이 아니면, Null 아닌 값을 반환합니다.
    val value = requireNotNull(message)

    println(value)
}


fun main(args: Array<String>) {
    logMessage("file.log", "hello")
}
*/

// void
//  Kotlin: Unit: 결과가 없는 반환값
//          Nothing: 함수가 정상적으로 반환될 수 없음.

/*
fun foo(): Nothing {
    println("foo")

    throw IllegalStateException()
}


// 2. 명시적인 실행 종료 함수
fun main(args: Array<String>) {
    // TODO("아직 작성중입니다.")
    val scanner = Scanner(System.`in`)
    val cmd = scanner.nextInt()

    if (cmd < 0) {
        // throw IllegalStateException()
        error("실행할 수 없습니다.")
    }
}
*/


// 명령형 프로그래밍: 알고리즘을 직접 작성해서 문제를 해결하는 방법
// 선언적 프로그래밍: 일반적 알고리즘 함수를 조합해서 문제를 해결하는 방법
//   => SQL

// select user from *
//        -
// DB / Collection / Excel / XML / JSON
// => Expression Tree

// 3. 스트림 함수 <- C# LINQ(언어 독립적인 중립 쿼리)
// - Sequence API(Java 6 - Kotlin 부터 사용할 수 있습니다)
// - Stream API(Java 8 - Kotlin)

//  : Java 8 에서는 컬렉션에 포함된 요소들을 편리하게 다룰 수 있는 기능을 제공합니다.
//    컬렉션 포함된 데이터를 변환하거나, 새로운 데이터를 생성하거나 등의
//    다양한 작업을 편리하게 수행할 수 있습니다.
/*
fun main(args: Array<String>) {
    val cities = listOf("Seoul", "Suwon", "Daegu", "Busan")

    //  1. 변환(transform(C++), map(Kotlin))
    // 명령형
    val result1 = mutableListOf<String>()  //
    for (city in cities) {
        if (city.startsWith("S"))
            result1.add(city.toUpperCase())
    }
    println(result1)

    // List<>
    // MutableList<>
    // 선언형
    //  : 디버깅이 어렵다.
    val result2 = cities
            .filter { it.startsWith("S") }
            .map { it.toUpperCase() }
    println(result2)
}
*/

// fun length(str: String) = str.length
// fun print(value: Int) = println(value)

/*
// fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
fun main(args: Array<String>) {
    val cities = listOf("Seoul", "Suwon", "Daegu", "Busan")

    // cities.map(::length).forEach(::print)

    // 기존 메소드의 참조를 전달할 수 있다면, 가독성이 더 좋다.
    cities.map(String::length)
            .forEach(::println)

    // map - 변환
    // transform: (String) -> Int
    cities.map {
        it.length
    }.forEach {
        // println(it)
    }

    // List<String> -> List<String?>
    // map을 사용하면서 특정 요소에 대한 결과가 null인 경우,
    // null을 자동으로 필터링 하는 메소드를 제공합니다.
    //  -> mapNotNull
    cities.mapNotNull { city ->
        if (city.startsWith("S"))
            city
        else
            null
    }.forEach(::println)

    // flatMap - 연산(transform)의 결과가 Collection인 경우
    //           1차원으로 평탄화 해준다.
    val numbers = 1..10
//    val result = numbers.map {
//        0..it
//    }
    val result = numbers.flatMap {
        0..it
    }

    // [ [ 0, 1 ], [ 0, 1, 2 ] ... [ 0, .. 10 ] ]
    // println(result)

    // groupBy
    val cities2 = listOf("Seoul", "Suwon", "Daegu", "Busan")

    // List<String> -> Map<Char, List<String>>
    val result2 = cities2.groupBy { city ->
        city[0] // Char
    }

    result2.forEach { (ch, location) ->
        println("group=$ch, name=$location")
    }

    // println(result2)
}
*/

/*
fun main(args: Array<String>) {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    // distinct: 중복된 항목을 걸러낸 결과를 반환합니다.
    var result = list.map { it % 3 }.distinct()
    println(result)

    val users = listOf("Tom", "Bob", "Alice")
    var result2 = users.distinctBy {
        it.length
    }

    println(result2)

    // take: 요소를 추출하는 연산입니다.
    println(list.take(5))
    println(list.takeWhile {
        it < 5
    })

    // TODO: takeIf

    // first
    // last
    println(list.first())
    println(list.first {
        it > 5
    })

    println()
    // drop
    // 원하지 않는 요소를 제거한 컬렉션을 반환합니다.
    // drop / dropLast / dropWhile / dropLastWhile
    // list.drop(1).forEach(::println)
    println(list.dropWhile {
        it < 5
    })
}
*/

/*
// 연관된 두 개의 컬렉션을 묶어서 하나의 컬렉션을 생성하고 싶다.
//  : 순서가 동일해야 한다.
//  -> zip

data class Country(val country: String, val code: String)

fun main(args: Array<String>) {
    val countries = listOf("Korea", "United States", "Japan")
    val codes = listOf("KR", "US", "JP")

    countries.zip(codes) { country, code ->
        Country(country, code)
    }.forEach(::println)

    // List<Country>

}
*/
// Sequence API 한계
//  1. 요소의 개수가 적을 경우, Stream API보다
//     빠르게 동작한다.
//     하지만, 많은 데이터를 처리할 수 없다.
//  2. 병렬화 기능이 제공되지 않는다.

// Stream API 동작 방식
//  1. 메모리 한꺼번에 올려 처리하는 것이 아니라
//     순회해서 하나씩 처리한다.
//  2. 쉽게 병렬화가 가능하다.
//    stream()      -> Single Thread
// parallelStream() -> Multi Thread
fun main(args: Array<String>) {
    val cities = listOf("A", "AA", "BBB", "CCCC", "DDD", "EEEE")

    val result = cities
            .map(String::length).filter { it % 2 == 0 }.toList()

    println(result)
}












