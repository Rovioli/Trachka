package org.rovioli.trachka

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.dialog_add_spending.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {

    private var userId = 0

    private val dialog: AlertDialog by lazy {
        val root = layoutInflater.inflate(R.layout.dialog_add_spending, null)
        val context = root.context
        root.dayOfWeekChooser.adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_dropdown_item,
            week
        )
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.add_spending)
            .setView(root)
            .setPositiveButton(R.string.add) { _, _ -> addSpending(context, root) }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }

        builder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val context = this
        userId = intent.getIntExtra("id", 0)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val body = Connector.client.getData().body() ?: throw NoSuchElementException("Have no body")
                initHome(context, userId, body)
                initTop(context, body)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, R.string.connection_error, Toast.LENGTH_LONG).show()
            }
        }
        addButton.setOnClickListener { dialog.show() }
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    viewPager.setCurrentItem(0, true)
                }
                R.id.navigation_top -> {
                    viewPager.setCurrentItem(1, true)
                }
                R.id.navigation_settings -> {
                    viewPager.setCurrentItem(2, true)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun initHome(context: Context, userId: Int, body: List<Spending>) {
        val spending = body
            .filter { it.userid == userId }
            .sortedByDescending { it.dow }
    }

    private fun initTop(context: Context, body: List<Spending>) {
        val spending = body.sortedByDescending { it.dow }
    }

    private fun addSpending(context: Context, root: View) {
        GlobalScope.launch(Dispatchers.Main) {
            Connector.client.addSpending(
                userId,
                root.dayOfWeekChooser.selectedItemId.toInt() + 1,
                root.comment.text.toString(),
                root.amount_of_money.text
                    .toString()
                    .toInt()
            )
            Toast.makeText(
                context,
                "Paid ${root.amount_of_money.text.toString().toInt()} for ${root.comment.text}!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
