package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mejdo.myisam.R


/**
 * A simple [Fragment] subclass.
 */
class detail_event : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_event, container, false)
    }
    companion object {
        fun newInstance() : detail_event = detail_event()
    }

}// Required empty public constructor
