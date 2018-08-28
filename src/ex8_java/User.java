package ex8_java;

import java.util.Objects;

public class User implements Cloneable {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 자신과 동일한 타입을 통해 객체를 생성하는 방법
    // => clone() 보다는 복사 생성자가 좋을 때가 많다.
    public User(User other) {
        this.name = other.name;
        this.age = other.age;
    }

    // 1. protected -> public
    // 2. 예외를 사용자가 처리하는 것이 아니라 내부에서 처리해야 한다.
    // 3. 캐스트를 내부적으로 처리한다.
    //   ; 부모의 메소드의 반환 타입을 하위 타입으로 변환하는 것이 허용된다.(1.5)
    //    => 공변 반환의 룰
    // 4. interface Cloneable 구현
    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // 1. clone()은 언제 사용하나요?


    public static void main(String[] args) {
        User u1 = new User("Tom", 42);

        // User u2 = u1;
        //  : 얕은 복사
        // 1) u1 객체가 수정 가능할 경우, u2는
        //    인지하지 못한다.
        // 얕은 복사를 통해서 객체를 복사하길 원할 경우
        // 객체를 반드시 불변 객체로 설계해야 한다.
        // 2) 순환 참조로 인산 누수의 위험을 항상 생각해야 한다.

        // clone의 한계
        //  : 부모가 clone을 제공하고 있지 않다면은
        //    clone을 제공할 수 없다.
        //  => 복사 생성자


        //  : 깊은 복사
        // 1. protected
        // 2. checked Exception
        // 3. cast
        // User u2 = u1.clone();
        User u2 = new User(u1);

        System.out.println(u1);
        System.out.println(u2);


    }
}















