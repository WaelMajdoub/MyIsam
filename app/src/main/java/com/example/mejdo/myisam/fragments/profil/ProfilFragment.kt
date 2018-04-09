package com.example.mejdo.myisam.fragments.profil


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.add.AddEventFragment
import com.example.mejdo.myisam.fragments.list.AdapterListeClub
import com.example.mejdo.myisam.model.Clubs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.mesclub.*


/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {
    var myAuth = FirebaseAuth.getInstance()
    lateinit var ref :DatabaseReference
    lateinit var mUserName:EditText
    lateinit var np:TextView
    lateinit var mes:TextView
    lateinit var nb:TextView
    lateinit var email:EditText

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
        np= view.findViewById(R.id.np)
        mes= view.findViewById(R.id.mes)
        nb= view.findViewById(R.id.nb)
      /*  val nombre = this.arguments!!.getString("nbre")
        nb.setText(nombre)*/


        mes.setOnClickListener{
            val mesclub = com.example.mejdo.myisam.fragments.list.mesclub.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, mesclub)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        email=view.findViewById(R.id.Email)
        mAuth=FirebaseAuth.getInstance()
      //  val uid=mAuth.currentUser?.uid


        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        var i=0
        ref= FirebaseDatabase.getInstance().getReference("Clubs")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN) override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    for(e in p0.children){
                        val club=e.getValue(Clubs::class.java)
                        if (club != null) {
                            if ((club.iduser==mUser!!.uid)  &&(club.etat=="1")){
                                i++
                                //role devient admin_club


                            }




                        }}
                    nb.text=i.toString()


                    /* val bundle = Bundle()
                     bundle.putString("nbre", i.toString())
                     val ProfilFragment = ProfilFragment.newInstance()
                     ProfilFragment.setArguments(bundle)*/


                }
            }

        })

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("firstName").value as String
                val pre = snapshot.child("lastName").value as String
                val em = snapshot.child("email").value as String

                email.setText(em)
                np.setText(name+" "+pre)



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
