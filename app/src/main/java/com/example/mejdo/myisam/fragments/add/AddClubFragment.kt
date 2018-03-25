package com.example.mejdo.myisam.fragments.add


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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.Query
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.mejdo.myisam.utils.SessionManager
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




/**
 * A simple [Fragment] subclass.
 */
class AddClubFragment : Fragment() {

    lateinit var save: Button
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText
    lateinit var mAuth: FirebaseAuth
    lateinit var session: SessionManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_add_club, container, false)

         save = view.findViewById<Button>(R.id.save)
         editText1 = view.findViewById<EditText>(R.id.name)
         editText2 = view.findViewById<EditText>(R.id.type)
         editText3 = view.findViewById<EditText>(R.id.description)
        session = SessionManager(view.context)
        session.checkLogin()

        save.setOnClickListener{
            val name= editText1.text.toString().trim()
            val type= editText2.text.toString().trim()
            val description= editText3.text.toString().trim()
            mAuth=FirebaseAuth.getInstance()
            val uid=mAuth.currentUser?.uid

         /*   val ref =FirebaseDatabase.getInstance().getReference("Clubs")
           Toast.makeText(view.context,"Saved succesfuly."+query,Toast.LENGTH_SHORT).show()*/


                val myDataBase = FirebaseDatabase.getInstance().getReference("Clubs")
                val clubId = myDataBase.push().key
                val etat="0"
                val club = Clubs(clubId, name, type,description,etat,uid!!)
                myDataBase.child(clubId).setValue(club).addOnCompleteListener {
                    Toast.makeText(view.context, "Veuillez attendez la confiramation", Toast.LENGTH_SHORT).show()
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
