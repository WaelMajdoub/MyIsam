package com.example.mejdo.myisam.fragments.list


import android.graphics.Color
import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_add_event.*


/**
 * A simple [Fragment] subclass.
 */
class DetailEventFragment : Fragment() {

    lateinit var part: Button
    lateinit var nameEvent:TextView
    lateinit var dateEvent:TextView
    lateinit var formEvent:TextView
    lateinit var prixEvent:TextView
    lateinit var descEvent:TextView


    private lateinit var relativelayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail_event, container, false)
        nameEvent=view.findViewById(R.id.nameEvent)
        dateEvent=view.findViewById(R.id.dateEvent)
        formEvent=view.findViewById(R.id.formEvent)
        prixEvent=view.findViewById(R.id.prixEvent)
        descEvent=view.findViewById(R.id.descEvent)

        val name_event = this.arguments!!.getString("name_event")
        val description = this.arguments!!.getString("description")
        val formateur = this.arguments!!.getString("formateur")
        val date = this.arguments!!.getString("date")
        val prix = this.arguments!!.getString("prix")
        nameEvent.text=name_event
        dateEvent.text=date
        formEvent.text=formateur
        descEvent.text=description
        prixEvent.text=prix





        part=view.findViewById(R.id.save)
        relativelayout=view.findViewById(R.id.cord)
        part.setOnClickListener{
            var snackbar: Snackbar = Snackbar.make(relativelayout,"votre demande a été envoyer veuillez attendez la confirmation de responsable de club", Snackbar.LENGTH_LONG)
            snackbar.show()
            part.isEnabled=false
        }
        return view
    }
    companion object {
        fun newInstance() : DetailEventFragment = DetailEventFragment()
    }

}// Required empty public constructor
