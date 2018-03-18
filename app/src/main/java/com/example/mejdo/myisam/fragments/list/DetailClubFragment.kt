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


/**
 * A simple [Fragment] subclass.
 */
class DetailClubFragment : Fragment() {

    lateinit var rej: Button
    lateinit var nome: TextView

    lateinit var eve : Button
    private lateinit var relativelayout:RelativeLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_club, container, false)
        nome=view.findViewById(R.id.name_club)

        val value = this.arguments!!.getString("key")
        nome.text=value


        relativelayout=view.findViewById(R.id.cord)

        eve=view.findViewById(R.id.event)
        eve.setOnClickListener{
            val Liste_Event = ListeEventFragment.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, Liste_Event)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        rej=view.findViewById(R.id.save)

        rej.setOnClickListener{
            var snackbar:Snackbar= Snackbar.make(relativelayout,"votre demande a été envoyer veuillez attendez la confirmation de responsable de club",Snackbar.LENGTH_LONG)
            snackbar.show()
        rej.isEnabled=false
        }

        return view
    }
    companion object {
        fun newInstance() : DetailClubFragment = DetailClubFragment()
    }


}// Required empty public constructor
