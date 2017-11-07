package com.example.alancasas.guedr.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alancasas.guedr.R
import com.example.alancasas.guedr.fragment.CityListFragment
import com.example.alancasas.guedr.model.Cities
import com.example.alancasas.guedr.model.City

class ForecastActivity : AppCompatActivity(), CityListFragment.OnCitySelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        //Comprobar si tenemos un fragmen en nuestra jerarquia

        if(fragmentManager.findFragmentById(R.id.city_list_fragmen) == null) {

            val fragment = CityListFragment.newInstance(Cities())
            fragmentManager.beginTransaction()
                    .add(R.id.city_list_fragmen, fragment)
                    .commit()

        }
    }

    override fun onCitySelected(city: City?, position: Int) {
        startActivity(CityPagerActivity.intent(this, position))
    }


}






































