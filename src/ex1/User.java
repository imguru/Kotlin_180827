package ex1;

import ex2.UserKt;

import java.util.Objects;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. 참조 동등성 체크
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        // 2. 동일한 타입인지 검증
        //  : Reflection
        if (User.class != obj.getClass())
            return false;

        // 3. 내용을 비교
        User u = (User)obj;
        return u.age == age &&
                Objects.equals(u.name, name);
                // u.name.equals(name);
    }

    @Override
    public String toString() {
        return String.format("User(%s,%d)", name, age);
    }

    public static void foo() {
        System.out.println("foo");
    }

    public static void main(String[] args) {
        UserKt user = new UserKt("Tom", 42);
        System.out.println(user);

        // int n;
        // System.out.printf("n: %d\n", n);
    }
}

