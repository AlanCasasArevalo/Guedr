package com.example.alancasas.guedr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity (){

    companion object {
        val EXTRA_UNITS = "EXTRA_UNITS"

        fun intent(context: Context, units: Int) : Intent {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra(EXTRA_UNITS, units)
            return intent
        }
    }

    val radioGroup by lazy { findViewById<RadioGroup>(R.id.units_rg) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<Button>(R.id.ok_button).setOnClickListener { acceptSettings() }

        findViewById<Button>(R.id.cancel_button).setOnClickListener { cancelSettings() }

        val radioSelected = intent.getIntExtra(EXTRA_UNITS, R.id.celsius_rb)

        radioGroup.check(radioSelected)
    }

    private fun acceptSettings(){
        setResult(Activity.RESULT_OK)
        val returnIntent = Intent()

        returnIntent.putExtra(EXTRA_UNITS, radioGroup.checkedRadioButtonId)
        setResult(Activity.RESULT_OK, returnIntent)

        finish()
    }

    private fun cancelSettings(){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

}



























