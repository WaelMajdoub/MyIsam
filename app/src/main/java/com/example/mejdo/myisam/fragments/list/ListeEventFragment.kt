package com.example.mejdo.myisam.fragments.list


import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.model.Events
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class ListeEventFragment : Fragment() {
    lateinit var listView: ListView
    lateinit var ref: DatabaseReference
    lateinit var Eventlist:MutableList<Events>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.liste_event, container, false)
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
                                Eventlist.add(event!!)

                          }
                        }
                    }

                    val adapter= adapter_liste_event(view.context, R.layout.my_liste_item_event, Eventlist)
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
        bundle.putString("idclub", event.idclub)
        val DetailEventFragment = DetailEventFragment.newInstance()
        DetailEventFragment.setArguments(bundle);
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fargment_container, DetailEventFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }


    companion object {
        fun newInstance() : ListeEventFragment = ListeEventFragment()
    }
}// Required empty public constructor
