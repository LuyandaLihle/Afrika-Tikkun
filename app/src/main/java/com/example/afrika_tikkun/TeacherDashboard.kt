package com.example.afrika_tikkun

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.afrika_tikkun.databinding.ActivityLoginPageTeacherBinding
import com.example.afrika_tikkun.databinding.TeacherboardBinding
import java.lang.Exception

class TeacherDashboard : AppCompatActivity() {
    private lateinit var binding: TeacherboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TeacherboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dashboardFragment = DashboardFragment()
        val assignmentsubmission1fragment = AssignmentSubmission_1Fragment()
        val marksheet1fragment = Marksheet_1Fragment()


        makeCurrentFragment(dashboardFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener { it->
            when (it.itemId) {

                R.id.submission -> makeCurrentFragment(assignmentsubmission1fragment)
                R.id.marksheet -> makeCurrentFragment(marksheet1fragment)
                R.id.dashboard -> makeCurrentFragment(dashboardFragment)
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