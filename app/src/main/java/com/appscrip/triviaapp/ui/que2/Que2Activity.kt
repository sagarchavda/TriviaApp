package com.appscrip.triviaapp.ui.que2

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.appscrip.triviaapp.BaseActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityQue2Binding
import com.appscrip.triviaapp.ui.summary.SummaryActivity
import com.appscrip.triviaapp.utils.SharedPrefHelper

/**
 * This screen showing question with multiple option and multi answer selection
 */
class Que2Activity : BaseActivity() {

    private lateinit var binding: ActivityQue2Binding
    private var mAnswer: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_que2)
        initView()
    }

    /**
     * It's perform view initializing
     */
    private fun initView() {
        binding.btnNext.setOnClickListener {
            run {
                if (isValid()) {
                    getSharedPref().setPreference(SharedPrefHelper.ANS2, mAnswer)
                    redirectToNext()
                }
            }
        }
    }

    /**
     * It's used to validate user have selected any answer or not before next and display suitable message according to that
     */
    private fun isValid(): Boolean {
        var isValid = true;
        mAnswer += if (binding.cbAnswer1.isChecked) binding.cbAnswer1.text.trim() else ""
        mAnswer += if (binding.cbAnswer2.isChecked) (if (mAnswer.isNullOrEmpty()) binding.cbAnswer2.text.trim() else ", ".plus(binding.cbAnswer2.text.trim())) else ""
        mAnswer += if (binding.cbAnswer3.isChecked) (if (mAnswer.isNullOrEmpty()) binding.cbAnswer3.text.trim() else ", ".plus(binding.cbAnswer3.text.trim())) else ""
        mAnswer += if (binding.cbAnswer4.isChecked) (if (mAnswer.isNullOrEmpty()) binding.cbAnswer4.text.trim() else ", ".plus(binding.cbAnswer4.text.trim())) else ""

        if (mAnswer.isNullOrEmpty()) {
            showSnackMsg(R.string.error_msg_select_option)
            isValid = false
        } else if (mAnswer?.contains(",")?.not()!!) {
            showSnackMsg(R.string.msg_select_more_than_one)
            isValid = false
        }

        return isValid
    }

    /**
     * It's tap on button redirect to SummaryActivity
     */
    private fun redirectToNext() {
        val intent = Intent(this@Que2Activity, SummaryActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
