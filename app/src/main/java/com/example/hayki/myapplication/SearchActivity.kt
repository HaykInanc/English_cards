package com.example.hayki.myapplication


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SearchActivity: AppCompatActivity() {

    internal lateinit var SearchBtn:Button;
    internal lateinit var BackBtn:Button;
    internal lateinit var EnglishWord:EditText;
    internal lateinit var RussianWord:EditText;
    internal lateinit var ResultText:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vocabulary_lo)

        SearchBtn   = findViewById<Button>(R.id.v_lo_SearchBtn)
        BackBtn     = findViewById<Button>(R.id.v_lo_back)
        EnglishWord = findViewById<EditText>(R.id.v_lo_english_word)
        RussianWord = findViewById<EditText>(R.id.v_lo_russian_word)
        ResultText  = findViewById<TextView>(R.id.v_lo_Result)

        BackBtn.setOnClickListener(View.OnClickListener {
            back()
        })
        SearchBtn.setOnClickListener(View.OnClickListener {

            if (RussianWord.text.isNullOrEmpty() && EnglishWord.text.isNullOrEmpty()){
                Toast.makeText(context, "Задайте параметры поиска", Toast.LENGTH_SHORT).show()
                print(RussianWord.text.toString());
                print(EnglishWord.text.toString());
            }else{
                var query:String = ""
                var result:String
                if(RussianWord.text.isNullOrEmpty()){
                    query =  "where word = \'${EnglishWord.text.toString()}'"
                }else if(EnglishWord.text.isNullOrEmpty()){
                    query =  "where translate = \'${RussianWord.text.toString()}\'"
                }
                result = search(query);
                showResult(result);
            }
        })
    }
    var context = this;

    fun search(query:String):String{
        var badResult:String = "Такого слова в Вашем словаре нет =("
        var goodResult:MutableList<MutableList<String>?>
        var db = DataBaseHandler(context);
        goodResult = db.selectTable(query)

        if (goodResult.isEmpty()){
            return badResult
        }else{
            val iterate = goodResult?.listIterator()
            var result:String = "";
            do {
                val value = iterate?.next()
                result += "Слово: ${value?.get(0)} Перевод: ${value?.get(1)} \n Толкование: ${value?.get(2)} \n\n";
            }while (iterate.hasNext())
            return result
        }

    }

    fun showResult(data:String){
        ResultText.text = data;
    }

    fun back(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}