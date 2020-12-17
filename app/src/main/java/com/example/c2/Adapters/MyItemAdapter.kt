package com.example.c2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.c2.Interface.IItemClickListener
import com.example.c2.Model.ItemData
import com.example.c2.R
import com.squareup.picasso.Picasso

class MyItemAdapter (private val context: Context,
                     private val itemList:List<ItemData>?):RecyclerView.Adapter<MyItemAdapter.MyViewHolder>(){
    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_name: TextView
        var img_item:ImageView
        var txt_desc: TextView
        var txt_price: TextView

        lateinit var iItemClickListener: IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener)
        {
            this.iItemClickListener = iItemClickListener
        }


        init {
            txt_name = view.findViewById(R.id.nameItem) as TextView
            img_item = view.findViewById(R.id.imageItem) as ImageView
            txt_desc = view.findViewById(R.id.descrptionItem) as TextView
            txt_price = view.findViewById(R.id.priceItem) as TextView

            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            iItemClickListener.onItemClickListener(view!!,adapterPosition)

        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item,p0,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.txt_name.setText(itemList!![position].name!!)
        myViewHolder.txt_desc.setText(itemList[position].description!!)
        myViewHolder.txt_price.setText(itemList[position].price!!)
        Picasso.get().load(itemList[position].image).into(myViewHolder.img_item)

        myViewHolder.setClick(object: IItemClickListener{

            override fun onItemClickListener(view: View, position: Int) {
                Toast.makeText(context,""+itemList[position].name,Toast.LENGTH_SHORT).show()
            }


        })
    }

    override fun getItemCount(): Int {
        return itemList?.size?:0

    }
}