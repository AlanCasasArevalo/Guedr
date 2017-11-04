package com.example.alancasas.guedr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.RadioGroup

class SettingsActivity : AppCompatActivity (){

    companion object {
        val EXTRA_UNITS = "EXTRA_UNITS"
//        fun intent(context: Context) : Intent{
//            return Intent(context, SettingsActivity::class.java)
//        }
        //Se puede hacer de las dos maneras
        fun intent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    var radioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<Button>(R.id.ok_button).setOnClickListener { acceptSettings() }

        findViewById<Button>(R.id.cancel_button).setOnClickListener { cancelSettings() }

        radioGroup = findViewById(R.id.units_rg)

    }

    private fun acceptSettings(){
        setResult(Activity.RESULT_OK)
        val returnIntent = Intent()

        returnIntent.putExtra(EXTRA_UNITS, radioGroup?.checkedRadioButtonId)
        setResult(Activity.RESULT_OK, returnIntent)

        // Finalizar la actividad regresando a la anterior
        finish()
    }

    private fun cancelSettings(){
        setResult(Activity.RESULT_CANCELED)
        // Finalizar la actividad regresando a la anterior
        finish()
    }

}



























