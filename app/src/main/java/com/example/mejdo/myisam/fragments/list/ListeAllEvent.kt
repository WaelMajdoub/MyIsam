package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.model.Events
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class ListeAllEvent : Fragment() {

    lateinit var listView: ListView
    lateinit var ref: DatabaseReference
    lateinit var Eventlist:MutableList<Events>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_liste_all_event, container, false)
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

                            if (event.etat=="1") {
                                Eventlist.add(event!!)
                                //  Toast.makeText(context,""+event.eventId,Toast.LENGTH_LONG).show()

                            }

                        }
                    }

                    val adapter= adapter_liste_all_event(view.context, R.layout.my_list_event, Eventlist)
                    listView.adapter=adapter
                    listView.setOnItemClickListener{
                        parent: AdapterView<*>?, view: View?, position:Int, id:Long ->
                        val E=Eventlist[position]
                        detail(E)

                    }

                }
            }
        })
        return view

    }

    private fun detail(event: Events){

        val bundle = Bundle()
        bundle.putString("id_event", event.eventId)
        bundle.putString("name_event", event.name_event)
        bundle.putString("description", event.description)
        bundle.putString("formateur", event.formateur)
        bundle.putString("date", event.date)
        bundle.putString("prix", event.prix)
        bundle.putString("idclub", event.idclub)
        val Detail_admin_event = Detail_admin_event.newInstance()
        Detail_admin_event.setArguments(bundle);
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fargment_container, Detail_admin_event)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    companion object {
        fun newInstance() : ListeAllEvent = ListeAllEvent()
    }
}// Required empty public constructor
// Required empty public constructor
