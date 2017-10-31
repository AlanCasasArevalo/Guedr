package com.example.alancasas.guedr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = MainActivity::class.java.canonicalName
    var offlineWeather : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.stone_button).setOnClickListener(this)
        findViewById<Button>(R.id.donkey_button).setOnClickListener(this)

        offlineWeather = findViewById(R.id.offline_weather_image)

        Log.v(TAG, "He pasado por el metodo onCreate")

        if (savedInstanceState != null) {
            Log.v(TAG, savedInstanceState.getString("clave"))
            Log.v(TAG, "saveIntance no es null y la clave es: ${savedInstanceState.getString("clave")}")
        }else{
            Log.v(TAG, "onSaveInstanceState no es null")
        }

    }

    override fun onClick(v: View?) {

        Log.v(TAG, when (v?.id) {
            R.id.stone_button -> {

                "Han pulsado piedra"
            }
            R.id.donkey_button -> "han pulsado burro"
            else -> "No se que has pulsado socio"
        })

//        when (v?.id){
//            R.id.stone_button -> offlineWeather?.setImageResource(R.drawable.offline_weather)
//            R.id.donkey_button -> offlineWeather?.setImageResource(R.drawable.offline_weather2)
//        }

        offlineWeather?.setImageResource(when (v?.id) {
            R.id.stone_button -> R.drawable.offline_weather2
            else -> R.drawable.offline_weather
        })

        Log.v(TAG, "He pasado por onClick")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG, "He pasado por el metodo onSaveInstanceState")
        outState?.putString("clave", "valor")
    }
}






































