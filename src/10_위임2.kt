package ex10_2

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

// 1) 클래스 위임
// 2) 프로퍼티 위임
//  : 프로퍼티에 대한 get / set의 동작을 다른 객체에게 위임하는 것도 가능하다.

/*
class SampleDelegate {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        return "$thisRef - ${property.name}"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("$thisRef - ${property.name} to $value")
    }
}

class User {
    var name: String by SampleDelegate()
    // user.name -> getter -> getValue
}

fun main(args: Array<String>) {
    val user = User()
    println(user.name)

    user.name = "Bob"
}
*/

/*
// 코틀린이 이미 제공하는 표준 델리게이트를 살펴봅시다.
fun foo() : String {
    // 시간이 오래 걸리는 작업
    println("foo가 호출되었음")
    return "foo"
}

class User {
    init {
        println("User 객체 생성되었음")
    }

    // name을 처음 접근하는 시기에 초기화를 수행하고 싶다. - 늦은 초기화
    //  1. val 에서만 사용할 수 있다.
    //  2. 스레드 안전성도 제공한다.
    /*
    val name: String by lazy {
        foo()
    }
    */

    lateinit var name: String
    // 1. var 에서만 사용이 가능하다.
    // 2. setter / getter 재정의가 불가능하다.
    // 3. 일반적인 원시타입에는 사용이 불가능하다.
    //    (Int, Long, Float, Double)
}

fun main(args: Array<String>) {
    val user = User()

    user.name
}
*/

// 2. KVO(Key-Value Observation)
//  : 프로퍼티의 값이 변경에 따라서 수행되어야 하는 로직을 캡슐화 할 수 있다.
/*
class TextView {
    var text: String = ""
        set(value) {
            field = value
            println("Update TextView text to $text")
        }
}

// List<User> items
// RecyclerAdapter adapter
// adapter.setItems(items);

class MainActivity {
    /*
    var name: String = ""
        set(value) {
            field = value
            nameTextView.text = name
        }
    */
    /*
    var name: String {
        didSet {
            //...
        }
    }

    */

    // name의 값이 변경될 때마다 호출되는 로직
    var name by Delegates
            .observable("") { _, old, new ->
        println("$old -> $new")
        nameTextView.text = new
    }

    var nameTextView = TextView()

}
fun main(args: Array<String>) {
    val activity = MainActivity()

    activity.name = "Tom"
    activity.name = "Bob"
    // activity.nameTextView.text = activity.name
}
*/

// 3. KVC(Key-Value Coding)
// Javascript
// let user = {
//    name: "Tom",
//    age:  42
// };

// 직접 접근
//  : 존재하지 않는 이름일 경우 오류가 발생합니다.
//   user.name

// 간접 접근
//  : 존재하지 않을 경우 예외가 발생합니다.
//   user["name"]

// OCP
//  : Open - Close Principle
//  확장에는 열려 있고, 수정에는 닫혀 있어야 한다.
//  => 새로운 기능이 추가되어도, 기존 코드는 수정되면 안된다.

/*
class User(map: Map<String, Any>) {
    val name: String
    val age: Int

    // Property의 내용이 변경되거나, 새롭게 추가될 때마다 아래 초기화 블록은
    // 수정되어야 합니다.
    init {
        name = map["name"] as String
        age = map["age"] as Int
    }

    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }
}
*/

/*
// val name: String by map
//  getter + setter
//  get() {
//     return map["name"]
//  }

class User(map: Map<String, Any>) {
    val name: String by map  // map[property.name]
    val age: Int by map
    val address: String by map

    override fun toString(): String {
        return "User(name='$name', age=$age, address=$address)"
    }
}

class UserResponse(map: Map<String, Any>) {
    val user: User by map

    override fun toString(): String {
        return "UserResponse(user=${user.toString()})"
    }
}



fun main(args: Array<String>) {
    val map = mapOf("name" to "Tom", "age" to 42, "address" to "Suwon")
    val user = User(map)

    val response = mapOf("user" to user)
    println(UserResponse(response))

    // println(user)
}
*/


// 4. vetoable: Validation의 조건에 충족되지 않으면 값이 변경되지 않습니다.

class User {
    var name: String
            by Delegates.vetoable("Gildong") { p, old, new ->
        new.length >= 5
    }
}

fun main(args: Array<String>) {
    val user = User()

    user.name = ""
    println(user.name)
}





