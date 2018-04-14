package com.example.mejdo.myisam.fragments.list


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.activity.Home
import com.example.mejdo.myisam.activity.Home_admin
import com.example.mejdo.myisam.fragments.add.AddEventFragment
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.model.Events
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass.
 */

 class MyListeEvent : Fragment() {
    lateinit var listView: ListView
    lateinit var ref: DatabaseReference
    private var mDatabase: FirebaseDatabase? = null
    lateinit var Eventlist:MutableList<Events>
    lateinit var fab:FloatingActionButton
    private var mDatabaseReference: DatabaseReference? = null
    private var mAuth: FirebaseAuth? = null
    lateinit var del:Button
    var myAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_liste_event, container, false)
        // val role1 = this.arguments!!.getString("role1")
        // Toast.makeText(context,role1,Toast.LENGTH_LONG).show()
        /*  val args = arguments
          val index = args!!.getString("role1", "")
          Toast.makeText(context,index,Toast.LENGTH_LONG).show()*/


        mDatabase = FirebaseDatabase.getInstance()
           mDatabaseReference = mDatabase!!.reference!!.child("Users")
           mAuth = FirebaseAuth.getInstance()
           val mUser = mAuth!!.currentUser
           val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
           mUserReference.addValueEventListener(object : ValueEventListener {
               override fun onDataChange(snapshot: DataSnapshot) {
                   val role = snapshot.child("role").value as String

                   //  val sp= getSharedPreferences("sp",Context.MODE_PRIVATE)
                   //  val spp = sp.edit()
                   //  spp.putString("role",role);
                   //  spp.apply()
                   /*   val mFragment = ListeEventFragment()
                      val mArgs = Bundle()
                      mArgs.putString("role1", role)
                      mFragment.setArguments(mArgs)*/
                   fab = view.findViewById<View>(R.id.fab) as FloatingActionButton

                   if (role=="responsable"){

                       fab.visibility=View.VISIBLE
                       fab.setOnClickListener {
                           val id = this@MyListeEvent.arguments!!.getString("id")
                           val bundle = Bundle()
                           bundle.putString("id", id)
                           val AddEventFragment = AddEventFragment.newInstance()
                           AddEventFragment.setArguments(bundle);
                           val fragmentManager = activity!!.supportFragmentManager
                           val fragmentTransaction = fragmentManager.beginTransaction()
                           fragmentTransaction.replace(R.id.fargment_container, AddEventFragment)
                           fragmentTransaction.addToBackStack(null)
                           fragmentTransaction.commit()
                       }
                   }

               }
               override fun onCancelled(databaseError: DatabaseError) {}

           })



        /*   listView=view.findViewById(R.id.list_event)
           var list= mutableListOf<Events>()
           list.add(Events("1", "formation symphony", "formation pour les débutant", "karwi syrine", "12/04/2018", "00"))
           list.add(Events("1", "formation laravel", "formation pour les débutant", "karwi syrine", "1/04/2018","11"))
           list.add(Events("1", "formation android", "formation pour les débutant", "karwi syrine", "20/04/2018","33"))
           list.add(Events("1", "formation symphony", "formation pour les débutant", "karwi syrine", "12/04/2018", "00"))
           list.add(Events("1", "formation laravel", "formation pour les débutant", "karwi syrine", "1/04/2018","11"))
           list.add(Events("1", "formation android", "formation pour les débutant", "karwi syrine", "20/04/2018","33"))


           val adapter= adapter_liste_event(view.context, R.layout.my_liste_item_event, list)
           listView.adapter=adapter */

        /*     listView.setOnItemClickListener{
                 parent: AdapterView<*>?, view: View?, position:Int, id:Long ->
                 val detail_event = DetailEventFragment.newInstance()
                 val fragmentManager = activity!!.supportFragmentManager
                 val fragmentTransaction = fragmentManager.beginTransaction()
                 fragmentTransaction.replace(R.id.fargment_container, detail_event)
                 fragmentTransaction.addToBackStack(null)
                 fragmentTransaction.commit()                    }
             return viewgit merge sirine

                 fragmentTransaction.commit()                    }*/


        val id = this.arguments!!.getString("id")
        Eventlist = mutableListOf()
        listView=view.findViewById(R.id.list_event)
        ref= FirebaseDatabase.getInstance().getReference("Events")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }
            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    Eventlist.clear()
                    for(e in p0.children){

                        val event=e.getValue(Events::class.java)
                        if (event != null) {

                            if (event.idclub==id){
                                if (event.etat=="1") {
                                    Eventlist.add(event!!)
                                }
                            }
                        }
                    }

                    val adapter= adapter_my_event(view.context, R.layout.my_list_event, Eventlist)
                    listView.adapter=adapter

                    listView.setOnItemClickListener{
                        parent:AdapterView<*>? , view: View? ,position:Int ,id:Long ->
                        val E=Eventlist[position]
                        detail(E)

                    }

                }

            }
        })

        return view

    }

    private fun detail(event:Events){

        val bundle = Bundle()
        bundle.putString("name_event", event.name_event)
        bundle.putString("description", event.description)
        bundle.putString("formateur", event.formateur)
        bundle.putString("date", event.date)
        bundle.putString("prix", event.prix)
        bundle.putString("heure", event.heur)
        bundle.putString("idclub", event.idclub)
        val DetailEventFragment = DetailEventFragment.newInstance()
        DetailEventFragment.setArguments(bundle)
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fargment_container, DetailEventFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    companion object {
        fun newInstance() : MyListeEvent = MyListeEvent()
    }
}// Required empty public constructor
