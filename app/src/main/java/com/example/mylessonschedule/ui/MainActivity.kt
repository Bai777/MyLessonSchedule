package com.example.mylessonschedule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylessonschedule.R
import com.example.mylessonschedule.databinding.ActivityMainBinding
import com.example.mylessonschedule.ui.fragmenthome.FragmentHome

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            startFragment(FragmentHome())
        }
    }

    private fun startFragment(fragmentHome: FragmentHome) {
        supportFragmentManager.beginTransaction().replace(
            binding.container.id, fragmentHome
        ).commitNow()
    }
}