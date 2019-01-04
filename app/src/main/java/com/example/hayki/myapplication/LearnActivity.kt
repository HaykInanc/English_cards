package com.example.hayki.myapplication


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.learn_lo.*
import android.view.View
import kotlinx.android.synthetic.main.learn_lo.*


class LearnActivity : AppCompatActivity() {

    internal var dBword: String?      = ""
    internal var dBtranslate: String? = ""
    internal var dBinterp: String?    = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_lo)

        viewWord()

        class CustomClickListener : View.OnClickListener {
            override fun onClick(v: View) {
                when (v.getId()) {
                    R.id.lLoCheck -> checkWord()
                    R.id.lLoNextWord -> viewWord()
                }
            }
        }
        lLoCheck.setOnClickListener(CustomClickListener())
        lLoNextWord.setOnClickListener(CustomClickListener())

    }

    fun viewWord(){
        lLoAnswer.setTextColor(Color.BLACK)
        var result:MutableList<MutableList<String>?>
        var db = DataBaseHandler(this);
        var cntRows: Int = db.libSize();
        var rowCnt = (1..cntRows).shuffled().first()
        result = db.getData("where id = ${rowCnt}")

        if (result.isEmpty()){
            println(getString(R.string.undefinedRow) + "${rowCnt}")
        }else{
            val iterate = result?.listIterator()
            var result:String = "";
            val value = iterate?.next()
            println(getString(R.string.word)
                    + ": ${value?.get(0)} "
                    + getString(R.string.translate)
                    + ": ${value?.get(1)} \n "
                    + getString(R.string.interpretation)
                    + ": ${value?.get(2)} \n\n");

            lLoWord.text = value?.get(1)
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
            lLoAnswer.setTextColor(Color.GREEN)
        }else{
            lLoAnswer.setTextColor(Color.RED)
            lLoRightAnswer.text = dBword;
        }
        lLoInterp.text = dBinterp
    }

}
