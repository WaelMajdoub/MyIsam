package com.example.mejdo.myisam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Home : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
   /* lateinit var logout: Button
    lateinit var save: Button
    lateinit var database: Button
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText
    lateinit var editText4 : EditText*/
    //lateinit var navigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    /*    navigationView = findViewById<View>(R.id.nav) as BottomNavigationView
        navigationView.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                var selectedFragment : Fragment? = null
                when(item.itemId){
                    R.id.listClub ->
                            selectedFragment = ListClubFragment.newInstance()
                    R.id.addClub ->
                        selectedFragment = AddClubFragment.newInstance()
                }
                var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.main_frame,selectedFragment)
                ft.commit()
                return true
            }
        })
        var ft : FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_frame,AddClubFragment.newInstance())
        ft.commit()*/

        /*editText1 = findViewById(R.id.firstName)
        editText2 = findViewById(R.id.lastName)
        editText3 = findViewById(R.id.adress)
        editText4 = findViewById(R.id.departement)

        logout = findViewById(R.id.logout)
        database = findViewById(R.id.database)
        save = findViewById(R.id.save)

        logout.setOnClickListener { view ->
            Toast.makeText(this, "loggin out ...", Toast.LENGTH_LONG).show()
            signOut()
        }
        myAuth.addAuthStateListener {
            if (myAuth.currentUser==null){
                this.finish()
            }
        }
        database.setOnClickListener{
            var intent : Intent = Intent(this@Home,listView::class.java)
            startActivity(intent)
        }

        save.setOnClickListener{
            saveData()
        }*/
    }

    /*fun signOut() {
        myAuth.signOut()
    }

    private fun saveData(){
        val firstName= editText1.text.toString().trim()
        val lastName = editText2.text.toString().trim()
        val adress =  editText3.text.toString().trim()
        val departement = editText4.text.toString().trim()

        val myDataBase= FirebaseDatabase.getInstance().getReference("Employees")
        val employeeId = myDataBase.push().key
        val employee = Employees(employeeId,firstName,lastName,adress,departement)
        myDataBase.child(employeeId).setValue(employee).addOnCompleteListener{
            Toast.makeText(this, "saved ...", Toast.LENGTH_LONG).show()
        }

    }*/
}
