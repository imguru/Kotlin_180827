package xyz.ourguide.ankoapp

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType.*
import android.widget.Toast
import org.jetbrains.anko.*

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

/*
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

            /*
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
            */

            /*
            val alert = AlertDialog.Builder(this).apply {
                setTitle("Dialog Title")
                setMessage("Hello, Kotlin")
                setPositiveButton("OK") { _, _ ->
                    toast("OK")
                }
            }.create()
            alert.show()
            */

            // Android 현재 버전이 제공하는 라이브러리
            /*
            // alert(title = "Dialog Title", message = "Hello, Kotlin") {
            alert(message = "Hello, Kotlin") {
                positiveButton("Yes") { _ ->
                    toast("Yes")
                }

                negativeButton("No") { _ ->
                    toast("No")
                }
            }.show()
            */

            // Support Library Dialog를 사용하는 방법
            /*
            alert(Appcompat, message = "Hello, Kotlin") {
                positiveButton("Yes") { _ ->
                    toast("Yes")
                }

                negativeButton("No") { _ ->
                    toast("No")
                }
            }.show()
            */

            val selections = listOf("미혼", "기혼", "이혼")
            selector(title = "결혼 여부", items = selections) { dialog, index ->
                toast("선택 - ${selections[index]}")
            }

        }


    }
}
*/

/*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helloButton.setOnClickListener {
            // val intent = Intent(this, SubActivity::class.java)
            // startActivity(intent)

            // startActivity<SubActivity>()

            val intent = intentFor<SubActivity>(
                    "name" to "Tom",
                    "age" to 42
            )
            startActivity(intent)
        }

    }
}
*/

// Anko Layout
// 1. 코드를 이용해서 뷰를 구성하는 것은 어려운 일입니다.
// 2. XML로 정의된 뷰를 사용할 경우, 파싱에 대한 비용이 소모됩니다.
// 3. minSdkVersion 에 따라서 의존하는 라이브러리가 달라집니다.

// 문제점
// 1. Preview 가 제공됩니다. 하지만 불편합니다.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        MainActivityUI().setContentView(this)
    }
}

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
        verticalLayout {
            textView("Enter Login Email Address")
            padding = dip(8)

            editText {
                hint = "이메일"
                inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            editText {
                hint = "Password"
                inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
            }

            button("Submit")
        }
    }.view
}









class SubActivity : AppCompatActivity(), AnkoLogger {
    companion object {
        // const val TAG: String = "SubActivity"
        // val TAG: String = SubActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val name = intent.getStringExtra("name")
        // Log.i(TAG, "name: $name")
        info("name: $name")
    }
}




