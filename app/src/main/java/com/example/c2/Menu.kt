package com.example.c2

//16336 Elshy Xiomara Rosado Jimenez
//20344 Jose Fernando Gonzalez Anavia

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.c2.Adapters.MyGroupAdapter
import com.example.c2.Database.CartDataSource
import com.example.c2.Database.CartDatabase
import com.example.c2.Database.LocalCartDataSource
import com.example.c2.EventBus.CountCartEvent
import com.example.c2.Interface.IFirebaseLoadListener
import com.example.c2.Model.ItemData
import com.example.c2.Model.ItemGroup
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.layout_item.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode



class Menu : AppCompatActivity(), IFirebaseLoadListener {

    private lateinit var cartDataSource: CartDataSource

    lateinit var dialog: AlertDialog
    lateinit var IFirebaseLoadListener: IFirebaseLoadListener
    lateinit var myData: DatabaseReference

    override fun onResume () {
        super.onResume()
        countCartItem()
    }

    //invokes the database Json from Firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        dialog = SpotsDialog.Builder().setContext(this).build()
        myData = FirebaseDatabase.getInstance().getReference("MyData")
        IFirebaseLoadListener = this


        val mainbtn = findViewById<ImageButton>(R.id.buttonMain)
        mainbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


        /* THIS WAS DELETED DUE TO AN ERROR WITH THE SHOPPING CART DATABASE
        cartDataSource = LocalCartDataSource(CartDatabase.getInstance(this).cartDAO())


         */

        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.layoutManager = LinearLayoutManager(this)

        getFirebaseData()


    }

    //Get the data from Firebase server

    private fun getFirebaseData() {

        dialog.show()

        myData.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                IFirebaseLoadListener.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {

                val  itemGroups = ArrayList<ItemGroup>()
                for (myDataSnapshot in p0.children)
                {
                    val menuGroup = ItemGroup()
                    menuGroup.headerTitle = myDataSnapshot.child("headerTitle")
                            .getValue(true).toString()
                    val t = object:GenericTypeIndicator<ArrayList<ItemData>>(){}
                    menuGroup.listItem = myDataSnapshot.child("listItem").getValue(t)
                    itemGroups.add(menuGroup)
                }
                IFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups)

            }



        })
    }



    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(this,itemGroupList)
        my_recycler_view.adapter = adapter
        dialog.dismiss()


    }

    override fun onFirebaseLoadFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    

    }

    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    fun onCountCartEvent (event: CountCartEvent)
    {
        if(event.isSuccess)
        {
            countCartItem()
        }
    }

    private fun countCartItem () {

    }

}