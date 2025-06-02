package com.example.expensetracker.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.Utils
import com.example.expensetracker.data.ExpenseDataBase
import com.example.expensetracker.data.dao.ExpenseDao
import com.example.expensetracker.data.model.ExpenseEntity
import com.hever.expensetracker.R
import kotlin.math.exp

class HomeViewModel(dao: ExpenseDao): ViewModel() {
    val expenses = dao.getAllExpense()

    fun getBalance(list : List<ExpenseEntity>): String {
        var balance = 0.0
        list.forEach{
            if (it.type == "Income") {
                balance += it.amount
            } else {
                balance -= it.amount
            }
        }
        return "R$ ${Utils.formatToDecimalValue(balance)}"
    }

    fun getTotalExpense(list : List<ExpenseEntity>): String{

        var total = 0.0
        list.forEach{
            if (it.type == "Expense") {
                total += it.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(total)}"
    }

    fun getTotalIncome(list : List<ExpenseEntity>): String{
        var totalIncome = 0.0
        for(expense in list) {
            if (expense.type == "Income") {
                totalIncome += expense.amount
            }
        }
        return "$ ${Utils.formatToDecimalValue(totalIncome)}"
    }

    fun getItemIcon(item:ExpenseEntity): Int{

        if(item.category == "Paypal"){
            return R.drawable.ic_paypal
        } else if (item.category == "Netflix"){
            return R.drawable.ic_netflix
        } else if (item.category == "Starbucks"){
            return R.drawable.ic_starbucks
        }
        return R.drawable.ic_pagment
    }
}

class HomeViewModelFactory (private val context:Context): ViewModelProvider.Factory {
    override fun <T:ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
