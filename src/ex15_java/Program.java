package ex15_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

// 1. 자바는 전역 함수를 제공하지 않기 때문에, 클래스 안에
//    정적 메소드를 모아 놓는다.
// 2. 자바8은 익명 객체를 대체하는 람다를 지원합니다.
//    람다: 코드 조각을 참조하는 기술
//    => 인터페이스 기반의 익명 객체를 대체

// 3. 익명 객체를 사용하면, 매번 class를 생성합니다.
//    프로그램의 시작 속도에 안 좋은 영향을 미칩니다.
//  => 람다는 class 파일을 생성하지 않습니다.
//     : 처음 호출하는 시점에, 해당 코드의 바이트 코드를 생성해서
//       이후 호출은 항상 같은 객체를 참조하도록 설계되어 있습니다.

//@FunctionalInterface
//interface Predicate<E> {
//    boolean test(E e);
//
//    default void foo() {}
//    static void goo() {}
//}

final class Collections {
    private Collections() {
    }

    public static List<Integer> filter(List<Integer> data, Predicate<Integer> pred) {
        List<Integer> result = new ArrayList<>();
        for (Integer e : data) {
            if (pred.test(e))
                result.add(e);
        }

        return result;
    }
}


public class Program {
    public static void main(String[] args) {
        List<Integer> s = Arrays.asList(1, 2, 3, 4, 5);

        // 익명 객체를 통해서 정책을 전달하는 방법.
        List<Integer> evens = Collections.filter(s, new Predicate<Integer>() {
            @Override
            public boolean test(Integer e) {
                return e % 2 == 0;
            }
        });

        // 자바에서의 람다의 한계
        // 1. 인터페이스 기반으로 사용해야 합니다.
        // 2. 람다를 사용하기 위해서는 인터페이스 안에서 메소드가 단 한개만 있어야 합니다.
        List<Integer> odds = Collections.filter(s, e -> {
            return e % 2 == 1;
        });

        System.out.println(evens);
    }
}

/*
public class Program {
    public static void main(String[] args) {
        // 자바에서도 수정 불가능한 컬렉션을 제공하고 있습니다.
        // : Decorator Pattern
        //  => 실행 시간에 기존 클래스에 기능을 추가하는 디자인 패턴

        // List<String> s = new ArrayList<String>();
        List<String> s = Arrays.asList("Hello", "World", "Show");
        s.add("Me");

        // s는 이제 수정이 불가능한 컬렉션입니다.
        s = Collections.unmodifiableList(s);
        // s.add("The");
    }
}
*/


















