package com.example.mylessonschedule.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mylessonschedule.databinding.ActivityMainBinding
import com.example.mylessonschedule.ui.fragmenthome.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            startFragment(HomeFragment())
        }
    }

    fun startFragment(fragmentHome: Fragment) {
        supportFragmentManager.beginTransaction().replace(
            binding.container.id, fragmentHome
        ).commitNow()
    }
}