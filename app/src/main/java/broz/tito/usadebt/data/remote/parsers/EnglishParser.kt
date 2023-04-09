package broz.tito.usadebt.data.remote.parsers

import java.lang.IllegalArgumentException

class EnglishParser: Parser() {

    override val usd_list = arrayListOf("US dollar","US dollars")
    override val thousand_list = arrayListOf("thousand","thousands")
    override val million_list = arrayListOf("million","millions")
    override val billion_list = arrayListOf("billion","billions")
    override val trillion_list = arrayListOf("trillion","trillions")
    override val quadrillion_list = arrayListOf("quadrillion","quadrillions")
    override val quintillion_list = arrayListOf("quintillion","quintillions")

    override fun deleteNullsandAddUnits(string: String,list: List<String>) : String {
        if (list.size != 2) {
            throw IllegalArgumentException("TWO TYPES OF AMOUNT REQUIRED FOR ENGLISH_PARSER !!!")
        }
        var one: String = list.get(0)
        var many: String = list.get(1)
        var number: String
        var result: String
        if (string.length > 0 && string.contains('.')) {
            if (string.length > 0 && string.get(2).equals('1')) {
                number = one
                if (string.length > 1  && string.substring(0,1).equals("0")) {
                    result = string.substring(1,string.length) + " $number"
                }
                else if (string.length > 2 && string.substring(0,2).equals("00")) {
                    result = string.substring(2,string.length) + " $number"
                }
                else {
                    result = string + " $number"
                }
            }
            else {
                number = many
                if (string.length > 1  && string.substring(0,1).equals("0")) {
                    result = string.substring(1,string.length) + " $number"
                }
                else if ( string.length > 2 && string.substring(0,2).equals("00") ) {
                    result = string.substring(1,string.length) + " $number"
                }
                else {
                    result = string + " $number"
                }
            }
        }
        else if (string.length > 0 && string.get(string.length-1).equals('1')) {
            number = one
            if (string.length > 1  && string.substring(0,1).equals("0")) {
                result = string.substring(1,string.length) + " $number"
            }
            else if (string.length > 2 && string.substring(0,2).equals("00")) {
                result = string.substring(2,string.length) + " $number"
            }
            else {
                result = string + " $number"
            }
        }
        else {
            number = many
            if (string.length > 1  && string.substring(0,1).equals("0")) {
                result = string.substring(1,string.length) + " $number"
            }
            else if ( string.length > 2 && string.substring(0,2).equals("00") ) {
                result = string.substring(1,string.length) + " $number"
            }
            else {
                result = string + " $number"
            }
        }
        return result
    }
}


