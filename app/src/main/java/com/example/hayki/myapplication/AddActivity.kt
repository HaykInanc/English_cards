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

        class CustomClickListener : View.OnClickListener {
            override fun onClick(v: View) {
                when (v.getId()) {
                    R.id.addLoAddWord -> insertHandler()
                    R.id.selectBtn -> back()
                }
            }
        }

        addLoAddWord.setOnClickListener(CustomClickListener())
        selectBtn.setOnClickListener(CustomClickListener())

    }


    fun insertHandler(){
        if (addLoWord.text.toString().length > 0
            && addLoTrans.text.toString().length > 0){
            var newWord = VocLib(addLoWord.text.toString(),
                addLoTrans.text.toString(),
                addLoInterp.text.toString())
            var db = DataBaseHandler(this)
            db.insertData(newWord);
        }else{
            Toast.makeText(this, getString(R.string.addToDbError), Toast.LENGTH_SHORT).show()
        }
    }

    fun back(){
        this.finish()
    }

}
