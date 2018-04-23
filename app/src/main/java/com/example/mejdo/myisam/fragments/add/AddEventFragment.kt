package com.example.mejdo.myisam.fragments.add
import android.R.attr.*
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.text.format.DateFormat
import android.text.format.Time
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
import android.widget.TimePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat

import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class AddEventFragment  : Fragment()   {
  /*  private var filePath: Uri? =null
    internal var storage: FirebaseStorage?=null
    internal var storageReference:StorageReference?=null */
    lateinit var save: Button
    lateinit var editText1 : EditText
    lateinit var editText4 : EditText
    lateinit var editText5 : EditText
    lateinit var form : EditText
    lateinit var heur : EditText
    lateinit var mAuth: FirebaseAuth
    lateinit var uri : Uri
    lateinit var mStorage : StorageReference
    val file : Int=0

    @RequiresApi(Build.VERSION_CODES.N) override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?  {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_event, container, false)
      /*  storage= FirebaseStorage.getInstance()
        storageReference=storage!!.reference */




        var names= arrayOf("licence im","licence cm"," ingéneur","master","pour tous")
        editText1 = view.findViewById<EditText>(R.id.name)
        editText4 = view.findViewById<EditText>(R.id.date)
        editText5 = view.findViewById<EditText>(R.id.prix)
        val image =view.findViewById<View>(R.id.image)
        mStorage = FirebaseStorage.getInstance().getReference("Uploads")
        form=view.findViewById<EditText>(R.id.formateur_event)
        heur=view.findViewById<EditText>(R.id.heur)
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
           //  Toast.makeText(context,"Saved succesfuly."+size,Toast.LENGTH_SHORT).show()
            val name= editText1.text.toString().trim()
            val date= editText4.text.toString().trim()
            val prix= editText5.text.toString().trim()
                val f =form.text.toString().trim()
                val h =heur.text.toString().trim()



                mAuth=FirebaseAuth.getInstance()
             val uid=mAuth.currentUser?.uid
            val id = this.arguments!!.getString("id")
            val myDataBase= FirebaseDatabase.getInstance().getReference("Events")
            val eventId = myDataBase.push().key
                val etat="0"
                val event = Events(eventId, name, size, f , date,h ,prix,etat,id)
                myDataBase.child(eventId).setValue(event).addOnCompleteListener{
                Toast.makeText(view.context,"Veuillez attendez la confirmation.",Toast.LENGTH_SHORT).show()
            }
            }

        image.setOnClickListener(View.OnClickListener {
            view : View? -> val intent = Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"select image"),file)
        })

        val cal=Calendar.getInstance()
        heur.setOnClickListener(View.OnClickListener {
            val date=object:TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    cal.set(Calendar.HOUR_OF_DAY,p1)
                    cal.set(Calendar.MINUTE,p2)
                    heur.setText("$p1 : $p2 ")                }


            }

            TimePickerDialog(context,date,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE), DateFormat.is24HourFormat(getActivity())).show()
        })
            editText4.setOnClickListener(View.OnClickListener {
            val date=object:DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    cal.set(Calendar.YEAR,p1)
                    cal.set(Calendar.MONTH,p2)
                    cal.set(Calendar.DAY_OF_MONTH,p3)
                    editText4.setText("$p1 : $p2 : $p3")
                }

            }

            DatePickerDialog(context,date,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        })





      return view
}



override fun onActivityCreated(savedInstanceState: Bundle?) {
 super.onActivityCreated(savedInstanceState)
}
companion object {
 fun newInstance(): AddEventFragment = AddEventFragment()
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == file) {
                uri = data!!.data
                upload ()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun upload() {
        var mReference = mStorage.child(uri.lastPathSegment)
        try {
            mReference.putFile(uri).addOnSuccessListener {
                taskSnapshot: UploadTask.TaskSnapshot? -> var url = taskSnapshot!!.downloadUrl
            }
        }catch (e: Exception) {
            Toast.makeText(this.context, e.toString(), Toast.LENGTH_LONG).show()
        }

    }


}// Required empty public constructor

