package com.example.mejdo.myisam.fragments.list
import android.annotation.SuppressLint
import android.content.Context
import android.support.design.R.id.container
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.R.id.del

/**
 * Created by lenovo on 10/03/2018.
 */
class AdapterListeClub(var mctx: Context, var resource:Int, var items:List<Clubs>)
    :ArrayAdapter<Clubs>(mctx,resource,items){
    @SuppressLint("WrongViewCast") override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater =LayoutInflater.from(mctx)
        val view :View=layoutInflater.inflate(resource,null)
        val imageview: ImageView =view.findViewById(R.id.img)
    //   val imagev:ImageView=view.findViewById(R.id.del)
        var textview1: TextView =view.findViewById(R.id.name_club)
        var textview2: TextView =view.findViewById(R.id.type_club)
        var textview3: TextView =view.findViewById(R.id.desc_club)
        var club : Clubs =items[position]
      //  imageview.setImageDrawable(mctx.resources.getDrawable(club.))
        textview1.text=club.name
        textview2.text=club.type
        textview3.text=club.description
      /*  imagev.setOnClickListener{
            Toast.makeText(context,"lalal",Toast.LENGTH_LONG).show()
        }*/
        return view
    }
}