package com.example.mejdo.myisam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 */
class AddClubFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment {
            var addClub : AddClubFragment = AddClubFragment()
            return addClub
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_club, container, false)
    }

}// Required empty public constructor
