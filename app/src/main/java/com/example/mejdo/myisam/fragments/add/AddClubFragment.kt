package com.example.mejdo.myisam.fragments.add


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.Query
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.*
import com.example.mejdo.myisam.fragments.list.ListClubFragment
import com.example.mejdo.myisam.fragments.list.ListeDemandeEvent
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
        var names= arrayOf("educatif","loisir","music")
         save = view.findViewById<Button>(R.id.save)
         editText1 = view.findViewById<EditText>(R.id.name)
         editText3 = view.findViewById<EditText>(R.id.description)
         session = SessionManager(view.context)
         session.checkLogin()
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
        save.setOnClickListener{
            val size = spin.getSelectedItem().toString()
            val name= editText1.text.toString().trim()
            val description= editText3.text.toString().trim()
            mAuth=FirebaseAuth.getInstance()
            val uid=mAuth.currentUser?.uid

         /*   val ref =FirebaseDatabase.getInstance().getReference("Clubs")
           Toast.makeText(view.context,"Saved succesfuly."+query,Toast.LENGTH_SHORT).show()*/


                val myDataBase = FirebaseDatabase.getInstance().getReference("Clubs")
                val clubId = myDataBase.push().key
                val etat="0"
                val club = Clubs(clubId, name, size,description,etat,uid!!)
                myDataBase.child(clubId).setValue(club).addOnCompleteListener {
                Toast.makeText(view.context, "Veuillez attendez la confiramation", Toast.LENGTH_SHORT).show()
                    val ListClubFragment = ListClubFragment.newInstance()
                    val fragmentManager = activity!!.supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.fargment_container, ListClubFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
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
