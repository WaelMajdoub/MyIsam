package com.example.mejdo.myisam.fragments.list


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.add.AddEventFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class DetailDemandeClub : Fragment() {

    lateinit var accep: Button
    lateinit var nome: TextView
    lateinit var refus : Button
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_demande_club, container, false)
        nome=view.findViewById(R.id.name_demande)
        accep=view.findViewById(R.id.accepter)
        val value = this.arguments!!.getString("key")
        val id = this.arguments!!.getString("id")
        val etat = this.arguments!!.getString("etat")
        nome.text=value
        accep.setOnClickListener{
           // etat=="1"
          mDatabase = FirebaseDatabase.getInstance()
            mDatabaseReference = mDatabase!!.reference!!.child("Clubs")
            val mUserReference = mDatabaseReference!!.child(id).child("etat").setValue("1")
            Toast.makeText(view.context,"club accepter",Toast.LENGTH_SHORT).show()

            /*             mUserReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                       val ch = snapshot.child("etat")
                    }
                    override fun onCancelled(databaseError: DatabaseError) {}
                })*/
        }
        return view
    }
    companion object {
        fun newInstance() : DetailDemandeClub = DetailDemandeClub()
    }

}// Required empty public constructor
