package com.example.mejdo.myisam.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.add.AddClubFragment
import com.example.mejdo.myisam.fragments.list.ListClubFragment
import com.example.mejdo.myisam.fragments.about.about
import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var mToolbar: Toolbar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.listClub -> {
                val ListClubFragment = ListClubFragment.newInstance()
                openFragment(ListClubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.addClub -> {
                val AddClubFragment = AddClubFragment.newInstance()
                openFragment(AddClubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.about -> {
                val about = about.newInstance()
                openFragment(about)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fargment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mToolbar=findViewById(R.id.mainToolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setTitle("Home")

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val transaction = supportFragmentManager.beginTransaction()
        val ListClubFragment = ListClubFragment.newInstance()
        transaction.replace(R.id.fargment_container, ListClubFragment).commit()


        myAuth.addAuthStateListener {
            if (myAuth.currentUser==null){
                this.finish()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflatet = menuInflater
        inflatet.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.logout){
            myAuth.signOut()
            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (item?.itemId == R.id.profil){
            Toast.makeText(this, "profil", Toast.LENGTH_LONG).show()
        }
        return true
    }

}
