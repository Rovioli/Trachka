package org.rovioli.trachka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navView.menu.getItem(position).isChecked = true
            }

        })
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

}
