package broz.tito.usadebt.data.remote.parsers

import java.lang.IllegalArgumentException

class RParser: Parser() {

    override val usd_list = arrayListOf("долларов США","долларов США","долларов США")
    override val thousand_list = arrayListOf("тысяча","тысячи","тысяч")
    override val million_list = arrayListOf("миллион","миллиона","миллионов")
    override val billion_list = arrayListOf("миллиард","миллиарда","миллардов")
    override val trillion_list = arrayListOf("триллион","триллиона","триллионов")
    override val quadrillion_list = arrayListOf("квадриллион","квадриллиона","квадриллионов")
    override val quintillion_list = arrayListOf("квинтиллион","квинтиллиона","квантиллионов")

    override fun deleteNullsandAddUnits(string: String, list: List<String>): String {
        if (list.size != 3) {
            throw IllegalArgumentException("THREE TYPES OF AMOUNT REQUIRED FOR R_PARSER !!!")
        }
        var one: String = list.get(0)
        var two: String = list.get(1)
        var many: String = list.get(2)
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
            else if (string.length > 0 && (string.get(2).equals('2') || string.get(2).equals('3') || string.get(2).equals('4'))) {
                number = two
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
                    result = string.substring(2,string.length) + " $number"
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
        else if (string.length > 0 && (string.get(string.length-1).equals('2') || string.get(string.length-1).equals('3') || string.get(string.length-1).equals('4'))) {
            number = two
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
                result = string.substring(2,string.length) + " $number"
            }
            else {
                result = string + " $number"
            }
        }
        return result
    }
}