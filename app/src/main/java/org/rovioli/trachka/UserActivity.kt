package org.rovioli.trachka

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private var bodytext = ""
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                textMessage.text = bodytext
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                textMessage.text = ""
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.visibility = View.VISIBLE
                textMessage.setText(R.string.not_implemented)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val context = this

        textMessage = findViewById(R.id.message)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = Connector.client.getData()
                bodytext = response.body().toString()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show()
            }
        }

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
