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
     * @sample keyword = "nguyễn nhật ánh" => "nguyễn\nnhật ánh"
     */
    private fun makeText(keyword: String): String {
        // remove leading, trailing and duplicate whitespace
        val convertKeyword = keyword.trim().replace("( ) +".toRegex(), " ")
        var min = convertKeyword.length
        var endIndex = 0 // end line index
        convertKeyword.forEachIndexed { index, char ->
            if (char == ' ' && max(index, convertKeyword.length - index) < min) {
                min = max(index, convertKeyword.length - index)
                endIndex = index
            }
        }
        return if (endIndex == 0) convertKeyword
        else convertKeyword.substring(0, endIndex) + '\n' + convertKeyword.substring(endIndex + 1)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}