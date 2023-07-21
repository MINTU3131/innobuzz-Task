package com.mintusharma.innobuzz.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.snackbar.Snackbar
import com.mintusharma.innobuzz.R
import com.mintusharma.innobuzz.databinding.ActivityMainBinding
import com.mintusharma.innobuzz.fragments.PostListFragment
import com.mintusharma.innobuzz.utils.NetworkUtils


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!NetworkUtils.isNetworkAvailable(applicationContext)) {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Please Turn On Internet",
                Snackbar.LENGTH_LONG
            ).show()
        }

        setUpFragment();

    }

    fun checkAccessibilityPermission(): Boolean {
        var accessEnabled = 0
        try {
            accessEnabled =
                Settings.Secure.getInt(this.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
        return if (accessEnabled == 0) {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
            false
        } else {
            true
        }
    }

    private fun setUpFragment() {
        val dataDetailScreenFragment = PostListFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_container, dataDetailScreenFragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.allow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.accessibility-> {
                if (!checkAccessibilityPermission()) {
                    Toast.makeText(applicationContext, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}