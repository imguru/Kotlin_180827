package ex1;

import ex2.UserKt;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

