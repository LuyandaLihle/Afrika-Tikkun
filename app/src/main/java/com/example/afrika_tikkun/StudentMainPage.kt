package com.example.afrika_tikkun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.afrika_tikkun.databinding.ActivityLoginPageBinding
import com.example.afrika_tikkun.databinding.ActivityStudentMainPageBinding
import java.lang.Exception

class StudentMainPage : AppCompatActivity() {
    private lateinit var binding: ActivityStudentMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        val moduleFragment = ModuleFragment()
        val resultFragment = ResultFragment()


        makeCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { it->
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_modules -> makeCurrentFragment(moduleFragment)
                R.id.ic_results -> makeCurrentFragment(resultFragment)
            }
            true
        }
    }
    private fun makeCurrentFragment(fragment: Fragment) {
        // Use FragmentTransaction to handle fragment transactions
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_wrapper, fragment)
        fragmentTransaction.commit()
    }
}