package ex16;

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

    }
}
