package com.example.hayki.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLoLearnBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,LearnActivity::class.java)
            startActivity(intent)
        })


        mainLoAddBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        })

        mainLoSearchBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        })
    }

    var context = this;


}
