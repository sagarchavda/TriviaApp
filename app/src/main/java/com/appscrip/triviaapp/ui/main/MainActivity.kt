package com.appscrip.triviaapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.appscrip.triviaapp.BaseActivity
import com.appscrip.triviaapp.R
import com.appscrip.triviaapp.databinding.ActivityMainBinding
import com.appscrip.triviaapp.extension.hideKeyboard
import com.appscrip.triviaapp.ui.que1.Que1Activity
import com.appscrip.triviaapp.utils.DateUtil
import com.appscrip.triviaapp.utils.SharedPrefHelper

/**
 * This screen is taken input name for user before start quiz
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
    }

    /**
     * It's perform view initializing
     */
    private fun initView() {
        binding.edtName.requestFocus()

        binding.edtName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilName.error = null
            }
        })

        binding.btnNext.setOnClickListener {
            run {
                if (isValid()) {
                    it.hideKeyboard()
                    getSharedPref().setPreference(SharedPrefHelper.NAME, mName)
                    redirectToNext()
                }

            }
        }
    }

    /**
     * It's used to validate user have entered name or not and displaying relevant error message
     */
    private fun isValid(): Boolean {
            var isValid = true;

        mName = binding.edtName.text.toString().trim();

        if (mName.isNullOrEmpty()) {
            binding.tilName.error = getString(R.string.error_msg_name_is_required)
            isValid = false;
        }

        return isValid;
    }

    /**
     * It's tap on button redirect to Que1Activity
     */
    private fun redirectToNext() {
        val intent = Intent(this@MainActivity, Que1Activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}
