import java.util.*

// 1. Kotlin 는 Java 언어의 모던 버전이다.
// 2. Java 에서 불편했던 많은 문법이 개선되었다.

// 3. Java 에서는 함수(메소드)를 전역으로 만드는 것이 불가능했다.
//  Arrays, Collections, Objects
//  : 위의 클래스는 함수를 모아놓은 클래스 입니다

// 4. 전역으로 함수를 생성하는 것이 가능합니다.
// 5. main 함수도 전역 함수로 설계되어 있습니다.
// $ javac Hello.java
// $ java Hello
/*
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}

*/

// 첫번째 방법
// $ kotlinc Hello.kt -include-runtime -d Hello.jar
// $ java -jar Hello.jar

// 두번째 방법
// $ kotlinc Hello.kt
// $ kotlin  Hello
//      (Type name)
//   -> (name: Type)
fun main(args: Array<String>) {
    println("Hello, Kotlin")
}

// 6. REPL(Read-Eval-Print-Loop) 기능도 제공됩니다.
// => 추가적인 라이브러리도 같이 로드할 수 있습니다.
// $ kotlinc-jvm -cp joda-time-2.10.jar







