package broz.tito.usadebt.data.remote.parsers

import broz.tito.usadebt.data.remote.Model
import broz.tito.usadebt.model.*
import java.text.SimpleDateFormat



fun convertToRDate(date: String): String {
    val original_format = SimpleDateFormat("yyyy-MM-dd")
    val format = SimpleDateFormat("dd-MM-yyyy")
    val date_formatted = original_format.parse(date)
    return format.format(date_formatted)
}


fun parseFromRawHistory(history: RawHistory,model: Model): ArrayList<String> {
    val result = ArrayList<String>()
    val data = history.data
    data.forEach {
        var debt: String
        var date: String
        if (model.parser is RParser) {
            date = convertToRDate(it.recordDate)
        }
        else {
            date = it.recordDate
        }
        debt = model.parser.parse("",date,it.totPubDebtOutAmt)
        result.add(debt)
    }
    return result
}