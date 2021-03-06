package com.example.mejdo.myisam.fragments.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.R
import com.google.firebase.database.*
import android.R.attr.data
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi


/**
 * A simple [Fragment] subclass.
 */
class ListClubFragment : Fragment() {
    lateinit var listView:ListView
    lateinit var ref: DatabaseReference
    lateinit var clublist:MutableList<Clubs>
    lateinit var listDem:MutableList<Clubs>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_list_club, container, false)
       /* listView=view.findViewById(R.id.list_club)
        var list= mutableListOf<Clubs>()
        list.add(Clubs("12","jeune ing","éducatif","ancien club"))
        list.add(Clubs("12","jeune ing","éducatif","ancien club"))
        list.add(Clubs("12","jeune ing","éducatif","ancien club"))
        list.add(Clubs("12","jeune ing","éducatif","ancien club"))
        val adapter=AdapterListeClub(view.context,R.layout.my_liste_item,list)
        listView.adapter=adapter */







        clublist = mutableListOf()
        listDem = mutableListOf()

        listView=view.findViewById<ListView>(R.id.listClub)
        ref= FirebaseDatabase.getInstance().getReference("Clubs")
        ref.addValueEventListener(object :ValueEventListener{
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

                                listDem.add(club!!)


                            }
                            if (club.etat=="1"){
                                //role devient admin_club
                         /*       val intent= Intent()
                                val pendingIntent= PendingIntent.getActivity(context,0,intent,0)
                                val notification= Notification.Builder(context).setSmallIcon(R.drawable.navigation_empty_icon)
                                        .setContentTitle("Confirmation !!")
                                        .setContentText("Votre demande dajout est confirmé")


                                notification.setContentIntent(pendingIntent)
                                val noticationManager= context!!.getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
                                noticationManager.notify(0,notification.build()) */
                                listDem.add(club!!)

                                clublist.add(club!!)

                            }



                    }}


                    val adapter= AdapterListeClub(view.context, R.layout.my_liste_item,clublist)
                    listView.adapter=adapter
                    val bundle = Bundle()
                  //  bundle.putParcelableArrayList("liste",listDem(Clubs))
                    val ListeDemandeClub = ListeDemandeClub.newInstance()
                    ListeDemandeClub.setArguments(bundle);
                    listView.setOnItemClickListener{
                        parent:AdapterView<*>? , view: View? ,position:Int ,id:Long ->
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



        val ListeEventFragment = ListeEventFragment.newInstance()
        ListeEventFragment.setArguments(bundle);

                       val fragmentManager = activity!!.supportFragmentManager
                       val fragmentTransaction = fragmentManager.beginTransaction()
                       fragmentTransaction.replace(R.id.fargment_container, ListeEventFragment)
                       fragmentTransaction.addToBackStack(null)
                       fragmentTransaction.commit()


    }

        companion object {
        fun newInstance() : ListClubFragment = ListClubFragment()
    }
}


