package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fitearn.model.ShopItem
import com.example.fitearn.utils.LoggedUser

class AvatarCollectionViewModel: ViewModel(){

    var selectedAvatar = mutableStateOf("")
    private set

    init{
        if(LoggedUser.loggedInUser != null){
            selectedAvatar.value = LoggedUser.loggedInUser!!.equippedAvatar.toString()
        }
    }

    fun onEquip(item: ShopItem){
        if(LoggedUser.loggedInUser != null){
            LoggedUser.loggedInUser!!.equippedAvatar = item.name
            selectedAvatar.value = item.name
        }
    }
}