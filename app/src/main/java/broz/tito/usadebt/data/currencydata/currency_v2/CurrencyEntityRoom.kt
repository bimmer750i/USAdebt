package broz.tito.usadebt.data.currencydata.currency_v2

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntityRoom(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "currencyCode") var currencyCode: String,
    @ColumnInfo(name = "isSelected") var isSelected: Boolean
)

