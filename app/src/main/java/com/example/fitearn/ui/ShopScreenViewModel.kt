package com.example.fitearn.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.database.AppDatabase
import com.example.fitearn.model.ShopItem
import com.example.fitearn.utils.LoggedUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopScreenViewModel(private val appDatabase: AppDatabase): ViewModel() {

    companion object {
        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ShopScreenViewModel::class.java)) {
                        return ShopScreenViewModel(appDatabase) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    var trackCoinAmount = mutableStateOf(0)
    private set
    var trackDollarAmount = mutableStateOf(0.0)
    private set

    init{
        if(LoggedUser.loggedInUser != null){
            trackCoinAmount.value = LoggedUser.loggedInUser!!.coinAmount
            trackDollarAmount.value = LoggedUser.loggedInUser!!.dollarAmount
        }
    }

    fun onPurchase(selectedItem: ShopItem, onPurchaseResult: (String) -> Unit){

        val currentUser = LoggedUser.loggedInUser

        if(currentUser!= null){
            val userOwnAvatars = currentUser.ownAvatars
            val userCoins = currentUser.coinAmount
            val userDollars = currentUser.dollarAmount

            if (userOwnAvatars.any { it.name == selectedItem.name }) {
                onPurchaseResult("You already own this item: ${selectedItem.name}")
                return
            }

            if(selectedItem.itemType == "Avatar"){
                if(userCoins >= selectedItem.cost){
                    currentUser.coinAmount -= selectedItem.cost
                    currentUser.ownAvatars += selectedItem
                    trackCoinAmount.value = currentUser.coinAmount
                    viewModelScope.launch{
                        appDatabase.userDao().updateUser(currentUser)
                    }
                    onPurchaseResult("Avatar Purchase Successful!")
                }else{
                    onPurchaseResult("Insufficent funds to Purchase Avatar...")
                }
            }else if(selectedItem.itemType == "Redeem"){
                if(userDollars >= selectedItem.cost){
                    currentUser.dollarAmount -= selectedItem.cost
                    trackDollarAmount.value = currentUser.dollarAmount
                    viewModelScope.launch{
                        appDatabase.userDao().updateUser(currentUser)
                    }
                    onPurchaseResult("Purchase Successful! Reward is Pending...")
                }else{
                    onPurchaseResult("Insufficent funds to Purchase Reward...")
                }
            }
        }else{
            onPurchaseResult("Purchase Error. Try Again.")
        }
    }
}