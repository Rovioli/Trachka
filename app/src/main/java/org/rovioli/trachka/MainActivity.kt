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
        val users = arrayListOf(
            "Рома", "Дима", "Берг", "Виталий", "Холло", "Никита",
            "Игорь", "Валерия", "Борис", "Влад", "Тимофей", "Егор", "Гадель"
        )
        usernameSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, users)

        val intent = Intent(this, UserActivity::class.java)
            .putExtra("name", usernameSpinner.selectedItem.toString())
            .putExtra("id", usernameSpinner.selectedItemId)

        login.setOnClickListener {
            startActivity(intent)
        }
    }
}
