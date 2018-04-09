package com.example.mejdo.myisam.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.mejdo.myisam.R
import com.example.mejdo.myisam.fragments.list.ListClubFragment
import com.example.mejdo.myisam.fragments.list.ListeDemandeClub
import com.example.mejdo.myisam.fragments.list.ListeDemandeEvent
import com.example.mejdo.myisam.fragments.profil.ProfilFragment
import com.example.mejdo.myisam.utils.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class Home_admin : AppCompatActivity() {

    var myAuth = FirebaseAuth.getInstance()
    lateinit var mToolbar: Toolbar
    lateinit var tabLayout: TabLayout
    lateinit var btn: BottomNavigationView
    lateinit var session: SessionManager


    @SuppressLint("ResourceAsColor") private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        btn=findViewById(R.id.navigation)

            when (item.itemId) {

                R.id.listClub -> {

                    /* mToolbar.setBackgroundColor(Color.parseColor("#073C43"))
                     btn.setBackgroundColor(Color.parseColor("#073C43"))*/
                    //  mToolbar.setBackgroundColor(R.color.white)
                   val ListClubFragment = ListClubFragment.newInstance()
                   openFragment(ListClubFragment)
                   return@OnNavigationItemSelectedListener true

                }

                R.id.demande -> {
                    /* mToolbar.setBackgroundColor(Color.parseColor("#14515A"))
                      btn.setBackgroundColor(Color.parseColor("#14515A"))*/

                    val ListeDemandeClub = ListeDemandeClub.newInstance()
                    openFragment(ListeDemandeClub)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.demandeEvent -> {
                    /* mToolbar.setBackgroundColor(Color.parseColor("#14515A"))
                      btn.setBackgroundColor(Color.parseColor("#14515A"))*/

                    val ListeDemandeEvent = ListeDemandeEvent.newInstance()
                    openFragment(ListeDemandeEvent)
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
        setContentView(R.layout.activity_home_admin)

        session = SessionManager(applicationContext)
        session.checkLogin()
        mToolbar=findViewById(R.id.mainToolbar)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setTitle("administration")

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
            session.logoutUser()
            myAuth.signOut()
            val intent= Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (item?.itemId == R.id.profil){
            val profil= ProfilFragment.newInstance()
            openFragment(profil)
        }
        return true
    }

}
