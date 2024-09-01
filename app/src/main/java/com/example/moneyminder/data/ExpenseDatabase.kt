package com.example.moneyminder.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.moneyminder.data.dao.ExpenseDao
import com.example.moneyminder.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database (entities = [ExpenseEntity::class], version = 2)
abstract class ExpenseDatabase:RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao


    companion object {
        const val DATABASE_NAME = "expense_table"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDatabase {
            return Room.databaseBuilder(
                context,
                ExpenseDatabase::class.java,
                DATABASE_NAME
            ).addMigrations(MIGRATION_1_2)
                .fallbackToDestructiveMigrationFrom(3)
                .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }

                fun InitBasicData(context: Context) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).expenseDao()
                        dao.insertExpense(
                            ExpenseEntity(
                                1, "Rent", 1000.0,
                                System.currentTimeMillis().toString(), "Rent", "Expense"
                            )
                        )
                        dao.insertExpense(
                            ExpenseEntity(
                                2, "Starbucks", 50000.0,
                                System.currentTimeMillis().toString(), "Starbucks", "Expense"
                            )
                        )
                        dao.insertExpense(
                            ExpenseEntity(
                                3, "Salary", 50000.0,
                                System.currentTimeMillis().toString(), "Salary", "Income"
                            )
                        )
                    }
                }
            }).addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Alter the table to add any new columns if necessary
        // Ensure that the `id` column remains nullable if that is the intended design

        // Rename the old table
        database.execSQL("ALTER TABLE expense_table RENAME TO temp_expense_table")

        // Create a new table with the correct schema
        database.execSQL("""
            CREATE TABLE expense_table (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                amount REAL NOT NULL,
                date TEXT NOT NULL,    -- Ensure this matches the expected type
                category TEXT NOT NULL,
                type TEXT NOT NULL
            )
        """)

        // Copy the data from the old table to the new table
        database.execSQL("""
            INSERT INTO expense_table (id, title, amount, date, category, type)
            SELECT id, title, amount, date, category, type FROM temp_expense_table
        """)

        // Drop the old table
        database.execSQL("DROP TABLE temp_expense_table")
    }
}
