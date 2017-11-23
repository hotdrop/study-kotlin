package study.sorting

import study.AbstractStudy
import java.math.BigInteger
import java.util.*

class FirstPage: AbstractStudy(FirstPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 1
        when(targetNo) {
            1 -> bigSorting()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 正解率60%なんだけど・・辛そう
     */
    private fun bigSorting() {
        val cin = Scanner(System.`in`)
        val cnt = cin.next().toInt()
        val strNumberArr = mutableListOf<String>()
        (0 until cnt).forEach {
            strNumberArr.add(cin.next())
        }

        sortByBigNumber(strNumberArr).forEach {
            println(it)
        }
    }

    private fun sortByBigNumber(s: List<String>): List<String> {
        val sortedElements = mutableListOf<String>()
        val sToLengthGroup = s.groupBy { it.length }

        sToLengthGroup.toSortedMap().forEach { length, values ->
            if(lessThanLongValue(length, values)) {
                values.sortedBy { it.toLong() }.forEach { sortedElements.add(it) }
            } else {
                values.sortedBy { BigInteger(it) }.forEach { sortedElements.add(it) }
            }
        }
        return sortedElements
    }

    private fun lessThanLongValue(len: Int, values: List<String>) =
        when {
            len == 19 -> {
                // 面倒なので正確にやらない.先頭５バイトが92233未満ならLongで処理できるとみなす
                val numberOfStartWithLongMax = Long.MAX_VALUE.toString().substring(0, 5).toInt()
                values.all{ it.substring(0, 5).toInt() < numberOfStartWithLongMax }
            }
            len < 19 -> true
            else -> false
        }
}