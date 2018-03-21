package com.example.mejdo.myisam.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.utils.SessionManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var session: SessionManager
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var login : Button
    lateinit var register : TextView
    lateinit var progressBar: ProgressDialog
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
                        var intent= Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                        progressBar.dismiss()
                    } else {
                        progressBar.dismiss()
                        Toast.makeText(this, "Sorry " + task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
    }

}
