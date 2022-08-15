package com.example.mylessonschedule.ui.fragmenthome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylessonschedule.R
import com.example.mylessonschedule.databinding.FragmentMainLayoutBinding
import com.example.mylessonschedule.domain.ClassesState
import com.example.mylessonschedule.domain.HomeWorkState
import com.example.mylessonschedule.ui.MainActivity
import com.example.mylessonschedule.ui.fragmentclasses.ClassesFragment


class HomeFragment : Fragment() {

    private var _binding: FragmentMainLayoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    private val classesAdapter = HomeClassesRecyclerAdapter()
    private val homeworkAdapter = HomeworkRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.classesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.homeworkRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.classes_nav_bar -> (requireActivity() as MainActivity).startFragment(
                    ClassesFragment(0)
                )
            }
            return@setOnItemSelectedListener true
        }
        workLivedata()

        classesAdapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(classItem: Int) {
                (requireActivity() as MainActivity).startFragment(ClassesFragment(classItem))
            }
        })
    }

    private fun workLivedata() {
        binding.classesRecycler.adapter =
            classesAdapter
        binding.homeworkRecycler.adapter =
            homeworkAdapter

        val classesObserver =
            Observer<ClassesState> { a ->
                renderClassesData(a)
            }
        val homeworkObserver =
            Observer<HomeWorkState> { a ->
                renderHomeworkData(a)
            }

        viewModel.getClassesData().observe(
            viewLifecycleOwner,
            classesObserver
        )
        viewModel.getHomeworkData().observe(
            viewLifecycleOwner,
            homeworkObserver
        )
        viewModel.getClasses()
        viewModel.getHomework()
    }

    private fun renderClassesData(data: ClassesState) = when (data) {
        is ClassesState.Success -> {
            val dataList = data.data
            binding.loadingLayout.visibility = View.GONE
            classesAdapter.setData(dataList)
        }
        is ClassesState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is ClassesState.Error -> {
            binding.loadingLayout.visibility = View.GONE

        }
    }

    private fun renderHomeworkData(data: HomeWorkState) = when (data) {
        is HomeWorkState.Success -> {
            val dataList = data.data
            binding.loadingLayout.visibility = View.GONE
            homeworkAdapter.setData(dataList)
        }
        is HomeWorkState.Loading -> {
            binding.loadingLayout.visibility = View.VISIBLE
        }
        is HomeWorkState.Error -> {
            binding.loadingLayout.visibility = View.GONE

        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(classItem: Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        classesAdapter.removeOnItemViewClickListener()
    }
}