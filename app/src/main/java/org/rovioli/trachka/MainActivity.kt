package org.rovioli.trachka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val service = (application as App).service
        val users = service.getUsers() ?: listOf()
        val names = users.map { it.name }

        usernameSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, names)

        val intent = Intent(this, UserActivity::class.java)
            .putExtra("name", usernameSpinner.selectedItem.toString())
            .putExtra("id", usernameSpinner.selectedItemId + 1)

        login.setOnClickListener {
            startActivity(intent)
        }
    }
}
