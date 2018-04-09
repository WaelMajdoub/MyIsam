package com.example.mejdo.myisam.activity
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.add.AddEventFragment
import com.example.mejdo.myisam.fragments.list.AdapterListeClub
import com.example.mejdo.myisam.fragments.list.ListeEventFragment
import com.example.mejdo.myisam.model.Clubs
import com.example.mejdo.myisam.utils.SessionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var myAuth = FirebaseAuth.getInstance()
    lateinit var ref :DatabaseReference
    lateinit var session: SessionManager
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var login : Button
    lateinit var register : TextView
    lateinit var progressBar: ProgressDialog
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null
    //lateinit var mToolbar: Toolbar
    private lateinit var relativelayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        relativelayout=findViewById(R.id.cord)
        session=SessionManager(applicationContext)
        if (session.isLoggedIn()){
            var i:Intent= Intent(applicationContext,Home::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }
        editText1=findViewById(R.id.email)
        editText2=findViewById(R.id.password)
        login= findViewById(R.id.login)
        register= findViewById(R.id.register)
        progressBar= ProgressDialog(this)
        /*  mToolbar=findViewById(R.id.mainToolbar)
          setSupportActionBar(mToolbar)
          supportActionBar!!.setTitle("Login")*/
        register.setOnClickListener{
            var intent : Intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }
        login.setOnClickListener{
            view ->
            val email=editText1.text.toString().trim()
            val password=editText2.text.toString().trim()
            if (email.isEmpty() && password.isEmpty() ){
                Toast.makeText(this,"Please enter your email and password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if (email.isEmpty()){
               Toast.makeText(this,"Please enter your email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if (password.isEmpty()){
                Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            signIn(view,email,password)
        }

    }

    private fun signIn(view:View,email:String,password:String){
        progressBar.setMessage("Please wait ....")
        progressBar.show()
        myAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        session.createLoginSession(email,password)
                     //recupere role user ye sirine
                     //    val currentUser = FirebaseAuth.getInstance().currentUser
                    //    val uid=currentUser!!.uid
                      //  ref=FirebaseDatabase.getInstance().getReference("Users")

                        mDatabase = FirebaseDatabase.getInstance()
                        mDatabaseReference = mDatabase!!.reference!!.child("Users")
                        mAuth = FirebaseAuth.getInstance()
                        val mUser = mAuth!!.currentUser
                        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
                        mUserReference.addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                  val role = snapshot.child("role").value as String

                              //  val sp= getSharedPreferences("sp",Context.MODE_PRIVATE)
                              //  val spp = sp.edit()
                              //  spp.putString("role",role);
                              //  spp.apply()
                             /*   val mFragment = ListeEventFragment()
                                val mArgs = Bundle()
                                mArgs.putString("role1", role)
                                mFragment.setArguments(mArgs)*/

                                if ((role=="user") || (role=="responsable")){
                                    var intent= Intent(this@MainActivity, Home::class.java)
                                    startActivity(intent)
                                    finish()
                                   // Toast.makeText(this,"Role="+role, Toast.LENGTH_LONG).show()

                                }
                                else {
                                    var intent= Intent(this@MainActivity, Home_admin::class.java)
                                    startActivity(intent)
                                    finish()
                                   // Toast.makeText(this,"Role="+role, Toast.LENGTH_LONG).show()
                                }

                            }
                            override fun onCancelled(databaseError: DatabaseError) {}

                        })



                         // ref.child(uid).child("role")
                        // val role
                       //  val mobileNumExistanceQuery = ref.orderByChild("email").equalTo(email)
                        //val sp= getSharedPreferences("sp", Context.MODE_PRIVATE)
                      //  val role = sp.getString("role","")




                    } else {
                        progressBar.dismiss()
                        Toast.makeText(this, "Sorry " + task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
    }

}
