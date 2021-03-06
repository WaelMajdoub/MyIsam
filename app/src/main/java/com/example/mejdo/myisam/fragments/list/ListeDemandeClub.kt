package com.example.mejdo.myisam.fragments.list


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.model.Clubs
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 */
class ListeDemandeClub : Fragment() {
    lateinit var listView: ListView
    lateinit var ref: DatabaseReference
    lateinit var clublist:MutableList<Clubs>
    lateinit var listDem:MutableList<Clubs>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_liste_demande_club, container, false)
        clublist = mutableListOf()
        listDem = mutableListOf()
        listView=view.findViewById<ListView>(R.id.listDemande)
        ref= FirebaseDatabase.getInstance().getReference("Clubs")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN) override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    clublist.clear()
                    for(e in p0.children){
                        val club=e.getValue(Clubs::class.java)
                        if (club != null) {
                            if (club.etat=="0"){
                                //role devient admin_club
                                val intent= Intent()
                                val pendingIntent= PendingIntent.getActivity(context,0,intent,0)
                                val notification= Notification.Builder(context).setSmallIcon(R.drawable.navigation_empty_icon)
                                        .setContentTitle("Demande d'ajout d'un club")
                                        .setContentText("Nouvelle demande d'ajout d'un club")


                                notification.setContentIntent(pendingIntent)
                                val noticationManager= context!!.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
                                noticationManager.notify(0,notification.build())
                                listDem.add(club!!)


                            }
                            if (club.etat=="1"){
                                //role devient admin_club

                                clublist.add(club!!)

                            }

                        }}

                    val adapter= AdapterListeClub(view.context, R.layout.my_liste_item,listDem)
                    listView.adapter=adapter
                    listView.setOnItemClickListener{
                        parent: AdapterView<*>?, view: View?, position:Int, id:Long ->
                        val C=listDem[position]
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
        bundle.putString("etat", club.etat)
        bundle.putString("iduser", club.iduser)

        val DetailDemandeClub = DetailDemandeClub.newInstance()
        DetailDemandeClub.setArguments(bundle);
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fargment_container, DetailDemandeClub)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
    companion object {
        fun newInstance() : ListeDemandeClub = ListeDemandeClub()
    }
}// Required empty public constructor
