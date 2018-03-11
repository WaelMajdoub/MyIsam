package com.example.mejdo.myisam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView


/**
 * A simple [Fragment] subclass.
 */
class Liste_Event : Fragment() {
    lateinit var listView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.liste_event, container, false)
        listView=view.findViewById(R.id.list_event)
        var list= mutableListOf<Events>()
        list.add(Events("1","formation symphony","formation pour les débutant","karwi syrine","12/04/2018"))
        list.add(Events("1","formation laravel","formation pour les débutant","karwi syrine","1/04/2018"))
        list.add(Events("1","formation android","formation pour les débutant","karwi syrine","20/04/2018"))
        list.add(Events("1","formation photoshop","formation pour les débutant","karwi syrine","2/04/2018"))
        list.add(Events("1","formation ilustrateur","formation pour les débutant","karwi syrine","2/04/2018"))
        list.add(Events("1","formation php","formation pour les débutant","karwi syrine","04/04/2018"))
        list.add(Events("1","formation html","formation pour les débutant","karwi syrine","12/04/2018"))
        list.add(Events("1","formation css","formation pour les débutant","karwi syrine","12/04/2018"))

        val adapter=adapter_liste_event(view.context,R.layout.my_liste_item_event,list)
        listView.adapter=adapter
        listView.setOnItemClickListener{
            parent: AdapterView<*>?, view: View?, position:Int, id:Long ->
            val detail_event = detail_event.newInstance()
            val fragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fargment_container, detail_event)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()                    }
        return view
    }
    companion object {
        fun newInstance() : Liste_Event = Liste_Event()
    }
}// Required empty public constructor
