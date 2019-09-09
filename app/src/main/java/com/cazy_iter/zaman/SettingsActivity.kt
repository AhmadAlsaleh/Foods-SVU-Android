package com.cazy_iter.zaman

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        Statics.setRTL_LTR(this)

        backIV.setOnClickListener {
            onBackPressed()
        }

        englishTV.setOnClickListener {
            Statics.setCurrentLanguageName(this, Statics.en)
            startActivity(Intent(this, LauncherActivity::class.java))
            finish()
        }

        arabicTV.setOnClickListener {
            Statics.setCurrentLanguageName(this, Statics.ar)
            startActivity(Intent(this, LauncherActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
