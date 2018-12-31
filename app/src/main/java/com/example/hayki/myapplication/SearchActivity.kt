package com.example.hayki.myapplication


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.search_lo.*
import android.widget.Toast

class SearchActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_lo)

        sLoBack.setOnClickListener(View.OnClickListener {
            back()
        })
        sLoSearchBtn.setOnClickListener(View.OnClickListener {

            if (sLoRussianWord.text.isNullOrEmpty() && sLoEnglishWord.text.isNullOrEmpty()){
                Toast.makeText(context, "Задайте параметры поиска", Toast.LENGTH_SHORT).show()
                print(sLoRussianWord.text.toString());
                print(sLoEnglishWord.text.toString());
            }else{
                var query:String = ""
                var result:String
                if(sLoRussianWord.text.isNullOrEmpty()){
                    query =  "where word = \'${sLoEnglishWord.text.toString()}'"
                }else if(sLoEnglishWord.text.isNullOrEmpty()){
                    query =  "where translate = \'${sLoRussianWord.text.toString()}\'"
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
        sLoResult.text = data;
    }

    fun back(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}