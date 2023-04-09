package broz.tito.usadebt.data.localstorage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom
import broz.tito.usadebt.domain.currencydomain.currency_v2.local.provideCurrenciesHashMap
import broz.tito.usadebt.presentation.MapToStringResources
import kotlinx.coroutines.*
import javax.inject.Inject

@Database(entities = [CurrencyEntityRoom::class], version = 1)
abstract class CurrencyRoomDataBase: RoomDatabase() {

    abstract fun currencyDao(): CurrencyDAO

    companion object {

        val TAG = "ROOM"

        private var roomdatabase: CurrencyRoomDataBase? = null

        private fun build(context: Context): CurrencyRoomDataBase {
            return Room.databaseBuilder(context,CurrencyRoomDataBase::class.java,"currencies")
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val list = ArrayList<CurrencyEntityRoom>()
                            provideCurrenciesHashMap().onEach {
                                list.add(CurrencyEntityRoom(0,it.key,false))
                            }
                            getInstance(context)?.currencyDao()?.insertAll(list)
                        }
                    }
                })
                .build()
        }

        fun getInstance(context: Context): CurrencyRoomDataBase? {
            return if (roomdatabase == null) {
                build(context)
            } else {
                roomdatabase
            }
        }
    }


}