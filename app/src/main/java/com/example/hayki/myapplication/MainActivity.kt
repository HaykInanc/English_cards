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

        class CustomClickListener : View.OnClickListener {
            override fun onClick(v: View) {
                when (v.getId()) {
                    R.id.mainLoLearnBtn -> goToLearn()
                    R.id.mainLoAddBtn -> goToAdd()
                    R.id.mainLoSearchBtn -> goToSearch()

                }
            }
        }
        mainLoLearnBtn.setOnClickListener(CustomClickListener())
        mainLoAddBtn.setOnClickListener(CustomClickListener())
        mainLoSearchBtn.setOnClickListener(CustomClickListener())

    }

    fun goToLearn(){
        val intent = Intent(this,LearnActivity::class.java)
        startActivity(intent)
    }


    fun goToAdd(){
        val intent = Intent(this,AddActivity::class.java)
        startActivity(intent)
    }

    fun goToSearch(){
        val intent = Intent(this,SearchActivity::class.java)
        startActivity(intent)
    }

}
