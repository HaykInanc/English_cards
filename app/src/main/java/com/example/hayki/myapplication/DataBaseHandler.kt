package com.example.hayki.myapplication



import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


val DATABASE_NAME = "MyDB"
val TABLE_NAME = "VocLib"
val COLUMN_WORD = "word"
val COLUMN_TRANSLATE = "translate"
val COLUMN_INTERPRETATION = "interpretation"
val COLUMN_ID = "id"

class DataBaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable =
            "CREATE TABLE " +
                    TABLE_NAME              + " ( " +
                    COLUMN_ID               + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_WORD             + " VARCHAR(256), " +
                    COLUMN_TRANSLATE        + " VARCHAR(256), " +
                    COLUMN_INTERPRETATION   + " VARCHAR(256)) "
        db?.execSQL(createTable);
    }

    fun insertData(voclib: VocLib){
        val db = this.writableDatabase
        var cv = ContentValues()

        cv.put(COLUMN_WORD, voclib.word)
        cv.put(COLUMN_TRANSLATE, voclib.translate)
        cv.put(COLUMN_INTERPRETATION, voclib.interpretation)

        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Что-то пошло не так, обратись к Гайку.", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Слово добавлено в словарь", Toast.LENGTH_SHORT).show()
    }


    fun selectTable(cond:String = ""):MutableList<MutableList<String>?>{
        val db = this.writableDatabase
        var query = "select * from " + TABLE_NAME + " ${cond}"
        println(query)
        val result = db.rawQuery(query, null)
        var output:MutableList<MutableList<String>?> = mutableListOf();

        if (result.moveToFirst()){
            do{
                var outputInnerList:MutableList<String>
                val id = result.getString(result.getColumnIndex(COLUMN_ID)).toInt()
                val word = result.getString(result.getColumnIndex(COLUMN_WORD))
                val translate = result.getString(result.getColumnIndex(COLUMN_TRANSLATE))
                val interpretation = result.getString(result.getColumnIndex(COLUMN_INTERPRETATION))
                outputInnerList = mutableListOf(word, translate, interpretation)
                output.add(outputInnerList)
            }while (result.moveToNext())
        }
        return output
    }

    fun getCnt():Int{
        val db = this.writableDatabase
        var query = "select count(*) as cnt_ from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        result.moveToFirst()
        val cnt_ = result.getString(result.getColumnIndex("cnt_")).toInt()
        return cnt_

    }


}

