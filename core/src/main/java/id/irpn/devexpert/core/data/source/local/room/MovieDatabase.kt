package id.irpn.devexpert.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.irpn.devexpert.core.data.source.local.entitiy.MovieEntity

/**
 * Created by irpanpadillah on 22/11/21
 * Email: padillahirpan8@gmail.com
 */

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "MovieIrpn.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
    }

}