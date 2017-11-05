package com.example.alancasas.guedr

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastUnitTest {

    lateinit var forecast : Forecast

    @Before
    fun setUP(){
        forecast = Forecast(25f,10f,34f,"Algunas nubes en el horizonte",R.drawable.ico_01)
    }

    @Test
    fun isCorrectMaxTempUnitsConversion(){
        assertEquals(77f, forecast.getMaxTemp(Forecast.TempUnit.FAHRENHEIT))
    }

    @Test
    fun isCorrectMinTempUnitsConversion(){
        assertEquals(50f,forecast.getMinTemp(Forecast.TempUnit.FAHRENHEIT))
    }

    @Test
    fun maxTempUnits_inCelsius(){
        assertEquals(25f, forecast.getMaxTemp(Forecast.TempUnit.CELSIUS))
    }

    @Test
    fun minTempUnits_inCelsius(){
        assertEquals(10f, forecast.getMinTemp(Forecast.TempUnit.CELSIUS))
    }

    @Test(expected = IllegalArgumentException::class)
    fun humidityOverRange_throwsArgumentException(){
        Forecast(25f,
                10f,
                100.01f,
                "Descripcion",
                R.drawable.ico_01)
    }

    @Test(expected = IllegalArgumentException::class)
    fun humidityUnderRange_throwsArgumentException(){
        Forecast(25f,
                10f,
                -1f,
                "Descripcion",
                R.drawable.ico_01)
    }
}