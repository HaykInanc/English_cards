package com.example.hayki.myapplication


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.search_lo.*
import android.widget.Toast
import kotlinx.android.synthetic.main.add_lo.*

class SearchActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_lo)

        class CustomClickListener : View.OnClickListener {
            override fun onClick(v: View) {
                when (v.getId()) {
                    R.id.sLoSearchBtn -> searchBtnHandler()
                    R.id.sLoBack -> back()
                }
            }
        }

        sLoSearchBtn.setOnClickListener(CustomClickListener())
        sLoBack.setOnClickListener(CustomClickListener())
    }

    fun searchBtnHandler(){
        if (sLoWord.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.getSearchParams), Toast.LENGTH_SHORT).show()
        }else{
            var query:String = "where word like (\'%${sLoWord.text.toString()}%\') or translate like \'%${sLoWord.text.toString()}%\'"
            var result:String = search(query);
            showResult(result);
        }
    }

    fun search(query:String):String{
        var badResult:String = getString(R.string.emptySearchResult)
        var goodResult:MutableList<MutableList<String>?>
        var db = DataBaseHandler(this);
        goodResult = db.getData(query)

        if (goodResult.isEmpty()){
            return badResult
        }else{
            val iterate = goodResult?.listIterator()
            var result:String = "";
            do {
                val value = iterate?.next()
                result += (getString(R.string.word)
                + ": ${value?.get(0)} "
                + getString(R.string.translate)
                + ": ${value?.get(1)} \n "
                + getString(R.string.interpretation)
                + ": ${value?.get(2)} \n\n");
            }while (iterate.hasNext())
            return result
        }

    }

    fun showResult(data:String){
        sLoResult.text = data;
    }

    fun back(){
        this.finish()
    }

}