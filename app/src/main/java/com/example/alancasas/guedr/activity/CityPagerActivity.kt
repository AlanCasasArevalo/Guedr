package com.example.alancasas.guedr.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.alancasas.guedr.R
import com.example.alancasas.guedr.fragment.ForecastFragment
import com.example.alancasas.guedr.model.Cities
import com.example.alancasas.guedr.model.Forecast
import kotlinx.android.synthetic.main.fragment_forecast.*

class CityPagerActivity : AppCompatActivity() {

    val pager by lazy { findViewById<ViewPager>(R.id.view_pager) }
    val cities = Cities()

    companion object {

        val EXTRA_CITY_INDEX = "EXTRA_CITY_INDEX"

        fun intent(context: Context, cityIndex: Int) : Intent{
            val intent = Intent(context, CityPagerActivity::class.java)
            intent.putExtra(EXTRA_CITY_INDEX, cityIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_pager)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)

        setSupportActionBar(toolbar)

        val adapter = object : FragmentPagerAdapter(fragmentManager){

            override fun getItem(position: Int) = ForecastFragment.newInstance(cities[position])

            override fun getCount() = cities.count

        }

        pager.adapter = adapter

        pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                updateCityInfo(position)
            }


        })

        val initialCityIndex = intent.getIntExtra(EXTRA_CITY_INDEX, 0)
        pager.currentItem = initialCityIndex

        updateCityInfo(initialCityIndex)

    }

    fun updateCityInfo(position: Int) {
        supportActionBar?.title = cities[position].name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.pager, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.previous -> {
                pager.currentItem = pager.currentItem - 1
                true
            }
            R.id.next -> {
                pager.currentItem++
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        invalidateOptionsMenu()

        val menuPrev = menu?.findItem(R.id.previous)
        menuPrev?.setEnabled(pager.currentItem > 0)

        val menuNext = menu?.findItem(R.id.next)
        menuNext?.setEnabled(pager.currentItem < cities.count-1)

        return true
    }

}




















