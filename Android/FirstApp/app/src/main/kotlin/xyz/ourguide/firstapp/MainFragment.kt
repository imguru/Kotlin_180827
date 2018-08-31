package xyz.ourguide.firstapp


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_city.view.*
import kotlin.properties.Delegates


// Java -> Kotlin
//  : 대부분의 타입을 Nullable 형태로 보기 때문에, 코틀린 코드를 작성하기 어려울 때가
//    많습니다.

// Java 코드를 작성하는 사람이, 코틀린에서도 타입이 정확하게 추론될 수 있도록
// 만드는 것이 가능합니다.
// @NonNull  String name      ->     name: String
// @Nullable String name      ->     name: String?

data class City(val korName: String, val engName: String)

// Recycler View
//  1. ViewHolder
//  2. Adapter
class CityAdapter(data: List<City> = emptyList()) : RecyclerView.Adapter<CityAdapter.Holder>() {
    /*
    var cities = cities
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    */

    var cities: List<City> by Delegates.observable(data) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(parent)

    override fun getItemCount() = cities.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = cities[position] // cities.get(position)

        holder.itemView.korNameText.text = model.korName
        holder.itemView.engNameText.text = model.engName

        with(holder.itemView) {
            korNameText.text = model.korName
            engNameText.text = model.engName
        }
    }

    class Holder(parent: ViewGroup)
        : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city,
                    parent, false))
}


class MainFragment : Fragment() {
    private val cities = listOf(
            City("서울", "Seoul"),
            City("수원", "Suwon"),
            City("대구", "Daegu")
    )

    private val adapter = CityAdapter()
    private val handler = Handler()

    // List<Pair<String, String>>
    /*
    val cities = listOf(
            "서울" to "Seoul",
            "수원" to "Suwon",
            "대구" to "Daegu"
    )
    */

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // recyclerView.adapter = CityAdapter(cities)

        recyclerView.adapter = adapter
        // adapter.cities = cities

        handler.postDelayed({
            adapter.cities = cities
            // adapter.notifyDataSetChanged()
        }, 2000)
    }

    /*
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        // setLayoutManager -> layoutManager
        // getActivity      -> activity
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = CityAdapter(cities)
    }
    */
}























