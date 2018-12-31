package com.example.hayki.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {

    internal lateinit var ActionBtn: Button
    internal lateinit var Back: Button
    internal lateinit var wordIns: EditText
    internal lateinit var transIns: EditText
    internal lateinit var interIns: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_lo)
        ActionBtn = findViewById<Button>(R.id.add_lo_AddWord)
        wordIns   = findViewById<EditText>(R.id.add_lo_word)
        transIns   = findViewById<EditText>(R.id.add_lo_trans)
        interIns   = findViewById<EditText>(R.id.add_lo_interp)
        Back = findViewById<Button>(R.id.selectBtn)

        ActionBtn.setOnClickListener(View.OnClickListener {
            insertHandler()
        })

        Back .setOnClickListener(View.OnClickListener {
            back()
        })
    }
    var context = this;

    fun insertHandler(){
        if (wordIns.text.toString().length > 0
            && transIns.text.toString().length > 0){
            var newWord = VocLib(wordIns.text.toString(),
                transIns.text.toString(),
                interIns.text.toString())
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
