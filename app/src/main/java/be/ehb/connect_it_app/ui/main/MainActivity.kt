package be.ehb.connect_it_app.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import be.ehb.connect_it_app.R
import be.ehb.connect_it_app.data.model.Vacancy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.yuyakaido.android.cardstackview.*

class MainActivity : AppCompatActivity(), CardStackListener {
    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { CardStackAdapter(createVacancies()) }
    private val queue by lazy { Volley.newRequestQueue(this) }
    private val url = "http://10.0.2.2:8081/getVacancies"
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCardStackView()
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    Log.i(tag,"Response is: ${response.substring(0, 500)}")
                },
                Response.ErrorListener {
                    Log.i(tag,"Network call didn't work, error:  $it") })
        queue.add(stringRequest)

    }


    override fun onCardDisappeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.item_name)
        //Log.d("CardStackView", "onCardDisappeared: ($position) ${textView.text}")
    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (manager.topPosition == adapter.itemCount - 5) {
            paginate()
        }
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        val textView = view.findViewById<TextView>(R.id.company_name)
        Log.d("CardStackView", "onCardAppeared: ($position) ${textView.text}")
    }

    override fun onCardRewound() {
        TODO("Not yet implemented")
    }
    private fun setupCardStackView() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    private fun paginate() {
        val old = adapter.getVacancies()
        val new = old.plus(createVacancies())
        val callback = VacancyDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setVacancies(new)
        result.dispatchUpdatesTo(adapter)
    }

    private fun createVacancies(): List<Vacancy> {
        val vacancies = ArrayList<Vacancy>()
        vacancies.add(Vacancy(name = "Google", city="Brussels, Belgium"))
        vacancies.add(Vacancy(name = "Microsoft", city="London, UK"))
        vacancies.add(Vacancy(name = "Amazon", city="New York, USA"))
        return vacancies
    }
}