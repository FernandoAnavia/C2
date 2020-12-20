package com.example.c2.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.c2.Database.CartDataSource
import com.example.c2.Database.CartDatabase
import com.example.c2.Database.CartItem
import com.example.c2.Database.LocalCartDataSource
import com.example.c2.EventBus.CountCartEvent
import com.example.c2.Interface.IItemClickListener
import com.example.c2.Model.ItemData
import com.example.c2.R
import com.google.android.gms.common.internal.service.Common
import com.squareup.picasso.Picasso
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.layout_item.*
import org.greenrobot.eventbus.EventBus
import org.w3c.dom.Text

class MyItemAdapter (private val context: Context,
                     private val itemList:List<ItemData>?):RecyclerView.Adapter<MyItemAdapter.MyViewHolder>(){

    /*
    private val compositeDisposable : CompositeDisposable
    private val cartDataSource : CartDataSource

    init {
        compositeDisposable = CompositeDisposable()
        cartDataSource = LocalCartDataSource(CartDatabase.getInstance(context).cartDAO())
    }

     */



    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener {

        var txt_name: TextView
        var img_item:ImageView
        //var txt_desc: TextView
        var txt_price: TextView
        var btn_dec: Button
        var btn_inc: Button
        var btn_cart: Button
        var txt_qty: TextView
        var singlePrice: TextView


        lateinit var iItemClickListener: IItemClickListener

        fun setClick(iItemClickListener: IItemClickListener)
        {
            this.iItemClickListener = iItemClickListener


        }


        init {
            txt_name = view.findViewById(R.id.nameItem) as TextView
            img_item = view.findViewById(R.id.imageItem) as ImageView
            //txt_desc = view.findViewById(R.id.descrptionItem) as TextView
            txt_price = view.findViewById(R.id.priceItem) as TextView
            btn_dec = view.findViewById(R.id.buttondDec) as Button
            btn_inc = view.findViewById(R.id.buttonInc) as Button
            btn_cart = view.findViewById(R.id.buttonCart) as Button
            txt_qty = view.findViewById(R.id.ValueText) as TextView
            singlePrice = view.findViewById(R.id.realPrice) as TextView


            view.setOnClickListener(this)
        }

        var count = 0
        //var priceStrg = singlePrice.getText().toString()
        var itemPrice = 1 //singlePrice.toString().toFloat()
        var subtotal = 0f


        fun increment (){
            count = count+1
            txt_qty.setText(count.toString())
        }

        fun decrement () {
            if (count <= 0) {
                count = 0
            } else {
                count = count-1
                txt_qty.setText(count.toString())

            }
        }

        fun total (){
            subtotal = (count * itemPrice).toFloat()
            Toast.makeText(context,"Added to your cart, subtotal " + subtotal ,Toast.LENGTH_LONG).show()

        }

        override fun onClick(view: View?) {

            iItemClickListener.onItemClickListener(view!!,adapterPosition)

            btn_inc.setOnClickListener{increment()}
            btn_dec.setOnClickListener{decrement()}
            btn_cart.setOnClickListener { total() }





        }



    }



    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_item,p0,false)

        return MyViewHolder(view)


    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        myViewHolder.txt_name.setText(itemList!![position].name!!)
        //myViewHolder.txt_desc.setText(itemList[position].description!!)
        myViewHolder.txt_price.setText(itemList[position].price!!)
        Picasso.get().load(itemList[position].image).into(myViewHolder.img_item)
        myViewHolder.singlePrice.setText(itemList[position].priceInt.toString())

        myViewHolder.btn_cart.setOnClickListener {
            val cartItem = CartItem()
            cartItem.foodName = itemList.get(position).name!!
            cartItem.foodPrice = itemList.get(position).priceInt!!.toDouble()
            cartItem.foodQuantity = 1
            cartItem.foodExtraPrice = 0.0

        }

            /*
            compositeDisposable.add(cartDataSource.insertOrReplaceAll(cartItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(context, "[Add to cart success}", Toast.LENGTH_SHORT).show()
                    EventBus.getDefault().postSticky(CountCartEvent(true))
                },{
                    t:Throwable -> Toast.makeText(context,"Insert Cart"+t!!.message,Toast.LENGTH_SHORT).show()
                }))



        }

        fun onStop(){
            if(compositeDisposable != null)
            compositeDisposable.clear()
        }

             */

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