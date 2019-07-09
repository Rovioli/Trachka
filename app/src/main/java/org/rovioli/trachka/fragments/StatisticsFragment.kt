package org.rovioli.trachka.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.rovioli.trachka.R
import org.rovioli.trachka.Spending

class StatisticsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.stats_fragment, container, false)
        return view
    }

    private fun initTop(context: Context, body: List<Spending>) {
        val spending = body.sortedByDescending { it.dow }
    }
}