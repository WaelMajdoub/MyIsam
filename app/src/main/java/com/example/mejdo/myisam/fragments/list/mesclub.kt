package com.example.mejdo.myisam.fragments.list


import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.profil.ProfilFragment
import com.example.mejdo.myisam.model.Clubs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class mesclub : Fragment() {
    lateinit var listView: ListView
    lateinit var ref: DatabaseReference
    lateinit var clublist:MutableList<Clubs>
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.mesclub, container, false)

        clublist = mutableListOf()
        listView=view.findViewById<ListView>(R.id.mesclub)
        ref= FirebaseDatabase.getInstance().getReference("Clubs")


        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        var i=0
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN) override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    clublist.clear()
                    for(e in p0.children){
                        val club=e.getValue(Clubs::class.java)
                        if (club != null) {
                            if ((club.iduser==mUser!!.uid) &&(club.etat=="1")){
                                i++
                                //role devient admin_club
                                clublist.add(club!!)


                            }




                        }}

                   /* val bundle = Bundle()
                    bundle.putString("nbre", i.toString())
                    val ProfilFragment = ProfilFragment.newInstance()
                    ProfilFragment.setArguments(bundle)*/

                    Toast.makeText(context,i.toString(), Toast.LENGTH_LONG).show()

                    val adapter= AdapterListeClub(view.context, R.layout.my_liste_item,clublist)
                    listView.adapter=adapter
                    listView.setOnItemClickListener{
                        parent: AdapterView<*>?, view: View?, position:Int, id:Long ->
                        val C=clublist[position]
                        detail(C)

                    }
                }
            }
        })

        return view
    }
    private fun detail(club:Clubs){

        // Log.e("test",club.name)
        val bundle = Bundle()
        bundle.putString("key", club.name)
        bundle.putString("id", club.clubId)
        bundle.putString("des", club.description)



        val detail_club = DetailClubFragment.newInstance()
        detail_club.setArguments(bundle);

        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fargment_container, detail_club)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


    }

    companion object {
        fun newInstance() : mesclub = mesclub()
    }
}

