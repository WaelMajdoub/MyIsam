package com.example.mejdo.myisam.fragments
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.model.Events

/**
 * Created by lenovo on 10/03/2018.
 */
class adapter_liste_event(var mctx: Context, var resource:Int, var items:List<Events>)
    :ArrayAdapter<Events>(mctx,resource,items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater =LayoutInflater.from(mctx)
        val view :View=layoutInflater.inflate(resource,null)
        var textview1: TextView =view.findViewById(R.id.name_event)
        var textview2: TextView =view.findViewById(R.id.date_event)
        var textview3: TextView =view.findViewById(R.id.formateur)
        var event : Events =items[position]
        //  imageview.setImageDrawable(mctx.resources.getDrawable(club.))
        textview1.text=event.name_event
        textview2.text=event.date
        textview3.text=event.description
        return view
    }
}