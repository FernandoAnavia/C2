package com.example.c2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c2.Model.ItemGroup
import com.example.c2.R

class MyGroupAdapter (private val context:Context,
                      private val dataList:List<ItemGroup>?):
    RecyclerView.Adapter<MyGroupAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var itemTitle:TextView
        var recycler_view_list: RecyclerView
        var btnMore:Button

        init {
            itemTitle = view.findViewById(R.id.itemTitle) as TextView
            btnMore = view.findViewById(R.id.btnMore) as Button
            recycler_view_list = view.findViewById(R.id.rec_view) as RecyclerView



        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_group,p0,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.itemTitle.text = dataList!![position].headerTitle

        val items = dataList[position].listItem
        val itemListAdapter = MyItemAdapter (context, items)

        myViewHolder.recycler_view_list.setHasFixedSize(true)
        myViewHolder.recycler_view_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        myViewHolder.recycler_view_list.adapter = itemListAdapter

        myViewHolder.recycler_view_list.isNestedScrollingEnabled = false
        myViewHolder.btnMore.setOnClickListener{
            Toast.makeText(context,"Btn More: "+dataList[position].headerTitle,Toast.LENGTH_SHORT).show()
        }


    }

    override fun getItemCount(): Int {
        return dataList?.size ?:0
    }


}