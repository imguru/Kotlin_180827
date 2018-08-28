package ex9_2

import java.io.File

/*
// 1. Object 선언 - Singleton
// => 상속 또는 인터페이스를 구현하는 것이 가능합니다.
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = false)
    }
}

// 2. Object 중첩해서 만들기
// => 상속 또는 인터페이스를 구현하는 것이 가능합니다.
data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}

fun main(args: Array<String>) {
    val people = listOf(Person("Tom"), Person("Bob"))
    people.sortedWith(Person.NameComparator)
}
*/


/*
fun main(args: Array<String>) {
    val files = listOf(File("/z"), File("/A"))

    val sortedFiles = files.sortedWith(CaseInsensitiveFileComparator)
    println(sortedFiles)


    val sortedFiles2 = files.sortedWith(CaseInsensitiveFileComparator)
    println(sortedFiles2)
}
*/

/*
fun main(args: Array<String>) {
    val files = listOf(File("/z"), File("/A"))

    val sortedFiles = files.sortedWith(object: Comparator<File> {
        override fun compare(o1: File, o2: File): Int {
            return o1.path.compareTo(o2.path, ignoreCase = false)
        }
    })

    println(sortedFiles)


    val sortedFiles2 = files.sortedWith(object: Comparator<File> {
        override fun compare(o1: File, o2: File): Int {
            return o1.path.compareTo(o2.path, ignoreCase = false)
        }
    })
    println(sortedFiles)

}
*/

/*
// 3. 동반 객체 활용 - 정적 팩토리 메소드(static factory method)

// Design Pattern - OOP
// Idioms - 특정한 언어의 기법
//   C++ Idioms  - CRTP, RAII
//   Java Idioms - static factory method / IODH

fun getFacebookName(accountId: Int): String {
    return "facebook@$accountId"
}

/*
class User {
    val nickname: String

    constructor(email: String) {
        nickname = email.substringBefore('@')
    }

    constructor(facebookAccountId: Int) {
        nickname = getFacebookName(facebookAccountId)
    }
}

fun main(args: Array<String>) {
    val email = "chansik.yun@gmail.com"
    val id = 1313123211

    val user1 = User(email)
    val user2 = User(id)
}
*/

// 생성자의 한계
// 1. 이름을 변경할 수 없다.
//    User("aaa") vs User(100)
// 2. 매번 새로운 객체를 생성해야 한다.
//   : 객체 생성 정책을 변경할 수 없다.
// => 정적 팩토리 메소드


class User private constructor(val nickname: String) {
    companion object {
        val cache = HashMap<String, User?>()

        fun newSubscribingUser(email: String): User {
            val userId = email.substringBefore('@')
            if (cache.containsKey(userId)) {
                return cache[userId]!!
            }

            val obj = User(userId)
            cache[userId] = obj
            return obj
        }

        fun newFacebookUser(accountId: Int): User {
            return User(getFacebookName(accountId))
        }
    }

//    constructor(email: String) {
//        nickname = email.substringBefore('@')
//    }
//
//    constructor(facebookAccountId: Int) {
//        nickname = getFacebookName(facebookAccountId)
//    }
}

fun main(args: Array<String>) {
    val email = "chansik.yun@gmail.com"
    val id = 1313123211

    // val user1 = User(email)
    val user1 = User.newSubscribingUser(email)
    // val user2 = User(id)
    val user2 = User.newFacebookUser(id)
}
*/

/*
// JSON
//  { "name": "Tom", "age": 42 }

// JSON -> Map<String, Any>
// 4. 동반 객체에서도 인터페이스를 구현하는 것이 가능합니다.
interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

data class Person(val name: String, val age: Int) {
    companion object : MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            val age = map["age"] as Int

            return Person(name, age)
        }
    }
}

fun<T> loadFromMap(factory: MapFactory<T>): T {
    val map = mapOf("name" to "Tom",
            "age" to 42)

    return factory.fromMap(map)
}

fun main(args: Array<String>) {
    val person = loadFromMap(Person)
    println(person)

//    val person = Person.fromMap(map)
//    println(person)
}
*/

// 3. 무명 객체(Anonymous Object)
interface MouseAdapter {
    fun mouseClick()
    fun mouseEntered()
}

/*
class Window {
    lateinit var mouseListener: MouseAdapter
    // lateinit var
    //  : var를 나중에 초기화 하겠다는 의미로 사용됩니다.
    //    만약 초기화하지 않고 사용할 경우 예외가 발생합니다.

    fun click() {
        mouseListener.mouseClick()
    }

    fun enter() {
        mouseListener.mouseEntered()
    }
}
*/

class Window {
    var mouseListener: MouseAdapter? = null
    // lateinit var
    //  : var를 나중에 초기화 하겠다는 의미로 사용됩니다.
    //    만약 초기화하지 않고 사용할 경우 예외가 발생합니다.

    fun click() {
        mouseListener?.mouseClick()
    }

    fun enter() {
        mouseListener?.mouseEntered()
    }
}

fun main(args: Array<String>) {
    val window = Window()
//    window.mouseListener = object : MouseAdapter {
//        override fun mouseClick() {
//            println("mouse clicked")
//        }
//
//        override fun mouseEntered() {
//            println("mouse entered")
//        }
//    }

    window.click()
    window.enter()
}













