package com.example.moneyminder.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moneyminder.data.ExpenseDatabase
import com.example.moneyminder.data.dao.ExpenseDao
import com.example.moneyminder.data.model.ExpenseEntity

class HomeViewModel(dao: ExpenseDao):ViewModel() {

    val expenses = dao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>):String{
        var total = 0.0
        list.forEach {
            if(it.type=="Income"){
                total += it.amount
            }
            else{
                total-=it.amount
            }
        }
        return "$ ${total}"
    }

    fun getTotalExpense(list: List<ExpenseEntity>):String{
        var total = 0.0
        list.forEach {
            if(it.type=="Expense"){
                total += it.amount
            }
        }
        return "$ ${total}"
    }
    fun getTotalIncome(list: List<ExpenseEntity>):String{
        var total = 0.0
        list.forEach {
            if(it.type=="Income"){
                total += it.amount
            }
            else{
                total-=it.amount
            }
        }
        return "$ ${total}"
    }
}


class HomeViewModelFactory(private val context: Context):ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}