package com.appscrip.triviaapp

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.appscrip.triviaapp.utils.DimensionUtils
import com.appscrip.triviaapp.utils.SharedPrefHelper
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Sagar Chavda on 26/04/20.
 * Add base class for create common functions and access it easily such like display message in snackbar with single line.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var snackbar: Snackbar? = null

    /**
     * It is return instance of shared preference helper class so, that in any activity it can be accessible without initialize evey time.
     */
    fun getSharedPref(): SharedPrefHelper {
        return SharedPrefHelper(this)
    }

    /**
     * It's allow to show message in snack bar with just single line code with pass string resource id
     */
    open fun showSnackMsg(@StringRes errorMsg: Int) {
        if (snackbar == null) {
            snackbar = Snackbar.make(
                findViewById(R.id.content),
                errorMsg,
                Snackbar.LENGTH_LONG
            )
            snackbar?.show()
            return
        }

        snackbar?.setText(errorMsg)
        snackbar?.show()
    }

    /**
     * It's return instance of dimension util class so, there all method's can be accessible easily
     */
    fun getDimensionUtils(): DimensionUtils {
        return DimensionUtils(this)
    }
}