package xyz.ourguide.firstapp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


// Java -> Kotlin
//  : 대부분의 타입을 Nullable 형태로 보기 때문에, 코틀린 코드를 작성하기 어려울 때가
//    많습니다.

// Java 코드를 작성하는 사람이, 코틀린에서도 타입이 정확하게 추론될 수 있도록
// 만드는 것이 가능합니다.
// @NonNull  String name      ->     name: String
// @Nullable String name      ->     name: String?

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}