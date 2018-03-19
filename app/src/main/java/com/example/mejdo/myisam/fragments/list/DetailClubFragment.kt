package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.add.AddClubFragment
import com.example.mejdo.myisam.fragments.add.AddEventFragment


/**
 * A simple [Fragment] subclass.
 */
class DetailClubFragment : Fragment() {

    lateinit var add: Button
    lateinit var nome: TextView

    lateinit var eve : Button
    private lateinit var relativelayout:RelativeLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_club, container, false)
        nome=view.findViewById(R.id.name_club)
        val value = this.arguments!!.getString("key")
        val id = this.arguments!!.getString("id")
        val ListeEventFragment= ListeEventFragment.newInstance()
        val bundle = Bundle()
        bundle.putString("id", id)
        ListeEventFragment.setArguments(bundle);


        nome.text=value


        relativelayout=view.findViewById(R.id.cord)

        eve=view.findViewById(R.id.event)
        eve.setOnClickListener{
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, ListeEventFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        add=view.findViewById(R.id.addEvent)

        add.setOnClickListener{
            val id = this.arguments!!.getString("id")
            val bundle = Bundle()
            bundle.putString("id", id)
            val AddEventFragment = AddEventFragment.newInstance()
            AddEventFragment.setArguments(bundle);
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, AddEventFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }

        return view
    }
    companion object {
        fun newInstance() : DetailClubFragment = DetailClubFragment()
    }


}// Required empty public constructor
