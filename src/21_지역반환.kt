package ex21

class Person(val name: String)

/*
fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found")
            return
        }
    }

    println("Alice is not found")
}
*/

inline fun List<Person?>.forEach2(action: (Person?) -> Unit) {
    for (e in this) {
        if (e == null)
            continue

        action(e)
    }
}

// 코틀린은 람다를 함수로 취급하지 않습니다.
// : 함수(호출) - 반환(return)

// * inline 함수에서는 람다가 호출한 함수의 컨텍스트에서 수행되기 때문에
//   반환을 수행할 수 있다.
/*
fun lookForAlice(people: List<Person?>) {
    // people.forEach {  }

    people.forEach2 hello@{ person ->
        if (person == null)
            // return@forEach2            // 지역 반환(local return)
            return@hello                  // Label의 이름을 직접 지정하는 것도 가능합니다.

        if (person.name == "Alice") {
            println("Found")
            return                     // lookForAlice에 대한 반환입니다.
                                       //  -> 비지역 반환(default)
                                       //  -> non local return
        }
    }

    println("Alice is not found")
}

fun main(args: Array<String>) {
    val people = listOf(null, Person("Alice"))
    lookForAlice(people)
}
*/

fun lookForAlice(people: List<Person>) {
//    people.forEach { person ->
//        if (person.name == "Alice") {
//            println("Found")
//            return
//        }
//    }
    // 람다가 아닌 무명 함수도 전달 가능합니다.
    people.forEach(fun(person) {
        if (person.name == "Alice") {
            println("Found")
            return      // 무명 함수에서는 무명 함수가 반환합니다.
                        // 무조건 지역 반환이 발생합니다.
        }
    })

    println("Alice is not found")
}


fun main(args: Array<String>) {
    val people = listOf(Person("Alice"), Person("Alice"))
    lookForAlice(people)
}

