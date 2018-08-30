package ex24

// 범위 지정 함수

// 1. let
/*
fun sendEmail(to: String) = println("send email to $to")
class User(val email: String? = null)

// fun<T, R> T.let(block: (T) -> R): R
fun main(args: Array<String>) {
    val user: User = User()


    if (user.email == null) {
        // ...
    } else {
        sendEmail(user.email)                 // val
        // user.email?.let { sendEmail(it) }  // var
    }

    user.email?.let { email ->
        sendEmail(email)
    }

    // sendEmail(user.email) // String의 타입에 String?을 대입할 수 없습니다.
    // if (user.email != null) {
    //    sendEmail(user.email!!)
    // }
}
*/

/*
// 2. 수신 객체 지정 람다 - with / apply
fun alphabet1(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }

    result.append("\n")
    return result.toString()
}

// with - 코드의 중복을 줄일 수 있다.
fun alphabet2(): String {
    val result = StringBuilder()

    return with(result) {
        for (letter in 'A'..'Z') {
            append(letter)
        }

        append("\n")
        toString()
    }
}

// apply
fun alphabet3() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\n")
}.toString()
*/

/*
fun alphabet3() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\n")
}
*/
































