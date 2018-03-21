package com.example.mejdo.myisam.fragments.add
import android.R.attr.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.list.TimePickerFragment
import com.example.mejdo.myisam.model.Events
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.util.Log
import android.view.ViewParent
import android.widget.*
import com.example.mejdo.myisam.fragments.list.adapter_liste_event
/**
 * A simple [Fragment] subclass.
 */
class AddEventFragment : Fragment() {

    lateinit var save: Button
    lateinit var timePicker: Button
    lateinit var editText1 : EditText
    lateinit var editText3 : EditText
    lateinit var editText4 : EditText
    lateinit var editText5 : EditText
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_event, container, false)
        var names= arrayOf("educatif","loisir","music")
        editText1 = view.findViewById<EditText>(R.id.name)
        editText3 = view.findViewById<EditText>(R.id.description)
        editText4 = view.findViewById<EditText>(R.id.date)
        editText5 = view.findViewById<EditText>(R.id.prix)
        var spin: Spinner =view.findViewById<Spinner>(R.id.spin)
        var a: ArrayAdapter<String> = ArrayAdapter(activity,android.R.layout.simple_spinner_item,names)
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = a

        spin.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view:  View?, position: Int, id: Long) {
               // Toast.makeText(context,"Saved succesfuly."+names[position],Toast.LENGTH_SHORT).show()


            }

        }
            save = view.findViewById<Button>(R.id.save)
            save.setOnClickListener{
               val size = spin.getSelectedItem().toString()
                Toast.makeText(context,"Saved succesfuly."+size,Toast.LENGTH_SHORT).show()

                val name= editText1.text.toString().trim()
            val description= editText3.text.toString().trim()
            val date= editText4.text.toString().trim()
            val prix= editText5.text.toString().trim()

            mAuth=FirebaseAuth.getInstance()
            val uid=mAuth.currentUser?.uid
            val id = this.arguments!!.getString("id")
            val myDataBase= FirebaseDatabase.getInstance().getReference("Events")
            val eventId = myDataBase.push().key
            val event = Events(eventId, name, size, description , date ,prix,id)
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
