package com.example.mejdo.myisam.fragments.list

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.model.Events
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Created by lenovo on 15/04/2018.
 */


/**
 * Created by lenovo on 14/04/2018.
 */


/**
 * Created by lenovo on 10/03/2018.
 */
class adapter_liste_all_event(var mctx: Context, var resource:Int, var items:List<Events>)
    : ArrayAdapter<Events>(mctx,resource,items){
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mctx)
        val view : View =layoutInflater.inflate(resource,null)
        val imagev: ImageView =view.findViewById(R.id.del)
        var textview1: TextView =view.findViewById(R.id.name_event)
        var textview2: TextView =view.findViewById(R.id.date_event)
        var textview3: TextView =view.findViewById(R.id.formateur)
        var event : Events =items[position]
        //  imageview.setImageDrawable(mctx.resources.getDrawable(club.))
        textview1.text=event.name_event
        textview2.text=event.date
        textview3.text=event.description
        imagev.setOnClickListener{

            var builder : AlertDialog.Builder= AlertDialog.Builder(context)
            //  var inflatar:LayoutInflater=layoutInflater
            val v : View =layoutInflater.inflate(R.layout.dialog_msg,null)
            builder.setView(v)
            builder.setNegativeButton("No" ,object : DialogInterface.OnClickListener{
                override fun onClick (dialog: DialogInterface?, which:Int)
                {
                    if (dialog != null) {
                        dialog.dismiss()
                    }

                }
            })

            builder.setPositiveButton("Yes" ,object : DialogInterface.OnClickListener{
                override fun onClick (dialog: DialogInterface?, which:Int)
                {
                    mDatabase = FirebaseDatabase.getInstance()
                    mDatabaseReference = mDatabase!!.reference!!.child("Events")
                    val mUserReference = mDatabaseReference!!.child(event.eventId).removeValue()
                    // remove(event)
                }
            })
            var dialog: Dialog =builder.create()
            dialog.show()


        }


        return view

    }

}