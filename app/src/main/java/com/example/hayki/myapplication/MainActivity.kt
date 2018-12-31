package com.example.hayki.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    internal lateinit var learnBtn  : Button
    internal lateinit var searchBtn : Button
    internal lateinit var addBtn    : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        learnBtn  = findViewById<Button>(R.id.learnBtn)
        searchBtn = findViewById<Button>(R.id.searchBtn)
        addBtn    = findViewById<Button>(R.id.addBtn)

        learnBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,LearnActivity::class.java)
            startActivity(intent)
        })


        addBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        })

        searchBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        })
    }

    var context = this;


}
