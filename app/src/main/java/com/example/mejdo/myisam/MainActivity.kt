package com.example.mejdo.myisam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var login : Button
    lateinit var register : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText1=findViewById(R.id.email)
        editText2=findViewById(R.id.password)
        login= findViewById(R.id.login)
        register= findViewById(R.id.register)
        register.setOnClickListener{
            var intent : Intent = Intent(applicationContext,Register::class.java)
            startActivity(intent)
        }
        login.setOnClickListener{
            view ->
            val email=editText1.text.toString().trim()
            val password=editText2.text.toString().trim()
            signIn(view,email,password)
        }
    }

    private fun signIn(view:View,email:String,password:String){
        Toast.makeText(this, "Autehn....", Toast.LENGTH_LONG).show()
        myAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var intent= Intent(this,Home::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Sorry " + task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
    }

}
