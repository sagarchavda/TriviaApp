package com.appscrip.triviaapp.ui.que1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import com.appscrip.triviaapp.BaseActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityQue1Binding
import com.appscrip.triviaapp.ui.que2.Que2Activity
import com.appscrip.triviaapp.utils.SharedPrefHelper
import com.google.android.material.radiobutton.MaterialRadioButton

/**
 * This screen showing question with multiple option and single answer selection
 */
class Que1Activity : BaseActivity() {

    private lateinit var binding: ActivityQue1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_que1)
        initView()
    }

    /**
     * It's perform view initializing
     */
    private fun initView() {
        binding.btnNext.setOnClickListener {
            run {
                if (isValid()) {
                    val radio: MaterialRadioButton = findViewById(binding.rgAnswers.checkedRadioButtonId)
                    getSharedPref().setPreference(SharedPrefHelper.ANS1, radio.text)
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

        if (binding.rgAnswers.checkedRadioButtonId == -1) {
            showSnackMsg(R.string.error_msg_select_option)
            isValid = false;
        }

        return isValid
    }

    /**
     * It's tap on button redirect to SummaryActivity
     */
    private fun redirectToNext() {
        val intent = Intent(this@Que1Activity, Que2Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}
