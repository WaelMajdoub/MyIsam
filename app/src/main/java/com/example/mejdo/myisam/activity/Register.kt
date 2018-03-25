package com.example.mejdo.myisam.activity

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mejdo.myisam.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    //var myAuth = FirebaseAuth.getInstance()

    lateinit var editText1 : EditText
    lateinit var editText2 : EditText
    lateinit var editText3 : EditText
    lateinit var editText4 : EditText
    lateinit var editText5 : EditText
    lateinit var register : Button
    lateinit var login : TextView
    lateinit var myAuth:FirebaseAuth
    lateinit var progressBar: ProgressDialog
  //  lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editText1=findViewById(R.id.email)
        editText2=findViewById(R.id.password)
        editText3=findViewById(R.id.firstName)
        editText4=findViewById(R.id.lastName)
        editText5=findViewById(R.id.cin)
        register=findViewById(R.id.register)
        login=findViewById(R.id.login)
        myAuth= FirebaseAuth.getInstance()
        progressBar= ProgressDialog(this)
       /* mToolbar=findViewById(R.id.mainToolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setTitle("Register")*/

        register.setOnClickListener{
            val email=editText1.text.toString().trim()
            val password=editText2.text.toString().trim()
            val firstName=editText3.text.toString().trim()
            val lastName=editText4.text.toString().trim()
            val cin=editText5.text.toString().trim()

            if (email.isEmpty()){
                Toast.makeText(this,"Please enter your email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Please enter your password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            signUp(firstName,lastName,cin,email, password)
        }

        login.setOnClickListener{
            var intent : Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUp(firstName:String,lastName:String,cin:String,email : String, password: String) {
        progressBar.setMessage("Please wait ....")
        progressBar.show()

        myAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUser = FirebaseAuth.getInstance().currentUser
                        val uid=currentUser!!.uid
                        val userMap=HashMap<String,String>()
                        userMap["firstName"] = firstName
                        userMap["lastName"] = lastName
                        userMap["cin"] = cin
                        userMap["email"] = email
                        userMap["role"] = "user"

                        val myDataBase= FirebaseDatabase.getInstance().getReference("Users").child(uid)
                        myDataBase.setValue(userMap).addOnCompleteListener(OnCompleteListener { task ->
                            Toast.makeText(this, "Task callback function 1 done sucessfuly", Toast.LENGTH_LONG).show()
                            progressBar.dismiss()
                            val intent= Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        })

                        Toast.makeText(this, "done sucessfuly", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Sorry " + task.exception?.message, Toast.LENGTH_LONG).show()
                    }
                })
    }

}
