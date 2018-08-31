package xyz.ourguide.ankoapp

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.longToast
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast
import java.util.*

// Anko
//  : Jetbrains 에서 직접 제작하여 배포하는 안드로이드 코틀린 라이브러리
//  Kotlin - DSL(도메인 특화 언어)
//  => 안드로이드 앱 개발에 유용한 다양한 기능을 제공합니다.
//  1) Anko Commons
//  2) Anko Layout
//  3) Anko SQLite    - Room
//  4) Anko Coroutine - Experimental

/*
open class BaseActivity : AppCompatActivity() {
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
*/

// 이제는 수직 확장이 아닌 수평 확장을 사용해야 합니다.
fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton.setOnClickListener {
            // Toast.makeText(this, "Hi!", Toast.LENGTH_SHORT).show()
            // showToast("Hi!")

            // Anko - Toast
            // toast("Hi!")
            // longToast("Hello!")

            // indeterminateProgressDialog("Please wait...") {
            //    setCancelable(false)
            // }

            val progressDialog = progressDialog(title = "File downloading", message = "Plz wait") {
                setCancelable(false)
            }

            val timer = Timer()
            val timerTask = object : TimerTask() {
                override fun run() {
//                    progressDialog.progress += 10
//                    if (progressDialog.progress >= 100) {
//                        progressDialog.dismiss()
//                    }

                    with (progressDialog) {
                        progress += 10
                        if (progress >= 100 ) {
                            dismiss()
                            timer.cancel()
                        }
                    }
                }
            }

            timer.scheduleAtFixedRate(timerTask, 0, 1000)

        }





    }
}







