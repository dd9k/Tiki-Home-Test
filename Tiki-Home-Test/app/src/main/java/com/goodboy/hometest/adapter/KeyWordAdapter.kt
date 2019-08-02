package com.goodboy.hometest.adapter

import android.app.Activity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.goodboy.hometest.Const
import com.goodboy.hometest.R
import kotlinx.android.synthetic.main.item_key_word.view.*
import kotlin.math.max


class KeyWordAdapter(
    private var activity: Activity,
    private var keyWordList: ArrayList<String>
) : RecyclerView.Adapter<KeyWordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val keyWordView = inflater.inflate(R.layout.item_key_word, parent, false)

        return ViewHolder(keyWordView)
    }

    override fun getItemCount() = keyWordList.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.tv_key_word.text = makeText(keyWordList[position])
        val backgrounds = activity.resources.obtainTypedArray(R.array.key_word_backgrounds)
        viewHolder.itemView.btn_key_word.background =
            ContextCompat.getDrawable(activity, backgrounds.getResourceId(position % Const.numberColorHotKey, 0))
        backgrounds.recycle()
    }

    /**
     * @author Diệp Đăng Khoa
     * @param keyword keyword in button
     * @return keyword have have minimum difference in length
     * @sample keyword = nguyễn nhật ánh => nguyễn\nnhật ánh
     */
    private fun makeText(keyword: String): String {
        var textButton = ""
        if (keyword.isNotBlank()) {
            val listText = keyword.split(" ")
            when {
                listText.size == 1 -> textButton = keyword
                listText.size == 2 -> textButton = listText[0] + "\n" + listText[1]
                else -> {
                    // init minimum difference in length by max between length of the first word in list and sum of length of the others one
                    var minLength = max(listText[0].length, keyword.replace(" ", "").length - listText[0].length)
                    var nextLine = 0 // index next line
                    for (i in 1 until listText.size - 1) {
                        var firstLength = 0 // length of first line
                        var secondLength = 0 // length of second line
                        for (j in 0 until listText.size) {
                            if (j <= i)
                                firstLength += listText[j].length
                            else secondLength += listText[j].length
                        }
                        if (max(firstLength, secondLength) < minLength) {
                            minLength = max(firstLength, secondLength)
                            nextLine = i
                        }
                    }
                    for (i in 0 until listText.size) {
                        textButton += when (i) {
                            nextLine -> listText[i] + "\n"
                            listText.lastIndex -> listText[i]
                            else -> listText[i] + " "
                        }
                    }
                }
            }
        }
        return textButton
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}