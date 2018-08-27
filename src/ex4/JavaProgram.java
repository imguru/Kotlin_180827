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

import ex3.User;

public class JavaProgram {
    public static void main(String[] args) {
        // User user = new User("Tom", "Suwon", 42, 100);
        // 인자가 많은 생성자는 실수를 해도 컴파일 오류가 발생하지 않는다.
        // User user = new User("Suwon", "Tom", 42, 100);

        User user = new User("Tom");
    }
}


















