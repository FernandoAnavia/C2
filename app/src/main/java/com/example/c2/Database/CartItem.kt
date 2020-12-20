package com.example.c2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull

@Entity(tableName = "Cart")
class CartItem {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "foodId")
    var foodId:String = ""

    @ColumnInfo(name = "foodName")
    var foodName:String?=null

    @ColumnInfo(name = "foodPrice")
    var foodPrice:Double=0.0

    @ColumnInfo(name = "foodQuantity")
    var foodQuantity:Int=0

    @ColumnInfo(name = "foodExtraPrice")
    var foodExtraPrice:Double=0.0

    @ColumnInfo(name = "userPhone")
    var userPhone:String?=null

    @ColumnInfo(name = "uid")
    var uid:String?=null

}