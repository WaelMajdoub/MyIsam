package com.example.mejdo.myisam

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.listClub-> {
                val ListClubFragment = ListClubFragment.newInstance()
                openFragment(ListClubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.addClub -> {
                val AddClubFragment = AddClubFragment.newInstance()
                openFragment(AddClubFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.logout -> {
               //myAuth.signOut()
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
}
