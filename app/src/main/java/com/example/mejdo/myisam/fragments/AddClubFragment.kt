package com.example.mejdo.myisam.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.R
import com.google.firebase.database.FirebaseDatabase

/**
 * A simple [Fragment] subclass.
 */
class AddClubFragment : Fragment() {

    lateinit var save: Button
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_add_club, container, false)

        save = view.findViewById<Button>(R.id.save)
         editText1 = view.findViewById<EditText>(R.id.name)
         editText2 = view.findViewById<EditText>(R.id.type)
         editText3 = view.findViewById<EditText>(R.id.description)


        save.setOnClickListener{
            val name= editText1.text.toString().trim()
            val type= editText2.text.toString().trim()
            val description= editText3.text.toString().trim()

            val myDataBase= FirebaseDatabase.getInstance().getReference("Clubs")
            val clubId = myDataBase.push().key
            val club = Clubs(clubId, name, type, description)
            myDataBase.child(clubId).setValue(club).addOnCompleteListener{
                Toast.makeText(view.context,"Saved succesfuly.",Toast.LENGTH_SHORT).show()
            }
        }
        return view
}

override fun onActivityCreated(savedInstanceState: Bundle?) {
super.onActivityCreated(savedInstanceState)
}

companion object {
fun newInstance(): AddClubFragment = AddClubFragment()
}

    fun saveData(){


    }

}// Required empty public constructor
