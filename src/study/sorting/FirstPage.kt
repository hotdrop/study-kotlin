package study.sorting

import study.AbstractStudy
import java.math.BigInteger
import java.util.*

class FirstPage: AbstractStudy(FirstPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 5
        when(targetNo) {
            1 -> bigSorting()
            2 -> sampleChallenge()
            3 -> insertionSort()
            4 -> runningTimeOfAlgorithms()
            5 -> quickSortPart1()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * part1はQuickSortの最初の操作であるパーティションを学ぶ。
     * 入力された数値配列prに対し、pr[0]となる数値pに対して
     *  pより小さい要素をleft配列
     *  pより大きい要素をright配列
     *  pと同じ要素はequal配列
     * とパーティション区切りにし、left + equal + right の順にsingle line出力する。
     * それぞれは未ソートで良い。
     */
    private fun quickSortPart1() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numArr = IntArray(cnt)
        (0 until cnt).forEach { numArr[it] = cin.nextInt() }

        val p = numArr[0]
        val leftArr = mutableListOf<Int>()
        val equalArr = mutableListOf<Int>()
        val rightArr = mutableListOf<Int>()

        numArr.forEach {
            when {
                p == it -> equalArr.add(it)
                p < it -> rightArr.add(it)
                else -> leftArr.add(it)
            }
        }

        val concatArr = leftArr.apply {
            addAll(equalArr)
            addAll(rightArr)
        }
        println(concatArr.joinToString(" "))
    }

    /**
     * 挿入ソートのシフト回数を出力する
     */
    private fun runningTimeOfAlgorithms() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numArr = IntArray(cnt)
        (0 until cnt).forEach { numArr[it] = cin.nextInt() }

        var shiftCnt = 0
        (1 until numArr.size).forEach { i ->
            val currentNum = numArr[i]
            var j = i-1
            while(j >= 0 && numArr[j] > currentNum) {
                numArr[j+1] = numArr[j]
                j--
                shiftCnt++
            }
            numArr[j+1] = currentNum
        }
        println(shiftCnt)
    }

    /**
     * 挿入ソート
     */
    private fun insertionSort() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numArr = IntArray(cnt)
        (0 until cnt).forEach { numArr[it] = cin.nextInt() }

        (1 until numArr.size).forEach { i ->
            val currentNum = numArr[i]
            var j = i-1
            while(j >= 0 && numArr[j] > currentNum) {
                numArr[j+1] = numArr[j]
                j--
                // このprintはPart1。1つ1つの挿入の過程を確認する。
                // printIntArray(numArr)
            }
            numArr[j+1] = currentNum
            // このprintはPart2。１要素ごとの挿入状態を確認する。
            printIntArray(numArr)
        }
    }

    private fun printIntArray(num: IntArray) {
        println(num.joinToString(" "))
    }

    /**
     * サンプルチャレンジ
     */
    private fun sampleChallenge() {
        val cin = Scanner(System.`in`)
        val searchNum = cin.nextInt()
        val cnt = cin.nextInt()
        val numArr = IntArray(cnt)
        (0 until cnt).forEach { numArr[it] = cin.nextInt() }

        // kotlinの関数が便利なのでこれ使うのはちょっと気が引ける
        val resultIndex = numArr.indexOf(searchNum)
        println(resultIndex)
    }

    /**
     * 正解率60%なんだけど・・辛そう
     */
    private fun bigSorting() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
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