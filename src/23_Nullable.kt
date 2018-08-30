package ex23

/*
// Nullable, Optional, Maybe
//   : C#, Java 8, Swift, Kotlin

// Null 안정성
//  Null Pointer 참조의 오류는 소프트웨어 오류에 있어서
//  큰 비중을 차지하고 있다.
//  : 토니 호어

fun getName(): String? = null

fun main(args: Array<String>) {
    // 1. 일반 타입에 null을 대입하는 것은 허용되지 않습니다.
    // val name: String = null  - error!

    // 2. Nullable 타입은 타입의 뒤에 ?가 붙습니다.
    //  ex) Int? String?
    // var name: String? = null

    // 3. Nullable 타입을 사용할 때는 반드시 null 체크가 강제됩니다.

    // 1) Smart Cast
    val name: String? = getName()
    if (name != null) {
        // String? -> String
        println("length: ${name.length}")
    }

    // 2) ? 연산자(Safe null access operator)
    //  // ?: - 엘비스 연산자: default 값을 지정할 수 있습니다.
    val len = name?.length ?: -1

    // 3) 강제 접근 연산자 - !!
    println("${name!!.length}")
    //  => 예외 발생의 위험성이 있습니다.
}
*/

// 안전한 널 참조 연산자 활용 - Safe null access operator
class Person(name: String, val address: Address?)
class Address(name: String, postCode: String, val city: City?)
class City(name: String, val country: Country?)
class Country(val name: String)

// Null Check가 중첩될 경우, 안전한 널 참조 연산을 사용하면 편리하다.
fun getCountryName(person: Person?): String {
    return person?.address?.city?.country?.name ?: "Unnamed"
}

/*
fun getCountryName(person: Person?): String? {
    var countryName: String? = null
    if (person != null) {
        val address = person.address
        if (address != null) {
            val city = address.city
            if (city != null) {
                val country = city.country
                if (country != null) {
                    countryName = country.name
                }
            }
        }
    }

    return countryName
}
*/



fun main(args: Array<String>) {
    val country = Country("KR")
    val city = City("Suwon", country)
    val address = Address("영통구", "16512", city)
    val person = Person("Tom", address)

    val countryName = getCountryName(person)
    if (countryName != null)
        println(countryName)
}











