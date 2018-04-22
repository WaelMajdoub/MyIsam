package com.example.mejdo.myisam.fragments.list


import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.example.mejdo.myisam.R
import kotlinx.android.synthetic.main.fragment_add_event.*
import kotlinx.android.synthetic.main.liste_event.*


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
    lateinit var heur_event:TextView
    lateinit var fab_main:FloatingActionButton
    lateinit var fab_1:FloatingActionButton
    lateinit var fab_2:FloatingActionButton
    lateinit var fab_open:Animation
    lateinit var fab_close:Animation
    lateinit var rotate_cw:Animation
    lateinit var rotate_acw:Animation
    //lateinit var inter:TextView
    var isopen:Boolean=false



    private lateinit var relativelayout: RelativeLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail_event, container, false)
       // inter=view.findViewById(R.id.inter)
        fab_main=view.findViewById(R.id.fab1)
        fab_1=view.findViewById(R.id.fab2)
        fab_2=view.findViewById(R.id.fab3)
        fab_open=AnimationUtils.loadAnimation(context,R.anim.open_fab)
        fab_close=AnimationUtils.loadAnimation(context,R.anim.close_fab)
        rotate_cw=AnimationUtils.loadAnimation(context,R.anim.rotate_clockwise)
        rotate_acw=AnimationUtils.loadAnimation(context,R.anim.rotate_anticlockwise)
        fab_main.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                if (isopen) {
                    fab_main.setImageResource(R.drawable.ic_swap_calls_black_24dp)
                    fab_1.startAnimation(fab_close)
                    fab_2.startAnimation(fab_close)
                    fab_main.startAnimation(rotate_acw)
                    fab_1.visibility=View.GONE
                    //inter.visibility=View.GONE
                    fab_2.visibility=View.GONE
                    isopen=false



                } else {
                    fab_1.startAnimation(fab_open)
                    fab_2.startAnimation(fab_open)
                    fab_main.startAnimation(rotate_cw)
                    fab_1.visibility=View.VISIBLE
                    fab_2.visibility=View.VISIBLE
                    fab_1.isClickable=true
                    fab_2.isClickable=true
                    isopen=true
                    fab_main.setImageResource(R.drawable.ic_add_black_24dp)

                   // inter.visibility=View.VISIBLE

                }
            }
        })
        fab_1.setOnClickListener {
            var snackbar: Snackbar = Snackbar.make(relativelayout," ***  participer  ***", Snackbar.LENGTH_LONG)
            snackbar.show()
            fab_main.setImageResource(R.drawable.ic_swap_calls_black_24dp)
            fab_1.startAnimation(fab_close)
            fab_2.startAnimation(fab_close)
            fab_main.startAnimation(rotate_acw)
            fab_1.visibility=View.GONE
            //inter.visibility=View.GONE
            fab_2.visibility=View.GONE
        }
        fab_2.setOnClickListener {
            var snackbar: Snackbar = Snackbar.make(relativelayout,"***  Intéressé  ***", Snackbar.LENGTH_LONG)
            snackbar.show()
        }


        heur_event=view.findViewById(R.id.heur_event)
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
        val heure = this.arguments!!.getString("heure")
        heur_event.text=heure
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
