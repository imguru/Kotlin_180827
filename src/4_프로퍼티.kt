package ex4_2

/*
public class User {
    private String name;    // Field
    private int age;        // Field

    // getter + setter = Accessor method
    // getter
    public int getAge() {
        return age;
    }

    // setter
    public void setAge(int age) {
        this.age = age;
    }
}
*/

// Property => getter + setter를 자동으로 생성하는 문법
//  : C#
/*
class User
{
    String name;
    {
        get; set;
    }

    int age;
    {
        get; private set;
    }
}
*/

/*
class User {
    // 프로퍼티
    //  1. getter + setter => var
    //  2. getter          => val
    val name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

/*
class User {
    val name: String
    // name(field) + getter
    var age: Int
    // age(field) + setter + getter
    // name 과 age 를 인자를 통해서 초기화하는 것이 아니라
    // 객체가 생성되는 시점에 초기화 하고 싶다.
    init {
        name = "Tom"
        age = 42
    }

    override fun toString(): String {
        return "User - $name, $age"
    }
}

fun main(args: Array<String>) {
    val u = User()
    println(u.name)  // u.getName()
    u.age = 43       // u.setAge(43)

    println(u)
}
*/

// firstName, lastName: Property         / Stored
// fullName: Backing field 없는 Property  / Calculated

class User(var firstName: String, var lastName: String) {
    var fullName: String
        get() {
            return "$firstName, $lastName"
        }

        // "Gildong, Hong"
        // "Gildong Hong"
        set(value) {
            val arr = value.split(" ", ",")
            firstName = arr[0]
            lastName = arr[1]
        }

}

fun main(args: Array<String>) {
    val user = User("Chansik", "Yun")
    println(user.fullName)
}

// 주의 사항 - 프로퍼티? 메소드?
// 1. 복잡한 코드는 메소드를 사용해야 한다.
// 2. 시간이 오래 걸리는 작업은 메소드를 사용해야 한다.
// 3. 프로퍼티의 값을 얻는 동작이 부수 효과의 원인이 되서는 안된다.
// 4. 프로퍼티 연산에서 예외를 던지면 안된다.
// 5. 타입을 다른 타입으로 변환하는 동작은 메소드를 사용해야 한다,.
//      : toString()
// 6. 객체를 복제하는 작업은 메소드가 되어야 한다.

// val n = 42
// val n2: Long = n.long
//     n2: Long = n.toLong()


























