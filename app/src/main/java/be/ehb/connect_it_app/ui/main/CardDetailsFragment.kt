package be.ehb.connect_it_app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import be.ehb.connect_it_app.R

class CardDetailsFragment : Fragment() {
    companion object {

        fun newInstance(): CardDetailsFragment {
            return CardDetailsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_details, container, false)
    }
}