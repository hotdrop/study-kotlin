package study.implementation

import study.AbstractStudy
import java.util.*

class SecondPage: AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 3
        when(targetNo) {
            1 -> sockMerchant()
            2 -> drawingBook()
            3 -> countingValleys()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 山登りをし、谷の数を出力する。
     * 登りをU、降りをDとし、UとDで構成された文字列を入力とする。
     * 0を出発点とし谷の数を数える。
     * 谷の条件は、出発点である0より下降し再び0地点まで上がってくるところを指す。
     * 例:
     *  入力: UDDDUDUU
     *  出力: 1
     * UDDで出発点下の傾斜に入り、以降は谷底となる。再び出発点以上の標高になるのは最後のUとなる。
     * したがって谷は1つ
     */
    private fun countingValleys() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val steps = cin.next()

        var valleyCount = 0
        var currentPoint = 0
        steps.forEach {
            when(it) {
                'U' -> currentPoint++
                'D' -> currentPoint--
            }
            if(currentPoint == 0 && it == 'U') {
                valleyCount++
            }
        }
        println(valleyCount)
    }

    /**
     * 両開きの本があって、指定されたページにたどり着くまで
     * １ページ目から(右へめくると最終ページ目から(左へめくる)めくるページ数が最小の方を求める。
     * 例えば6ページの本に対し、4ページ目を開け、となったら
     * 1ページ目から -> 2|3 -> 4|5 で2回
     * 6ページ目から -> 4|5 で1回
     * したがって、出力する数値は 1 となる。
     */
    private fun drawingBook() {
        val cin = Scanner(System.`in`)
        val totalPage = cin.nextInt()
        val openPage = cin.nextInt()

        val turnPageWithFirst = if(openPage%2 == 0) openPage/2 else (openPage - 1)/2
        val turnPageWithEnd = (totalPage/2) - turnPageWithFirst

        val minimumTurnPage = if(turnPageWithFirst < turnPageWithEnd) turnPageWithFirst else turnPageWithEnd
        println(minimumTurnPage)
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