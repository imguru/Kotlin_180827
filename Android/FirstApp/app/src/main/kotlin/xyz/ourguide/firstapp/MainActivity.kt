package xyz.ourguide.firstapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

// Kotlin 에서는 더 이상 findViewById를 사용하지 않습니다.
//  => Kotlin Extension(KTX) 적용해야 합니다.
//     App 수준의 build.gradle 에서
//         apply plugin: 'kotlin-android-extensions'
//     android:id="@+id/helloButton"

//     helloButton 에 대한 참조를 자동으로 연결해 줍니다.

class MainActivity : AppCompatActivity() {
    // private static final String TAG = MainActivity.class.simpleName()
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton.setOnClickListener {
            Log.i(TAG, "Clicked - KTX")
        }

    }
}


/*
class MainActivity : AppCompatActivity() {
    // private static final String TAG = MainActivity.class.simpleName()
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Button helloButton = findViewById(R.id.btn_hello)
        val helloButton = findViewById<Button>(R.id.helloButton)
        helloButton.setOnClickListener {
            Log.i(TAG, "clicked")
        }

        // -> ButterKnife(iOS)
    }
}
*/

