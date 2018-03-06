package com.example.mejdo.myisam

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var register : Button
    lateinit var login : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editText1=findViewById(R.id.email)
        editText2=findViewById(R.id.password)
        register=findViewById(R.id.register)
        login=findViewById(R.id.login)

        register.setOnClickListener{
            val email=editText1.text.toString().trim()
            val password=editText2.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this,"Please enter your email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            signUp(email, password)
        }

        login.setOnClickListener{
            var intent : Intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(email : String, password: String) {
        myAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "done sucessfuly", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Sorry " + task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
    }
}
