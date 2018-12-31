package com.example.hayki.myapplication


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.learn_lo.*
import android.view.View


class LearnActivity : AppCompatActivity() {

    internal var dBword: String?      = ""
    internal var dBtranslate: String? = ""
    internal var dBinterp: String?    = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_lo)

        viewWord()

        lLoCheck.setOnClickListener(View.OnClickListener {
            checkWord()
        })


        lLoNextWord.setOnClickListener(View.OnClickListener {
            viewWord()
        })
    }

    val context = this

    fun viewWord(){
        lLoAnswer.setTextColor(Color.BLACK)
        var result:MutableList<MutableList<String>?>
        var db = DataBaseHandler(context);
        var cntRows: Int = db.getCnt();
        var rowCnt = (1..cntRows).shuffled().first()
        result = db.selectTable("where id = ${rowCnt}")

        if (result.isEmpty()){
            println("undefined row with id = ${rowCnt}")
        }else{
            val iterate = result?.listIterator()
            var result:String = "";
            val value = iterate?.next()
            println("Слово: ${value?.get(0)} Перевод: ${value?.get(1)} \n Толкование: ${value?.get(2)} \n\n");
            lLoWord.text   = value?.get(1)
            lLoAnswer.setText("")
            lLoRightAnswer.setText("")
            lLoInterp.setText("")
            dBword      = value?.get(0)
            dBtranslate = value?.get(1)
            dBinterp    = value?.get(2)
        }

    }

    fun checkWord(){
        var answerVal = lLoAnswer.text.toString()
        if (dBword == answerVal){

            println("WIN!")
            lLoAnswer.setTextColor(Color.GREEN)


        }else{
            println("Louse!")
            lLoAnswer.setTextColor(Color.RED)
            lLoRightAnswer.text = dBword;
        }
        lLoInterp.text = dBinterp


    }

}
