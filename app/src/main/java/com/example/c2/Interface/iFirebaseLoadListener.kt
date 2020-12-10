package com.example.c2.Interface

import com.example.c2.Model.menuGroup

interface iFirebaseLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList: List<menuGroup>)
    fun onFirebaseLoadFailed()
}