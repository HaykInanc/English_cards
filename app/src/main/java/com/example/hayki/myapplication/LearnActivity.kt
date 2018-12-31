package com.example.hayki.myapplication


import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*


class LearnActivity : AppCompatActivity() {

    internal lateinit var word:         TextView
    internal lateinit var answer:       EditText
    internal lateinit var rightAnswer:  TextView
    internal lateinit var interp:       TextView
    internal lateinit var checkBtn:     Button
    internal lateinit var nextWordBtn:  Button

    internal var DBword: String?      = ""
    internal var DBtranslate: String? = ""
    internal var DBinterp: String?    = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_lo)

        word = findViewById<TextView>(R.id.l_lo_word)
        answer = findViewById<EditText>(R.id.l_lo_answer)
        rightAnswer = findViewById<TextView>(R.id.l_lo_right_answer)
        interp = findViewById<TextView>(R.id.l_lo_interp)
        checkBtn = findViewById<Button>(R.id.l_lo_check)
        nextWordBtn = findViewById<Button>(R.id.l_lo_nextWord)
        viewWord()

        checkBtn.setOnClickListener(View.OnClickListener {
            checkWord()
        })


        nextWordBtn.setOnClickListener(View.OnClickListener {
            viewWord()
        })
    }

    val context = this

    fun viewWord(){
        answer.setTextColor(Color.BLACK)
        var Result:MutableList<MutableList<String>?>
        var db = DataBaseHandler(context);
        var cntRows: Int = db.getCnt();
        var rowCnt = (1..cntRows).shuffled().first()
        Result = db.selectTable("where id = ${rowCnt}")

        if (Result.isEmpty()){
            println("undefined row with id = ${rowCnt}")
        }else{
            val iterate = Result?.listIterator()
            var result:String = "";
            val value = iterate?.next()
            println("Слово: ${value?.get(0)} Перевод: ${value?.get(1)} \n Толкование: ${value?.get(2)} \n\n");
            word.text   = value?.get(1)
            answer.setText("")
            rightAnswer.setText("")
            interp.setText("")
            DBword      = value?.get(0)
            DBtranslate = value?.get(1)
            DBinterp    = value?.get(2)
        }

    }

    fun checkWord(){
        var answerVal = answer.text.toString()
        if (DBword == answerVal){

            println("WIN!")
            answer.setTextColor(Color.GREEN)


        }else{
            println("Louse!")
            answer.setTextColor(Color.RED)
            rightAnswer.text = DBword;
        }
        interp.text = DBinterp


    }

}
