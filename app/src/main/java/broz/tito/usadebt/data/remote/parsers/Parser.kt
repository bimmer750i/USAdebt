package broz.tito.usadebt.data.remote.parsers

abstract class Parser {

    abstract val usd_list: List<String>
    abstract val thousand_list: List<String>
    abstract val million_list: List<String>
    abstract val billion_list: List<String>
    abstract val trillion_list: List<String>
    abstract val quadrillion_list: List<String>
    abstract val quintillion_list: List<String>

    fun parse(header: String,date: String,string: String) : String {
        var output: String
        val length = string.length
        val debtRadix = DebtRadix()
        if (length in 1..6) {
            debtRadix.usd = string
        }
        else if (length in 7..9) {
            debtRadix.thousands = string.substring(0,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else if (length in 10..12) {
            debtRadix.millions = string.substring(0,length-9)
            debtRadix.thousands = string.substring(length-9,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else if (length in 13..15) {
            debtRadix.billions = string.substring(0,length-12)
            debtRadix.millions = string.substring(length-12,length-9)
            debtRadix.thousands = string.substring(length-9,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else if (length in 16..18) {
            debtRadix.trillions = string.substring(0,length-15)
            debtRadix.billions = string.substring(length-15,length-12)
            debtRadix.millions = string.substring(length-12,length-9)
            debtRadix.thousands = string.substring(length-9,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else if (length in 19..21) {
            debtRadix.quadrillions = string.substring(0,length-18)
            debtRadix.trillions = string.substring(length-18,length-15)
            debtRadix.billions = string.substring(length-15,length-12)
            debtRadix.millions = string.substring(length-12,length-9)
            debtRadix.thousands = string.substring(length-9,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else if (length in 22..24) {
            debtRadix.quintillions = string.substring(0,length-21)
            debtRadix.quadrillions = string.substring(length-21,length-18)
            debtRadix.trillions = string.substring(length-18,length-15)
            debtRadix.billions = string.substring(length-15,length-12)
            debtRadix.millions = string.substring(length-12,length-9)
            debtRadix.thousands = string.substring(length-9,length-6)
            debtRadix.usd = string.substring(length-6,length)
        }
        else {
            debtRadix.quintillions = "000"
            debtRadix.quadrillions = "000"
            debtRadix.trillions = "000"
            debtRadix.billions = "000"
            debtRadix.millions = "000"
            debtRadix.thousands = "000"
            debtRadix.usd = "000"
        }

        // ADDING UNITS AND DELETING NULLS

        if (!debtRadix.quintillions.equals("")) {
            debtRadix.quintillions = deleteNullsandAddUnits(debtRadix.quintillions,quintillion_list)
            debtRadix.quadrillions = deleteNullsandAddUnits(debtRadix.quadrillions,quadrillion_list)
            debtRadix.trillions = deleteNullsandAddUnits(debtRadix.trillions,trillion_list)
            debtRadix.billions = deleteNullsandAddUnits(debtRadix.billions,billion_list)
            debtRadix.millions = deleteNullsandAddUnits(debtRadix.millions,million_list)
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")){
                output ="\n\n" + header + "\n\n" + debtRadix.quintillions + "\n\n" + debtRadix.quadrillions + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.quintillions + "\n\n"  + debtRadix.quadrillions + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }

        }

        else if (!debtRadix.quadrillions.equals("")) {
            debtRadix.quadrillions = deleteNullsandAddUnits(debtRadix.quadrillions,quadrillion_list)
            debtRadix.trillions = deleteNullsandAddUnits(debtRadix.trillions,trillion_list)
            debtRadix.billions = deleteNullsandAddUnits(debtRadix.billions,billion_list)
            debtRadix.millions = deleteNullsandAddUnits(debtRadix.millions,million_list)
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")){
                output ="\n\n" + header + "\n\n" + debtRadix.quadrillions + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.quadrillions + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }

        }

        else if (!debtRadix.trillions.equals("")) {
            debtRadix.trillions = deleteNullsandAddUnits(debtRadix.trillions,trillion_list)
            debtRadix.billions = deleteNullsandAddUnits(debtRadix.billions,billion_list)
            debtRadix.millions = deleteNullsandAddUnits(debtRadix.millions,million_list)
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")) {
                output ="\n\n" + header + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.trillions + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }

        }
        else if (!debtRadix.billions.equals("")) {
            debtRadix.billions = deleteNullsandAddUnits(debtRadix.billions,billion_list)
            debtRadix.millions = deleteNullsandAddUnits(debtRadix.millions,million_list)
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")) {
                output ="\n\n" + header + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.billions + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
        }
        else if (!debtRadix.millions.equals("")) {
            debtRadix.millions = deleteNullsandAddUnits(debtRadix.millions,million_list)
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")) {
                output ="\n\n" + header + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.millions + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
        }
        else if (!debtRadix.thousands.equals("")) {
            debtRadix.thousands = deleteNullsandAddUnits(debtRadix.thousands,thousand_list)
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd,usd_list)
            if (!header.equals("")) {
                output ="\n\n" + header + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.thousands + "\n\n" + debtRadix.usd
            }
        }
        else {
            debtRadix.usd = deleteNullsandAddUnits(debtRadix.usd, usd_list)
            if (!header.equals("")) {
                output ="\n\n" + header + "\n\n" + debtRadix.usd
            }
            else {
                output = date + "\n\n" + debtRadix.usd
            }
        }

        return output
    }

    open abstract fun deleteNullsandAddUnits(string: String,list: List<String>) : String
}