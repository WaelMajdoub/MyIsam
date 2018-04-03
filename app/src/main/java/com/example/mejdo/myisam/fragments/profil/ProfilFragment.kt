package com.example.mejdo.myisam.fragments.profil


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import com.example.mejdo.myisam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {
    var myAuth = FirebaseAuth.getInstance()
    lateinit var ref :DatabaseReference
    lateinit var mUserName:EditText
    lateinit var mUserEmail:TextView
  //  lateinit var mDatabase : DatabaseReference
  //  lateinit var mAuth: FirebaseAuth
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profil, container, false)
        //mUserName= view.findViewById(R.id.profilName)
        mUserEmail= view.findViewById(R.id.profilEmail)
        mAuth=FirebaseAuth.getInstance()
      //  val uid=mAuth.currentUser?.uid
      //  val email= mAuth.currentUser?.email

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("firstName").value as String
                mUserEmail.setText(name)


            }
            override fun onCancelled(databaseError: DatabaseError) {}

        })
        //mDatabase=FirebaseDatabase.getInstance().getReference("User").child(uid)

        return view

    }
    companion object {
        fun newInstance(): ProfilFragment = ProfilFragment()
    }

}
