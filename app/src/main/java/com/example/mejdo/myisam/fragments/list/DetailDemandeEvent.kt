package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*

import com.example.mejdo.myisam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class DetailDemandeEvent : Fragment() {



    lateinit var nameEvent:TextView
    lateinit var dateEvent:TextView
    lateinit var formEvent:TextView
    lateinit var prixEvent:TextView
    lateinit var descEvent:TextView
    lateinit var heur_event:TextView
    lateinit var fab_main: FloatingActionButton
    lateinit var fab_1: FloatingActionButton
    lateinit var fab_2: FloatingActionButton
    lateinit var fab_open: Animation
    lateinit var fab_close: Animation
    lateinit var rotate_cw: Animation
    lateinit var rotate_acw: Animation
    //lateinit var inter:TextView
    var isopen:Boolean=false
    private lateinit var relativelayout: RelativeLayout


    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail_demande_event, container, false)
        fab_main=view.findViewById(R.id.fab1)
        fab_1=view.findViewById(R.id.fab2)
        fab_2=view.findViewById(R.id.fab3)
        fab_open= AnimationUtils.loadAnimation(context,R.anim.open_fab)
        fab_close= AnimationUtils.loadAnimation(context,R.anim.close_fab)
        rotate_cw= AnimationUtils.loadAnimation(context,R.anim.rotate_clockwise)
        rotate_acw= AnimationUtils.loadAnimation(context,R.anim.rotate_anticlockwise)

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
        nameEvent=view.findViewById(R.id.nameEvent)
        dateEvent=view.findViewById(R.id.dateEvent)
        formEvent=view.findViewById(R.id.formEvent)
        prixEvent=view.findViewById(R.id.prixEvent)
        descEvent=view.findViewById(R.id.descEvent)
        heur_event=view.findViewById(R.id.heur_event)
        val name_event = this.arguments!!.getString("name_event")
        val description = this.arguments!!.getString("description")
        val formateur = this.arguments!!.getString("formateur")
        val date = this.arguments!!.getString("date")
        val heur = this.arguments!!.getString("heure")
        val prix = this.arguments!!.getString("prix")
        val id_event = this.arguments!!.getString("id_event")
        //  Toast.makeText(this.context,""+idevent,Toast.LENGTH_LONG).show()
        nameEvent.text=name_event
        dateEvent.text=date
        formEvent.text=formateur
        descEvent.text=description
        prixEvent.text=prix
        heur_event.text=heur
        relativelayout=view.findViewById(R.id.cord)

        fab_1.setOnClickListener {

            mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase!!.reference!!.child("Events")
            val mUserReference = mDatabaseReference!!.child(id_event).child("etat").setValue("1")
            var snackbar: Snackbar = Snackbar.make(relativelayout,"evenement accepté", Snackbar.LENGTH_LONG)
            snackbar.show()
            val ListeDemandeEvent = ListeDemandeEvent.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, ListeDemandeEvent)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()


        }
        fab_2.setOnClickListener {
            mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase!!.reference!!.child("Events")
            val mUserReference = mDatabaseReference!!.child(id_event).removeValue()
            var snackbar: Snackbar = Snackbar.make(relativelayout,"evenement refusé", Snackbar.LENGTH_LONG)
            snackbar.show()
            val ListeDemandeEvent = ListeDemandeEvent.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, ListeDemandeEvent)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }





        return view
    }
    companion object {
        fun newInstance() : DetailDemandeEvent = DetailDemandeEvent()
    }

}// Required empty public constructor
