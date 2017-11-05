package com.example.alancasas.guedr

data class Forecast (val maxTemp: Float, val minTemp:Float, val humitity: Float, val description:String, val icon: Int ){
    enum class TempUnit{
        CELSIUS,
        FAHRENHEIT
    }

    init {
//        if (humitity < 0 || humitity > 100){
//            throw IllegalArgumentException("Humidity should be between 0 - 100")
//        }

        if (humitity !in 0f..100f){
            throw IllegalArgumentException("Humidity should be between 0 - 100")
        }
    }

    protected fun toFahrenheit(celsius: Float) = celsius * 1.8f + 32

    fun getMaxTemp(units: TempUnit) = when(units){
        TempUnit.CELSIUS -> maxTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(maxTemp)
    }

    fun getMinTemp(units: TempUnit) = when(units){
        TempUnit.CELSIUS -> minTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(minTemp)
    }
}















