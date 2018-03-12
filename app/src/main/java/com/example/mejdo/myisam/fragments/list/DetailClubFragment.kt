package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mejdo.myisam.R


/**
 * A simple [Fragment] subclass.
 */
class DetailClubFragment : Fragment() {

    lateinit var add: Button
    lateinit var eve : Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_club, container, false)
        eve=view.findViewById(R.id.event)
        eve.setOnClickListener{
            val Liste_Event = ListeEventFragment.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, Liste_Event)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        return view
    }
    companion object {
        fun newInstance() : DetailClubFragment = DetailClubFragment()
    }


}// Required empty public constructor
