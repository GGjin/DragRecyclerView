package com.gg.dragrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Creator : GG
 * Time    : 2018/7/14
 * Mail    : gg.jin.yu@gmail.com
 * Explain :
 */
class DemoAdapter(data: List<String> = listOf("淘票票", "充值中心", "信用卡还款", "生活缴费", "余额宝", "饿了么外卖", "我的快递", "红包", "蚂蚁庄园", "运动", "蚂蚁森林", "奖励金"))
    : RecyclerView.Adapter<DemoAdapter.DemoViewHolder>() {
    val mData = data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        return DemoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.title.text = mData[position]
    }


    class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
    }

}