package com.appscrip.triviaapp.ui.summary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.appscrip.triviaapp.BaseActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivitySummaryBinding
import com.appscrip.triviaapp.extension.observeOnce
import com.appscrip.triviaapp.models.History
import com.appscrip.triviaapp.ui.history.HistoryActivity
import com.appscrip.triviaapp.ui.main.MainActivity
import com.appscrip.triviaapp.utils.DateUtil
import com.appscrip.triviaapp.utils.SharedPrefHelper

/**
 * This screen is used for displaying taken quiz answer and additional option like re take test or go to history section
 */
class SummaryActivity : BaseActivity() {

    private lateinit var summaryViewModel: SummaryViewModel
    private lateinit var binding: ActivitySummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_summary)
        initView()
    }

    /**
     * It's perform view initializing and storing quiz detail into db
     */
    private fun initView() {
        summaryViewModel = ViewModelProvider(this).get(SummaryViewModel::class.java)
        binding.tvName.text =
            getString(R.string.arg_hello_name, getSharedPref().getString(SharedPrefHelper.NAME, ""))
        binding.tvAnswer1.text =
            getString(R.string.arg_answer, getSharedPref().getString(SharedPrefHelper.ANS1, ""))
        binding.tvAnswer2.text =
            getString(R.string.arg_answers, getSharedPref().getString(SharedPrefHelper.ANS2, ""))


        summaryViewModel.getCount().observeOnce(this, Observer<Int> {
            if (it != null) {
                val id: Int = it + 1
                val history = History(
                    id,
                    getSharedPref().getString(SharedPrefHelper.NAME, "")!!,
                    DateUtil.getCurrentDate(),
                    getSharedPref().getString(SharedPrefHelper.ANS1, "")!!,
                    getSharedPref().getString(SharedPrefHelper.ANS2, "")!!
                )
                summaryViewModel.insert(history)
                Log.d("APP_DEBUG", "Add Id : $id")
            }
        })

        binding.btnFinish.setOnClickListener {
            redirectToFinish()
        }

        binding.btnHistory.setOnClickListener {
            redirectToHistory()
        }
    }

    /**
     * It's tap on button redirect to MainActivity
     */
    private fun redirectToFinish() {
        getSharedPref().clearAll()
        val intent = Intent(this@SummaryActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    /**
     * It's tap on button redirect to HistoryActivity
     */
    private fun redirectToHistory() {
        getSharedPref().clearAll()
        val intent = Intent(this@SummaryActivity, HistoryActivity::class.java)
        startActivity(intent)
    }

}
