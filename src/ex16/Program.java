package ex16;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@FunctionalInterface
interface OnClickListener {
    void onClick();
}

class Button {
    private OnClickListener listener;
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void click() {
        if (listener != null)
            listener.onClick();
    }
}


public class Program {
    public static void main(String[] args) {
        List<String> cities =Arrays.asList("A", "AA", "BBB", "CCCC", "DDD", "EEEE");


        // Function<String, Integer> a = String::length;
        List<Integer> result = cities.stream()
                .map(String::length)
                .filter(e -> e % 2 == 0).collect(Collectors.toList());


        System.out.println(result);
    }
}
