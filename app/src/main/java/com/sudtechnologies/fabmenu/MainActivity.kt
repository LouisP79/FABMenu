package com.sudtechnologies.fabmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.fmenu.FMenu

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fMenu.setOnFMenuClickListener(object: FMenu.OnFMenuClick{
            override fun onFBT1Click() {
                Toast.makeText(this@MainActivity, "fBt1 Click", Toast.LENGTH_SHORT).show()
            }

            override fun onFBT2Click() {
                Toast.makeText(this@MainActivity, "fBt2 Click", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        if(fMenu.isFMenuOpen()) {
            fMenu.hideFMenu()
            return
        }
        super.onBackPressed()
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
