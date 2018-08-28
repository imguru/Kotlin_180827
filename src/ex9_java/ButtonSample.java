package ex9_java;

/*
class Dialog implements OnClickListener {
    public void close() {
        System.out.println("Dialog close");
    }

    @Override
    public void onClick(Button button) {
        // switch (button.getId()) {
        //   case R.id.close_button:
        //       close(); break;
        //   case R.id.open_button:
        //       open(); break;
        // }

        close();
    }
}
*/

class Dialog {
    public void close() {
        System.out.println("Dialog close");
    }
}

interface OnClickListener {
    void onClick(Button button);
}

class Button {
    private OnClickListener listener;
    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void click() {
        System.out.println("Button click");
        listener.onClick(this);
    }
}

public class ButtonSample {
    public static void main(String[] args) {
        Button button = new Button();
        Button button2 = new Button();

        Dialog dialog = new Dialog();

        // Java 8 이전에는 반드시 해당 변수가 final 이어야 합니다.
        // Java 8 에서는 암묵적으로 접근이 가능하다.
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Button button) {
                dialog.close();
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Button button) {
                // dialog.open();
            }
        });

        button.click();
    }
}














