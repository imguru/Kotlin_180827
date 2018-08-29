package ex15_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


















