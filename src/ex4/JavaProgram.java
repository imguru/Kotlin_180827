package ex4;

class Test {
    protected int n = 42;
}

// com.aaa.Awesome
//   Awesome
//      Hello(package)
//      Hi(package)

// com.aaa.AweSome
//    Ok



public class JavaProgram {
    public static void main(String[] args) {
        Test test = new Test();
        test.n = 100;
    }
}
