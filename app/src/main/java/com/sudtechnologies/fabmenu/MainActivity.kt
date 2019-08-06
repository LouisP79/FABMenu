package com.sudtechnologies.fabmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewAnimationUtils
import kotlin.math.hypot
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {showMenuFab()}
        overlay.setOnClickListener {hideMenuFab() }
        fab1.setOnClickListener { Toast.makeText(this, "Fab 1", Toast.LENGTH_SHORT).show() }
        fab2.setOnClickListener { Toast.makeText(this, "Fab 2", Toast.LENGTH_SHORT).show() }

    }

    override fun onBackPressed() {
        if(fab.visibility != View.VISIBLE) {
            hideMenuFab()
            return
        }
        super.onBackPressed()
    }

    private fun showMenuFab(){
        card.visibility = View.VISIBLE
        overlay.visibility = View.VISIBLE
        fab.hide()

        val animatorCard = ViewAnimationUtils.createCircularReveal(
            card,
            card.width - (fab.width / 2),
            card.height - (fab.height / 2),
            0F,
            hypot(card.width.toDouble(), card.height.toDouble()).toFloat()
        )

        animatorCard.interpolator = AccelerateDecelerateInterpolator()
        animatorCard.duration = 400
        animatorCard.start()
    }

    private fun hideMenuFab(){
        overlay.visibility = View.GONE
        card.visibility = View.INVISIBLE
        fab.show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
