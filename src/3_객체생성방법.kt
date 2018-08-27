package ex3

// Visual Studio
//    Solution
//      Project  - myprog.exe
//      Project  - mylib.lib

// Intelli J
//    Project
//      Module   - prog.jar
//      Module   - mylib.jar

// 1. Default Value 기능을 제공합니다.
// 2. @JvmOverloads 어노테이션을 생성자에 지정하면
//    생성자 오버로딩 코드를 자동으로 생성해줍니다.

class User
@JvmOverloads
constructor(val name: String,
            val address: String = "",
            val age: Int = 0,
            val money: Int = 0)

fun main(args: Array<String>) {
    val user1 = User("Tom")
    val user2 = User("Tom", "Suwon")
    val user3 = User("Tom", "Suwon", 42, 0)

    val user4 = User(name = "Tom", age = 42, address = "Suwon", money = 0)
}





















