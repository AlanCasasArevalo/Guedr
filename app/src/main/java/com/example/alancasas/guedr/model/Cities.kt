package com.example.alancasas.guedr.model

import com.example.alancasas.guedr.R
import java.io.Serializable

class Cities: Serializable {
//     Si quiero el array vacio es mutablelistOf()
    private var cities : List<City> = listOf(
            City("Madrid", Forecast(25f,10f,35f,"Soleado con nubes", R.drawable.ico_01)),
            City("Jaen", Forecast(25f,10f,35f,"Soleado con nubes", R.drawable.ico_02)),
            City("Quito", Forecast(25f,10f,35f,"Soleado con nubes", R.drawable.ico_03))
    )

//    Se puede hacer de las 2 maneras
//    val count = cities.count()
    val count get() = cities.size


//    fun getCity (index: Int) = cities[index]
    operator fun get(index:Int) = cities[index]

    fun toArray() = cities.toTypedArray()

}