package broz.tito.usadebt.data.localstorage

import androidx.room.*
import broz.tito.usadebt.data.currencydata.currency_v2.CurrencyEntityRoom

@Dao
interface CurrencyDAO {

    @Query ("SELECT * FROM currencies WHERE isSelected LIKE :isSelected")
    suspend fun getCurrencies(isSelected: Boolean): List<CurrencyEntityRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyEntityRoom>)

    @Update
    suspend fun updateAll(currencies: List<CurrencyEntityRoom>)



}