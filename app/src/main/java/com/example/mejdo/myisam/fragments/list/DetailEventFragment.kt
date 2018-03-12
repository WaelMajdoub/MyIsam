package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.example.mejdo.myisam.R


/**
 * A simple [Fragment] subclass.
 */
class DetailEventFragment : Fragment() {

    lateinit var part: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail_event, container, false)
        part=view.findViewById(R.id.save)
        part.setOnClickListener{
            Toast.makeText(context, "votre demande a été envoyer veuillez attendez la confirmation", Toast.LENGTH_LONG).show()
            part.isEnabled=false
        }
        return view
    }
    companion object {
        fun newInstance() : DetailEventFragment = DetailEventFragment()
    }

}// Required empty public constructor
