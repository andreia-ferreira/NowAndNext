package com.example.nowandnext.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.nowandnext.R
import com.example.nowandnext.databinding.ItemChannelBinding
import com.example.nowandnext.model.DisplayProgram

class ChannelProgramRecyclerViewAdapter(private val context: Context, private val listProgramming: List<DisplayProgram>)
    : RecyclerView.Adapter<ChannelProgramRecyclerViewAdapter.ChannelViewHolder>() {

    lateinit var binding: ItemChannelBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.item_channel,
            parent,
            false)

        return ChannelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProgramming.size
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        return holder.bind(listProgramming[position])
    }

    inner class ChannelViewHolder(private var binding: ItemChannelBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(program: DisplayProgram) {
            binding.setVariable(BR.program, program)
            binding.executePendingBindings()
        }
    }
}