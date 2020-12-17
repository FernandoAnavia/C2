package com.example.c2.Interface

import com.example.c2.Model.ItemGroup

interface IFirebaseLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>)
    fun onFirebaseLoadFailed(message: String)
}