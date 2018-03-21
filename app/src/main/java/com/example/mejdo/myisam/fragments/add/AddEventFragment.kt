package com.example.mejdo.myisam.fragments.add


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.list.TimePickerFragment
import com.example.mejdo.myisam.model.Events
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.R.attr.defaultValue
import android.R.attr.key
import android.R.attr.key
import android.widget.TextView








/**
 * A simple [Fragment] subclass.
 */
class AddEventFragment : Fragment() {

    lateinit var save: Button
    lateinit var timePicker: Button

    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText
    lateinit var editText4 : EditText
    lateinit var editText5 : EditText

    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_event, container, false)








        editText1 = view.findViewById<EditText>(R.id.name)
        editText2 = view.findViewById<EditText>(R.id.type)
        editText3 = view.findViewById<EditText>(R.id.description)
        editText4 = view.findViewById<EditText>(R.id.date)
        editText5 = view.findViewById<EditText>(R.id.prix)


        save = view.findViewById<Button>(R.id.save)


            save.setOnClickListener{

            val name= editText1.text.toString().trim()
            val type= editText2.text.toString().trim()
            val description= editText3.text.toString().trim()
            val date= editText4.text.toString().trim()
            val prix= editText5.text.toString().trim()

            mAuth=FirebaseAuth.getInstance()
            val uid=mAuth.currentUser?.uid
            val id = this.arguments!!.getString("id")
            val myDataBase= FirebaseDatabase.getInstance().getReference("Events")
            val eventId = myDataBase.push().key
            val event = Events(eventId, name, type, description , date ,prix,id)
            myDataBase.child(eventId).setValue(event).addOnCompleteListener{
                Toast.makeText(view.context,"Saved succesfuly.",Toast.LENGTH_SHORT).show()
            }


        }




        timePicker = view.findViewById<Button>(R.id.timePicker)
        timePicker.setOnClickListener(View.OnClickListener {
            val newFragment = TimePickerFragment()
            newFragment.show(activity!!.fragmentManager, "msg")
        })

        return view




    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(): AddEventFragment = AddEventFragment()
    }

    fun saveData(){


    }


}// Required empty public constructor
