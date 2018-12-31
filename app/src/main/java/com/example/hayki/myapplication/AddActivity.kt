package com.example.hayki.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_lo.*
import android.view.View
import android.widget.Toast

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_lo)

        addLoAddWord.setOnClickListener(View.OnClickListener {
            insertHandler()
        })

        selectBtn.setOnClickListener(View.OnClickListener {
            back()
        })
    }
    var context = this;

    fun insertHandler(){
        if (addLoWord.text.toString().length > 0
            && addLoTrans.text.toString().length > 0){
            var newWord = VocLib(addLoWord.text.toString(),
                addLoTrans.text.toString(),
                addLoInterp.text.toString())
            var db = DataBaseHandler(context)
            db.insertData(newWord);
        }else{
            Toast.makeText(context, "Не удалось добавить в бд, Скажи об этом Гайку.", Toast.LENGTH_SHORT).show()
        }
    }

    fun back(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}
