package com.tanvir.training.actioninputsbatch03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1)
abstract class BusScheduleDB : RoomDatabase(){
    abstract fun getScheduleDao() : ScheduleDao

    companion object {
        private var db: BusScheduleDB? = null

        fun getDB(context: Context) : BusScheduleDB {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext, BusScheduleDB::class.java, "schedule_db")
                    //.allowMainThreadQueries()
                    .build()
                return db!!
            }
            return db!!
        }
    }
}