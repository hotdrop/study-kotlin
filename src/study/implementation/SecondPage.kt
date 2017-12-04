package study.implementation

import study.AbstractStudy
import java.util.*

class SecondPage: AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 5
        when(targetNo) {
            1 -> sockMerchant()
            2 -> drawingBook()
            3 -> countingValleys()
            4 -> electronicsShop()
            5 -> catsAndMouse()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * ２匹の猫が、あるpointX、pointYから同じスピードで移動し、pointZにいるマウスを捕まえる。
     *   Xにいる猫がマウスを捕まえられる場合「Cat A」
     *   Yにいる猫がマウスを捕まえられる場合「Cat B」
     *   XとYが同時にマウスのいるZに行った場合、喧嘩するのでマウスが逃げるため「Mouse C」
     * と出力する。
     */
    private fun catsAndMouse() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()

        (0 until cnt).forEach {
            judgeWinAnimal(cin.nextInt(), cin.nextInt(), cin.nextInt())
        }
    }

    private fun judgeWinAnimal(pointX: Int, pointY: Int, pointZ: Int) {
        val distanceXtoZ = Math.abs(pointX - pointZ)
        val distanceYtoZ = Math.abs(pointY - pointZ)
        when {
            distanceXtoZ < distanceYtoZ -> println("Cat A")
            distanceXtoZ > distanceYtoZ -> println("Cat B")
            else -> println("Mouse C")
        }
    }

    /**
     * あるショップに行ってキーボードとUSBドライブを購入したい。
     * 入力は5つ「予算」「キーボードの数」「USBドライブの数」「キーボードの価格」「USBドライブの価格」
     * 予算を最大限使い切る組み合わせの価格を出力する。予算をオーバーする組み合わせしかない場合は −1 を出力する
     * 例:
     * 入力
     *   10 2 3   <- 予算10 キーボード2つ USBドライブ3つ
     *   3 1      <- キーボードは$3、$1
     *   5 2 8    <- USBドライブは$5、$2、$8
     * 出力
     *   9　　     <- 最大はキーボード$1、USBドライブ$8 で合計$9
     */
    private fun electronicsShop() {
        val cin = Scanner(System.`in`)

        val myMoney = cin.nextInt()
        val keyboardCnt = cin.nextInt()
        val usbDriveCnt = cin.nextInt()

        val keyboardCosts = IntArray(keyboardCnt)
        (0 until keyboardCnt).forEach { keyboardCosts[it] = cin.nextInt() }

        val usbDriveCosts = IntArray(usbDriveCnt)
        (0 until usbDriveCnt).forEach { usbDriveCosts[it] = cin.nextInt() }

        val maxCost = calcMaxCost(myMoney, keyboardCosts, usbDriveCosts)
        println(maxCost)
    }

    private fun calcMaxCost(limitCost: Int, keyboardCosts: IntArray, usbDriveCosts: IntArray): Int {
        var maxCost = -1
        // O(n^2)・・・・
        keyboardCosts.forEach { keyboardCost ->
            usbDriveCosts.forEach { usbDriveCost ->
                val pairCost = keyboardCost + usbDriveCost
                if(pairCost in maxCost..limitCost) {
                    maxCost = pairCost
                }
            }
        }
        return maxCost
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