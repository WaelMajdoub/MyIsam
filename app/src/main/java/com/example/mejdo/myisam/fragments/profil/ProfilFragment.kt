package com.example.mejdo.myisam.fragments.profil


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.example.mejdo.myisam.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {

    lateinit var mUserName:EditText
    lateinit var mUserEmail:EditText
    lateinit var mDatabase : DatabaseReference
    lateinit var mAuth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profil, container, false)
        //mUserName= view.findViewById(R.id.profilName)
        mUserEmail= view.findViewById(R.id.profilEmail)

        mAuth=FirebaseAuth.getInstance()
        val uid=mAuth.currentUser?.uid
        val email= mAuth.currentUser?.email
        mUserEmail.setText(email)
        //mDatabase=FirebaseDatabase.getInstance().getReference("User").child(uid)

        return view

    }
    companion object {
        fun newInstance(): ProfilFragment = ProfilFragment()
    }

}
