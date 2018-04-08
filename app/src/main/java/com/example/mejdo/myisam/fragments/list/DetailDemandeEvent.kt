package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.mejdo.myisam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class DetailDemandeEvent : Fragment() {

    lateinit var accep: Button
    lateinit var ref: Button
    lateinit var nameEvent: TextView
    lateinit var dateEvent: TextView
    lateinit var formEvent: TextView
    lateinit var prixEvent: TextView
    lateinit var descEvent: TextView
    private lateinit var RelativeLayout: RelativeLayout

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_detail_demande_event, container, false)
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
        val id_event = this.arguments!!.getString("id_event")
      //  Toast.makeText(this.context,""+idevent,Toast.LENGTH_LONG).show()
        nameEvent.text=name_event
        dateEvent.text=date
        formEvent.text=formateur
        descEvent.text=description
        prixEvent.text=prix
        accep=view.findViewById(R.id.accep)
        ref=view.findViewById(R.id.refuser)

        RelativeLayout=view.findViewById(R.id.cordd)

      accep.setOnClickListener{
            // etat=="1"
        mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase!!.reference!!.child("Events")
            val mUserReference = mDatabaseReference!!.child(id_event).child("etat").setValue("1")
          var snackbar: Snackbar = Snackbar.make(RelativeLayout,"evenement accepté", Snackbar.LENGTH_LONG)
          snackbar.show()
          accep.isEnabled=false
        }
        ref.setOnClickListener{
            // etat=="1"
            mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase!!.reference!!.child("Events")
            val mUserReference = mDatabaseReference!!.child(id_event).removeValue()
            var snackbar: Snackbar = Snackbar.make(RelativeLayout,"evenement refusé", Snackbar.LENGTH_LONG)
            snackbar.show()
            accep.isEnabled=false
        }
        return view
    }
    companion object {
        fun newInstance() : DetailDemandeEvent = DetailDemandeEvent()
    }

}// Required empty public constructor
