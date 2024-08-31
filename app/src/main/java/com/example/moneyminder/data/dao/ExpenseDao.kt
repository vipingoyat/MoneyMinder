package com.example.moneyminder.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moneyminder.data.model.ExpenseEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ExpenseDao {


    @Query ("Select * from expense_table")
    fun getAllExpenses():Flow<List<ExpenseEntity>>


    @Insert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)
}