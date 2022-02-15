package com.example.ppb

import android.app.SearchManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ppb.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rvData: RecyclerView

    private lateinit var adapter: SliderAdapter
    private lateinit var binding: ActivityMainBinding
    private val listBaner = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    private var list: ArrayList<Data> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this,"Menu sudah di klik", Toast.LENGTH_SHORT).show()
        }

        //category
        rvData = findViewById(R.id.rv_category)
        rvData.setHasFixedSize(true)
        list.addAll(DataCategory.listData)
        val cardViewHolder = DataAdapter(list)
        rvData.adapter = cardViewHolder
        rvData.layoutManager = GridLayoutManager(this, 2)

        //slider
        listBaner.add(ImageData(R.drawable.banner))
        listBaner.add(ImageData(R.drawable.banner1))
        listBaner.add(ImageData(R.drawable.banner2))

        adapter = SliderAdapter(listBaner)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })


    }

    private fun selectedDot(position: Int) {
        for (i in 0 until listBaner.size) {
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.purple_200))
        }
    }

    private fun setIndicator() {
        for (i in 0 until listBaner.size) {
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId
        when(itemView){
            R.id.login -> Toast.makeText(applicationContext, "Login sudah di klik",Toast.LENGTH_SHORT).show()
        }
        return false
    }
}