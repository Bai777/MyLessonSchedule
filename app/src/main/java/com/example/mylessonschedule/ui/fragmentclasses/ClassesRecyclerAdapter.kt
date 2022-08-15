package com.example.mylessonschedule.ui.fragmentclasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mylessonschedule.R
import com.example.mylessonschedule.databinding.ItemClassesFragmentLayoutBinding
import com.example.mylessonschedule.domain.Class

class ClassesRecyclerAdapter :
    RecyclerView.Adapter<ClassesRecyclerAdapter.ClassesViewHolder>() {

    private var dataList: MutableList<Class> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassesViewHolder {
        val binding = ItemClassesFragmentLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClassesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassesViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ClassesViewHolder(private val binding: ItemClassesFragmentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Class, position: Int) {
            binding.apply {
                title.text = data.title
                time.text = data.time
                if (position == 0) openInBtn.visibility = View.VISIBLE
                when (data.title) {
                    "History" -> {
                        icon.setImageResource(R.drawable.history)
                    }
                    "Sport" -> {
                        icon.setImageResource(R.drawable.sport)
                    }
                    "Physics" -> {
                        icon.setImageResource(R.drawable.physics)
                    }
                    "Math" -> {
                        icon.setImageResource(R.drawable.math)
                    }
                    else -> {
                        icon.setImageResource(R.drawable.literature)
                    }
                }
            }
        }
    }

    fun setData(data: List<Class>) {
        dataList = data.toMutableList()
        notifyDataSetChanged()
    }
}