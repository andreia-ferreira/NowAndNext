package com.example.nowandnext.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.nowandnext.R
import com.example.nowandnext.databinding.NowAndNextFragmentBinding
import com.example.nowandnext.model.DisplayProgram
import com.example.nowandnext.ui.adapter.ChannelProgramRecyclerViewAdapter

class NowAndNextFragment : Fragment() {

    private lateinit var binding: NowAndNextFragmentBinding
    private val viewModel by lazy { ViewModelProvider(this).get(NowAndNextViewModel::class.java) }
    private lateinit var mContext: Context
    private val channelList = ArrayList<DisplayProgram>()

    companion object {
        var TAG: String = NowAndNextFragment::class.java.simpleName
        fun newInstance() =
            NowAndNextFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.now_and_next_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.isLoading = viewModel.isLoading

        val layoutManager = LinearLayoutManager(mContext)
        layoutManager.orientation = VERTICAL
        binding.layoutManager = layoutManager
        binding.adapter = ChannelProgramRecyclerViewAdapter(mContext, channelList)

        viewModel.showLoading()
        initListeners()
        initObservers()
        viewModel.refreshChannels(true)

        return binding.root
    }

    private fun initListeners() {
        binding.scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && channelList.isNotEmpty()) {
                    viewModel.showLoading()
                    viewModel.refreshChannels(false)
                }
            }
        }

        binding.swipeRefreshListener = OnRefreshListener {
            viewModel.refreshChannels(true)
        }
    }

    private fun initObservers() {
        viewModel.listDisplayProgram.observe(viewLifecycleOwner, Observer { list ->
            if (list != null && list.isNotEmpty()) {
                channelList.clear()
                channelList.addAll(list)
                binding.adapter?.notifyDataSetChanged()
                viewModel.hideLoading()
            }
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {message ->
            message?.run{
                viewModel.hideLoading()
                Toast.makeText(mContext, this, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        if (viewModel.listDisplayProgram.hasObservers()) viewModel.listDisplayProgram.removeObservers(this)
        if (viewModel.isError.hasObservers()) viewModel.isError.removeObservers(this)
        viewModel.job?.cancel()
        super.onDestroy()
    }

}