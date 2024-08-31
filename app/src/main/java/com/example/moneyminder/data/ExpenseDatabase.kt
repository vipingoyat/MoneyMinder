package com.example.moneyminder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moneyminder.data.dao.ExpenseDao
import com.example.moneyminder.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database (entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDatabase:RoomDatabase() {
    abstract fun expenseDao():ExpenseDao



    companion object{
        const val DATABASE_NAME = "expense_table"

        @JvmStatic
        fun getDatabase(context: Context):ExpenseDatabase{
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object :Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }
                fun InitBasicData(context: Context){
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDao()
                        dao.insertExpense(ExpenseEntity(1,"Rent", 1000.0, System.currentTimeMillis(),"Rent","Expense"))
                        dao.insertExpense(ExpenseEntity(2,"Starbucks", 50000.0, System.currentTimeMillis(),"Starbucks","Expense"))
                        dao.insertExpense(ExpenseEntity(3,"Salary", 50000.0, System.currentTimeMillis(),"Salary","Income"))
                    }
                }
            }).build()
        }
    }
}