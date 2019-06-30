package org.rovioli.trachka

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val service = Connector.client
        val context = this

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = service.getUsers()
                val users = response.body()?.data
                val names = users?.map { it.name }
                usernameSpinner.adapter = ArrayAdapter(
                    context, android.R.layout.simple_spinner_dropdown_item, names
                )
                usernameSpinner.visibility = View.VISIBLE
                login.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            }
        }

        val intent = Intent(this, UserActivity::class.java)

        login.setOnClickListener {
            intent.putExtra("name", usernameSpinner.selectedItem.toString())
            intent.putExtra("id", usernameSpinner.selectedItemId + 1)
            startActivity(intent)
        }
    }
}
