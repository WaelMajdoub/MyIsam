package com.example.mejdo.myisam.fragments
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.R

/**
 * Created by lenovo on 10/03/2018.
 */
class adapter_liste_club(var mctx: Context, var resource:Int, var items:List<Clubs>)
    :ArrayAdapter<Clubs>(mctx,resource,items){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater =LayoutInflater.from(mctx)
        val view :View=layoutInflater.inflate(resource,null)
        val imageview: ImageView =view.findViewById(R.id.img)
        var textview1: TextView =view.findViewById(R.id.name_club)
        var textview2: TextView =view.findViewById(R.id.type_club)
        var textview3: TextView =view.findViewById(R.id.desc_club)
        var club : Clubs =items[position]
      //  imageview.setImageDrawable(mctx.resources.getDrawable(club.))
        textview1.text=club.name
        textview2.text=club.type
        textview3.text=club.description
        return view
    }
}