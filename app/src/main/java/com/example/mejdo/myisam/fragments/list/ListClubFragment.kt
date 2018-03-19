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



/**
 * A simple [Fragment] subclass.
 */
class ListClubFragment : Fragment() {
    lateinit var listView:ListView
    lateinit var ref: DatabaseReference
    lateinit var clublist:MutableList<Clubs>
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
        listView=view.findViewById(R.id.listClub)
        ref= FirebaseDatabase.getInstance().getReference("Clubs")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    clublist.clear()
                    for(e in p0.children){
                        val club=e.getValue(Clubs::class.java)
                        clublist.add(club!!)
                    }
                    val adapter= AdapterListeClub(view.context, R.layout.my_liste_item, clublist)
                    listView.adapter=adapter

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



        val detail_club = DetailClubFragment.newInstance()
        detail_club.setArguments(bundle);

                       val fragmentManager = activity!!.supportFragmentManager
                       val fragmentTransaction = fragmentManager.beginTransaction()
                       fragmentTransaction.replace(R.id.fargment_container, detail_club)
                       fragmentTransaction.addToBackStack(null)
                       fragmentTransaction.commit()


    }

        companion object {
        fun newInstance() : ListClubFragment = ListClubFragment()
    }
}

