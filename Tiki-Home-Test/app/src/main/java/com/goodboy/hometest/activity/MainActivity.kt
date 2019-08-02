package com.goodboy.hometest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import com.goodboy.hometest.R
import com.goodboy.hometest.adapter.KeyWordAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private var keyWordList: ArrayList<String> = ArrayList()
    private val keywordsJson = "[\"xiaomi\",\"bitis hunter\",\"bts\",\"balo\",\"bitis hunter x\",\"tai nghe\",\"harry potter\",\"anker\",\"iphone\",\"balo nữ\",\"nguyễn nhật ánh\",\"đắc nhân tâm\",\"ipad\",\"senka\",\"tai nghe bluetooth\",\"son\",\"maybelline\",\"laneige\",\"kem chống nắng\",\"anh chính là thanh xuân của em\"]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()
        keyWordList = gson.fromJson<ArrayList<String>>(keywordsJson, object : TypeToken<ArrayList<String>>() {}.type)

        val adapter = KeyWordAdapter(this, keyWordList)
        rv_key_word.adapter = adapter
        rv_key_word.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
    }
}

