package ex4;


// 아래처럼 필드가 많은 클래스에 대해서 생성 방법은
// 2가지 형태
//   1) 생성자 오버로딩
//   2) 빌더 패턴

/*
class User {
    private String name;
    private String address;
    private int age;
    private int money;

    public User(String name, String address, int age, int money) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.money = money;
    }

    public User(String name, String address, int age) {
        this(name, address, age, 0);
    }

    public User(String name, String address) {
        this(name, address, 0, 0);
    }

    public User(String name) {
        this(name, "", 0, 0);
    }
}


public class JavaProgram {
    public static void main(String[] args) {
        // User user = new User("Tom", "Suwon", 42, 100);
        // 인자가 많은 생성자는 실수를 해도 컴파일 오류가 발생하지 않는다.
        User user = new User("Suwon", "Tom", 42, 100);
    }
}
*/

/*
// Builder -> Lombok
class User {
    private String name;
    private String address;
    private int age;
    private int money;

    private User(Builder builder) {
        name = builder.name;
        address = builder.address;
        age = builder.age;
        money = builder.money;
    }

    static class Builder {
        private String name;
        private String address;
        private int age;
        private int money;

        public Builder(String name) {
            this.name = name;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder money(int money) {
            this.money = money;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}



public class JavaProgram {
    public static void main(String[] args) {
        // User user = new User("Tom", "Suwon", 42, 100);
        // 인자가 많은 생성자는 실수를 해도 컴파일 오류가 발생하지 않는다.
        // User user = new User("Suwon", "Tom", 42, 100);

        User user = new User.Builder("Tom")
                .address("Suwon")
                .age(42)
                .money(100)
                .build();
    }
}
*/

/*
import ex3.User;

import java.util.Arrays;
import java.util.Collections;


interface Mp3Player {
    void play();
    void stop();

    // default method / defender method - Java 8
    default void playOneMinute() {
    }
}

class IPod implements Mp3Player {
    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }
}


public class JavaProgram {
    public static void main(String[] args) {
        // User user = new User("Tom", "Suwon", 42, 100);
        // 인자가 많은 생성자는 실수를 해도 컴파일 오류가 발생하지 않는다.
        // User user = new User("Suwon", "Tom", 42, 100);

        User user = new User("Tom");
    }
}

class Car {
    private String name;
    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


// 직렬화 / 역직렬화
// 객체를 ByteStream 변경  - 직렬화
// ByteStream으로부터 객체로 복원 - 역직렬화

interface State extends Serializable {

}

interface View {
    State getCurrentState();
    void restoreState(State state);
}

// 내부 클래스(Inner class)는 생성 될 때, 바깥 클래스(Outer class)에 대한 암묵적인
// 참조를 가진다.
//  => 많은 문제의 원인
//     (메모리 누수, 직렬화 이슈)
class Button implements View {



    // class ButtonState: Inner Class
    // static class ButtonState: Nested Class
    class ButtonState implements State {
        // 다양한 상태값
        // x, y, width, height
    }

    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(State state) {

    }
}

public class JavaProgram {
    public static void main(String[] args) throws IOException {
        Button button = new Button();
        FileOutputStream fos = new FileOutputStream("state.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(button.getCurrentState());
    }
}














