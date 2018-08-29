package ex15

// 고차(계) 함수
//  : High Order Function
//  정의: 함수의 객체의 참조를 인자로 전달받거나,
//       함수의 참조를 반환하는 함수

// 고차 함수의 장점 - 함수의 인자를 받는 경우
//  1. 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.
//  2. 함수의 재사용성을 올린다.

// Kotlin's Collection
// 1. listOf, mapOf, setOf
//    List<E>, Map<K, V>, Set<E>

// 2. mutableListOf -> MutableList<E>
//    mutableMapOf  -> MutableMap<K,V>
//    mutableSetOf  -> MutableSet<E>

// 2. Collection 인터페이스를 세분화 하였습니다.
//    List<E>
//      -> List<E>        : 컬렉션을 수정하는 기능이 존재하지 않습니다.
//      -> MutableList<E>

// List<E>
//   ArrayList<E>
//   LinkedList<E>

// Map<K, V>
//   HashMap<K, V>
//   TreeMap<K, V>

// Set<E>
//   HashSet<E>
//   TreeSet<E>
/*
fun filterEvens(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 0)
            result.add(e)
    }

    return result
}

fun filterOdds(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (e % 2 == 1)
            result.add(e)
    }

    return result
}


fun main(args: Array<String>) {
    val ints = listOf(1, 2, 3, 4, 5)

    val evens = filterEvens(ints)
    val odds = filterOdds(ints)

    println(evens)
    println(odds)
}
*/

// Collection에서 요소를 Filter하는 방법은 변하지 않는다.
// 어떤 요소를 Filter 할지에 대한 정책은 변한다.
//  => 변하지 않는 전체 알고리즘에서 변해야 하는 정책은 분리되어야 한다.
//  => 공통성과 가변성의 분리
//  => 전략 패턴

// 함수에서 정책을 분리하는 방법
//  1) Java - 동작 파라미터화(Interface)
//   : 자바는 전통적으로 함수에 대한 참조를 전달할 수 없습니다.

/*
interface Predicate {
    fun test(e: Int): Boolean
}

fun filter(data: List<Int>, predicate: Predicate): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (predicate.test(e))
            result.add(e)
    }

    return result
}

fun filterEvens(data: List<Int>) = filter(data, object : Predicate {
    override fun test(e: Int) = e % 2 == 0
})

fun filterOdds(data: List<Int>) = filter(data, object : Predicate {
    override fun test(e: Int) = e % 2 == 1
})

fun main(args: Array<String>) {
    val ints = listOf(1, 2, 3, 4, 5)

    val evens = filterEvens(ints)
    val odds = filterOdds(ints)

    println(evens)
    println(odds)
}
*/

/*
//  2) Kotlin - 함수 기반의 정책 전달
interface Predicate {
    // (Int) -> Boolean
    fun test(e: Int): Boolean
}

fun filter(data: List<Int>, fn: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (e in data) {
        if (fn(e))
            result.add(e)
    }

    return result
}

fun isEven(e: Int) = e % 2 == 0
fun isOdd(e: Int) = e % 2 == 1

fun filterEvens(data: List<Int>) = filter(data, ::isEven)
fun filterOdds(data: List<Int>) = filter(data, ::isOdd)

// fun test(e: Int) = ...

fun main(args: Array<String>) {
    val ints = listOf(1, 2, 3, 4, 5)

//    val evens = filterEvens(ints)
//    val odds = filterOdds(ints)
//
//    println(evens)
//    println(odds)

    var result: List<Int> = emptyList()

    // 1. 무명 함수
    result = filter(ints, fun(e: Int) = e % 2 == 0)

    // 2. 코드 조각에 대한 참조를 전달하는 것도 가능합니다.
    // '코드 조각에 대한 참조' = Lambda(람다)
    result = filter(ints, { e: Int ->
        e % 2 == 0
    })

    // 3. 타입 추론이 가능합니다.
    result = filter(ints, { e -> e % 2 == 0 })

    // 4. 인자가 1개만 전달될 경우, 인자의 이름을 생략하는 것도 가능합니다.
    //   첫번째 인자 -> it
    result = filter(ints, { it % 2 == 0 })

    // 5. 마지막 인자로 전달될 경우, 함수의 외부로 블록을 빼는 것도 가능합니다.
    result = filter(ints) { it % 2 == 0 }
}
*/

// 람다가 무명 객체보다 사용하기 좋다.
// 1. 보일러플레이트가 없다.
// 2. 성능이 좋다.
// 3. this를 직관적으로 사용할 수 있다.

interface OnClickListener {
    fun onClick()
}



class Button {
    var onClickListener: OnClickListener? = null
    var onClick: (() -> Unit)? = null

    fun click() {
        onClickListener?.onClick()
        onClick?.invoke()
    }
}

class Intent(context: MainActivity, clazz: Class<*>)

class UserActivity
class MainActivity {
    val nextButton = Button()

    fun onCreate() {
        nextButton.onClick = {
            val intent = Intent(this, UserActivity::class.java)
            // startActivity(intent)
        }



        nextButton.onClickListener = object: OnClickListener {
            override fun onClick() {
                // MainActivity.this
                val intent = Intent(this@MainActivity,
                        UserActivity::class.java)


                // val intent = Intent(this, UserActivity.class)
                // startActivity(intent)
            }
        }
    }
}























