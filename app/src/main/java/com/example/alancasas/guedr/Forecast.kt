package com.example.alancasas.guedr

//Declaracion de la clase con el inicializador class myClass (var _name: String){
// fun getName (): String{
//    return _name
// }
// fun setNewName(newName:String){
//    _name = newName
// }
// }
//class Forecast (var _maxTemp: Float, var _minTemp:Float, var _humitity: Float, var _description:String, var _icon: Int ){
//
//    fun getMaxTemp () : Float{
//        return _maxTemp
//    }
//
//    fun setMaxTemp(newMaxTempValue: Float) : Unit{
//        _maxTemp = newMaxTempValue
//    }
//
//}

//data class Forecast (val _maxTemp: Float, val _minTemp:Float, val _humitity: Float, val _description:String, val _icon: Int ){
//  fun printMaxTemp(){
//      Log.v(TAG, "${_maxTemp}"
//  }
//}


data class Forecast (val maxTemp: Float, val minTemp:Float, val humitity: Float, val description:String, val icon: Int ){
    enum class TempUnit{
        CELSIUS,
        FAHRENHEIT
    }
}















