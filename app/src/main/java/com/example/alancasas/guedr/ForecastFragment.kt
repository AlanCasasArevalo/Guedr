package com.example.alancasas.guedr

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView

class ForecastFragment : Fragment() {

    val TAG = ForecastActivity::class.java.canonicalName

    lateinit var rootView : View

    lateinit var maxTemp: TextView
    lateinit var minTemp: TextView

    companion object {
        val REQUEST_UNITS = 1
    }

    var forecast:Forecast? = null
        set(value){

            field = value

            val foreCastImage = rootView.findViewById<ImageView>(R.id.forecast_image)
            maxTemp = rootView.findViewById(R.id.max_temp)
            minTemp = rootView.findViewById(R.id.min_temp)
            val humidity = rootView.findViewById<TextView>(R.id.humidity)
            val foreCastDescription: TextView = rootView.findViewById(R.id.forecast_description)

            value?.let {
                foreCastImage.setImageResource(value.icon)
                foreCastDescription.setText(value.description)

                updateTemperature()

                val humidityTempString = getString(R.string.humidity_format, value.humitity)
                humidity.text = humidityTempString
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        inflater?.let {
            //It es cuando inflater no es null es igual que hacer if (inflater != null)
            rootView = it.inflate(R.layout.fragment_forecast,container, false)
            //IMPORTANTE EL MODELO HA DE ESTAR DESPUES DE QUE LA VISTA ESTE CREADO
            forecast = Forecast(25f,18f,40f,"Calor",R.drawable.ico_01)
        }

        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.settings, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if ( item?.itemId == R.id.menu_show_settings) {

            val units = if (temperatureUnits() == Forecast.TempUnit.CELSIUS)
                R.id.celsius_rb
            else
                R.id.farenheit_rb

            val intent = SettingsActivity.intent(activity, units)

            startActivityForResult(intent, ForecastFragment.REQUEST_UNITS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateTemperature() {
        val units = temperatureUnits()
        val unitsString = temperatureUnitsString(units)
        val maxTempString = getString(R.string.max_temp_format, forecast?.getMaxTemp(units), unitsString)
        val minTempString = getString(R.string.min_temp_format, forecast?.getMinTemp(units), unitsString)

        maxTemp.setText(maxTempString)
        minTemp.text = minTempString
    }

    private fun temperatureUnitsString(units: Forecast.TempUnit) = if (units == Forecast.TempUnit.CELSIUS) "°C" else "F"

    private fun temperatureUnits(): Forecast.TempUnit =
            if(PreferenceManager.getDefaultSharedPreferences(activity)
                    .getBoolean(PREFERENCE_SHOW_CELSIUS,true)){
                Forecast.TempUnit.CELSIUS
            }else {
                Forecast.TempUnit.FAHRENHEIT
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_UNITS) {
            if (resultCode == Activity.RESULT_OK) {
                val unitSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)

                when (unitSelected) {
                    R.id.celsius_rb -> {
                        Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Celsius en el Radio boton")
                        //Similar a una alerta
//                        Toast.makeText(this, "Celsius seleccionado", Toast.LENGTH_LONG).show()
                    }
                    R.id.farenheit_rb -> {
                        Log.v(TAG, "Estamos en el Forecast y han pulsado OK y Farenheit en el Radio boton")
//                        Toast.makeText(this, "Fahrenheit seleccionado", Toast.LENGTH_LONG).show()
                    }

                }

                val oldShowCelsius = temperatureUnits()

                PreferenceManager.getDefaultSharedPreferences(activity)
                        .edit()
                        .putBoolean(PREFERENCE_SHOW_CELSIUS, unitSelected == R.id.celsius_rb)
                        .apply()

                updateTemperature()

                Snackbar.make(rootView,"Han cambiado las preferencias", Snackbar.LENGTH_LONG)
                        .setAction("Deshacer"){
                            PreferenceManager.getDefaultSharedPreferences(activity)
                                    .edit()
                                    .putBoolean(PREFERENCE_SHOW_CELSIUS, oldShowCelsius == Forecast.TempUnit.CELSIUS)
                                    .apply()

                            updateTemperature()
                        }
                        .show()

            } else {
                Log.v(TAG, "Estamos en el Forecast y han pulsado Cancel")
            }
        }

    }

}