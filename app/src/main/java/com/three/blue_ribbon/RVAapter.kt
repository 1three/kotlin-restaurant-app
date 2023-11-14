package com.three.blue_ribbon

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAapter(val context: Context, val List: MutableList<ContentModel>) :
    RecyclerView.Adapter<RVAapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)

        return ViewHolder(v)
    }

    // RV 개별 아이템 클릭 이벤트 생성 (1)
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: RVAapter.ViewHolder, position: Int) {
        // RV 개별 아이템 클릭 이벤트 생성 (2)
        if (itemClick != null) {
            holder?.itemView?.setOnClickListener { v ->
                itemClick!!.onClick(v, position)
            }
        }

        holder.bindItems(List[position])
    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: ContentModel) {
            val rv_img = itemView.findViewById<ImageView>(R.id.rvImgArea)
            val rv_text = itemView.findViewById<TextView>(R.id.rvTextArea)

            rv_text.text = item.title

            // Glide를 통해 url 이미지 불러오기
            // load() : 얘를 불러와서
            // into() : 여기에 넣어라
            Glide.with(context).load(item.imgUrl).into(rv_img)
        }
    }


}