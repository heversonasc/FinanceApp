package com.example.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.expensetracker.data.model.ExpenseEntity
import com.example.expensetracker.data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow


@Dao
interface ExpenseDao {

    @Query ("SELECT * FROM expense_table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT date, type, SUM(amount) as total_amount FROM expense_table GROUP BY date, type")
    fun getAllExpenseByDate(): Flow<List<ExpenseSummary>>


    @Insert
    suspend fun insertExpense (expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense (expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense (expenseEntity: ExpenseEntity)

}