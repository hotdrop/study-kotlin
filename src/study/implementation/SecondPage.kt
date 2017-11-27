package study.implementation

import study.AbstractStudy
import java.util.*

class SecondPage: AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 1
        when(targetNo) {
            1 -> sockMerchant()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 入力された数値列のうちペアの数をカウントする。
     * 例:
     * 入力
     *  6
     *  10 20 10 10 30 20
     * 出力
     *  2
     */
    private fun sockMerchant() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numArr = IntArray(cnt)
        (0 until cnt).forEach { numArr[it] = cin.nextInt() }

        val pairSockCount = countPairSock(numArr)
        println(pairSockCount)
    }

    private fun countPairSock(numArr: IntArray): Int {
        val countMap = mutableMapOf<Int, Int>()
        var pairSockCnt = 0
        numArr.forEach { num ->
            if(countMap.containsKey(num)) {
                countMap[num]?.let {
                    val sockCnt = it + 1
                    if(sockCnt%2 == 0) {
                        pairSockCnt++
                    }
                    countMap.replace(num, sockCnt)
                }
            } else {
                countMap.put(num, 1)
            }
        }
        return pairSockCnt
    }
}