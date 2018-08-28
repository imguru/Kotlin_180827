package ex9_java;

// Java Singleton
// => GoF's Design Pattern 23가지 중 1개
// Singleton
//   : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 접근할 수 있어야 한다.
/*
class Cursor {
    // 2. 정적 객체
    private static final Cursor INSTANCE = new Cursor();
    // static final
    //  : 생성에 대한 스레드 안정성을 보장한다.

    // 문제점
    //  : 프로그램 시작 속도에 악영향을 미친다.

    // 1. private 생성자
    private Cursor() {

    }

    // 3. 정적 메소드
    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/

/*
class Cursor {
    private static Cursor sInstance;
    private Cursor() {

    }

    // 2. Lazy Singleton
    /*
    public synchronized static Cursor getInstance() {
        if (sInstance == null)
            sInstance = new Cursor();

        return sInstance;
    }
    */

    // 3. DCLP(Double Checked Locking Pattern)
    //  : 직관적이지 않다.(선언적이지 않다.)
    /*
    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }

        return sInstance;
    }



}
*/


// 4. IODH(Initialization-on-demand holder)
class Cursor {
    private Cursor() {}

    // 중첩 클래스의 정적 요소는 처음 접근되는 시점에 로드된다.
    private static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }
}

public class Singleton {
    public static void main(String[] args) {
        Cursor c1 = Cursor.getInstance();
        Cursor c2 = Cursor.getInstance();

        System.out.println(c1);
        System.out.println(c2);

        // User.Companion.foo();
        // User.Utils.foo();
        // System.out.println(User.TAG);
    }
}












