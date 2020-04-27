package com.appscrip.triviaapp.ui.history

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appscrip.triviaapp.BaseActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityHistoryBinding
import com.appscrip.triviaapp.utils.SpacingItemDecoration

/**
 * It's screen is displaying history of all taken test with their answer and date time
 */
class HistoryActivity : BaseActivity() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history)
        initView()
    }

    /**
     * It's initialize view and take data from room db and set it in history list
     */
    private fun initView() {

        val adapter = HistoryAdapter(this)
        binding.rvHistory.adapter = adapter
        binding.rvHistory.isNestedScrollingEnabled = false
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHistory.layoutManager = layoutManager
        binding.rvHistory.itemAnimator = DefaultItemAnimator()
        binding.rvHistory.addItemDecoration(
            SpacingItemDecoration(
                getDimensionUtils().dp2px(12F),
                SpacingItemDecoration.VERTICAL
            )
        )

        historyViewModel.histories.observe(this, Observer { histories ->
            histories?.let { adapter.setHistories(it) }
        })
    }
}
