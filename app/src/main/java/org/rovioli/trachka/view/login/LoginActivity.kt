package org.rovioli.trachka.view.login

import android.content.Context
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
import org.rovioli.trachka.R
import org.rovioli.trachka.model.ZhrachkaApi
import org.rovioli.trachka.view.MainActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val intent = Intent(this, MainActivity::class.java)
        tryAgain.setOnClickListener { attemptShowContent() }
        login.setOnClickListener {
            intent.putExtra("name", usernameSpinner.selectedItem.toString())
            val userId = usernameSpinner.selectedItemId + 1
            intent.putExtra("id", userId.toInt())
            startActivity(intent)
        }

        attemptShowContent()
    }

    private fun attemptShowContent() = GlobalScope.launch(Dispatchers.Main) {
        try {
            showContent(this@LoginActivity)
        } catch (e: Throwable) {
            e.printStackTrace()
            showError(this@LoginActivity)
        }
    }

    private fun showError(context: Context) {
        Toast.makeText(context, "Connection Error", Toast.LENGTH_LONG).show()
        progressBar.visibility = View.INVISIBLE
        tryAgain.visibility = View.VISIBLE
    }

    private suspend fun showContent(context: Context) {
        tryAgain.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val response = ZhrachkaApi.CLIENT.getUsers()
        val users = response.body()
        val names = users?.map { it.name }
        usernameSpinner.adapter = ArrayAdapter(
            context, android.R.layout.simple_spinner_dropdown_item, names
        )

        usernameSpinner.visibility = View.VISIBLE
        login.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }
}
