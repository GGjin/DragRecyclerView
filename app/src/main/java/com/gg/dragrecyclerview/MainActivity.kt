package com.gg.dragrecyclerview

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val data = arrayListOf("淘票票", "充值中心", "信用卡还款", "生活缴费", "余额宝", "饿了么外卖", "我的快递", "红包", "蚂蚁庄园", "运动", "蚂蚁森林", "奖励金")
        val adapter = DemoAdapter(data)
        recyclerView.adapter = adapter

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?): Int {
                val swapFlag = ItemTouchHelper.LEFT

                val dragFlag = if (recyclerView.layoutManager is GridLayoutManager)
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN
                else
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlag, swapFlag)

            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                val fromPosition = viewHolder!!.adapterPosition
                val targetPosition = target!!.adapterPosition
                if (fromPosition < targetPosition) {
                    for (i in fromPosition..targetPosition) {
                        Collections.swap(data, i, i - 1)
                    }
                } else {
                    for (i in targetPosition..fromPosition)
                        Collections.swap(data, i, i + 1)
                }
                adapter.notifyItemMoved(fromPosition, targetPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                data.removeAt(viewHolder!!.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }


            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE)
                    viewHolder?.itemView?.setBackgroundColor(Color.GREEN)
            }


            override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?) {
                super.clearView(recyclerView, viewHolder)
                viewHolder?.itemView?.setBackgroundColor(0)
                viewHolder?.itemView?.translationX = 0f
            }
        })

        helper.attachToRecyclerView(recyclerView)

    }
}
