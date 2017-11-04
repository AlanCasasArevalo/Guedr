package com.example.alancasas.guedr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class ForecastActivity : AppCompatActivity() {

    val TAG = ForecastActivity::class.java.canonicalName

    companion object {
        val REQUEST_UNITS = 1
    }

    var maxTemp: TextView? = null
    var minTemp: TextView? = null

    var forecast:Forecast? = null
        set(value){

            //si hago esto lo que hacemos es como inicializar los valores
            field = value

            //Acceder a las vistas de la interface
            val foreCastImage:ImageView = findViewById(R.id.forecast_image)
            maxTemp = findViewById(R.id.max_temp)
            minTemp = findViewById(R.id.min_temp)
            val humidity = findViewById<TextView>(R.id.humidity)
            val foreCastDescription: TextView = findViewById(R.id.forecast_description)

            //Actualizar la vista con el modelo
            if (value != null) {
                foreCastImage.setImageResource(value.icon)
                foreCastDescription.setText(value.description)

                updateTemperature()

                val humidityTempString = getString(R.string.humidity_format, value.humitity)
                humidity.text = humidityTempString
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        forecast = Forecast(25f,18f,40f,"Calor",R.drawable.ico_01)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if ( item?.itemId == R.id.menu_show_settings) {
            //Se ha pulsado menu mostrar ajustes y hay que mostrar la pantalla de ajustes

            //Creamos un intent(Evento llamado) le pasamos el contexto (Esta actividad), le pasamos la clase de actividad
            // a la que tiene que mostrar que se escribe ClaseAMostrar::class.java
            val intent = SettingsActivity.intent(this)
            //Esto se hace solo si la pantalla a la que navegamos no nos devuelve nada, en el caso de que la pantalla nos devuelva algo se hace de otra manera
            //startActivity(intent)

            startActivityForResult(intent, REQUEST_UNITS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //llamamos a este metodo, pasandole el intent al que queremos ir, y un numero que queramos.
    //Se llama desde startActivityForResult(intent,1)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // requestCode es el numero que nos hemos inventado, el resultCode y el data son los datos recibidos de la actividad
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_UNITS) {
            if (resultCode == Activity.RESULT_OK) {
                val unitSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)

//                if(unitSelected == R.id.celsius_rb){
//                    Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Celsius en el Radio boton")
//                }else if (unitSelected == R.id.farenheit_rb) {
//                    Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Farenheit en el Radio boton")
//                }
                //Este es el switch de Kotlin
                when (unitSelected) {
                    R.id.celsius_rb -> {
                        Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Celsius en el Radio boton")
                    }
                    R.id.farenheit_rb -> {
                        Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Farenheit en el Radio boton")
                    }

                }

                        //El NuserDefaults(iOS) de Android
                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit()
                        //Guarda el resultado de comparar unitSelected y R.id.celsius_rb
                        .putBoolean(PREFERENCE_SHOW_CELSIUS, unitSelected == R.id.celsius_rb)
                        .apply()

                updateTemperature()

            } else {
                Log.v(TAG, "Estamos en el Forecast y han pulsado Cancel")
            }
        }
    }

    private fun updateTemperature() {
        val units = temperatureUnits()
        val unitsString = temperatureUnitsString(units)
        val maxTempString = getString(R.string.max_temp_format, forecast?.maxTemp, unitsString)
        val minTempString = getString(R.string.min_temp_format, forecast?.minTemp, unitsString)

        //Se puede usar de las 2 maneras
        maxTemp?.setText(maxTempString)
        minTemp?.text = minTempString
    }

    private fun temperatureUnitsString(units: Forecast.TempUnit) = if (units == Forecast.TempUnit.CELSIUS) "°C" else "F"


//    Se puede poner de las tres maneras
//    private fun temperatureUnits(): Forecast.TempUnit = when(PreferenceManager.getDefaultSharedPreferences(this)
//                                                                            .getBoolean(PREFERENCE_SHOW_CELSIUS,true)){
//                                                                                true -> Forecast.TempUnit.CELSIUS
//                                                                                false -> Forecast.TempUnit.FAHRENHEIT
//                                                        }

    private fun temperatureUnits(): Forecast.TempUnit =
            if(PreferenceManager.getDefaultSharedPreferences(this)
                    .getBoolean(PREFERENCE_SHOW_CELSIUS,true)){
                Forecast.TempUnit.CELSIUS
            }else {
                Forecast.TempUnit.FAHRENHEIT
            }
    
//    private fun temperatureUnits(): Forecast.TempUnit {
//
//        return when(PreferenceManager.getDefaultSharedPreferences(this)
//                .getBoolean(PREFERENCE_SHOW_CELSIUS,true)){
//            true -> Forecast.TempUnit.CELSIUS
//            false -> Forecast.TempUnit.FAHRENHEIT
//        }
//
//    }


}






































