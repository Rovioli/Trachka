package org.rovioli.trachka

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private var bodytext = "noffin"
    private val dialog: AlertDialog by lazy {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.add_spending)
            .setView(layoutInflater.inflate(R.layout.dialog_add_spending, null))
            .setPositiveButton(R.string.add) { dialog, id ->

            }
            .setNegativeButton(R.string.cancel) { dialog, id ->

            }

        builder.create()
    }

    private val onNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.text = bodytext
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.text = ""
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.visibility = View.VISIBLE
                message.setText(R.string.not_implemented)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val context = this

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = Connector.client.getData()
                val body = response.body()
                if (body != null) {
                    bodytext = body.data[0].descr
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show()
            }
        }

        addButton.setOnClickListener { dialog.show() }
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
