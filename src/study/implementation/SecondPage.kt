package study.implementation

import study.AbstractStudy
import java.util.*

class SecondPage: AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 10
        when(targetNo) {
            1 -> sockMerchant()
            2 -> drawingBook()
            3 -> countingValleys()
            4 -> electronicsShop()
            5 -> catsAndMouse()
            6 -> theHurdleRace()
            7 -> designerPDFViewer()
            8 -> utopianTree()
            9 -> angryProfessor()
            10 -> pickingNumber()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * ある数値配列を入力とする。
     * その数値配列にて、2つの整数の差分の絶対値が1より小さくなる組み合わせの数値の最大数を出力する。
     * 文章だと結構意味不明なので例を示す。
     *   4 6 5 3 3 1
     * この中で差の絶対値が1より小さくなるのは[6 5] [5 4] [4 3]の3種類の組み合わせ
     * 3は2つあるため、この中で数値が最も多いのは[4 3 3]の3つで出力は 3 となる。
     */
    private fun pickingNumber() {
        val cin =  Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numbers = IntArray(cnt)
        (0 until cnt).forEach { numbers[it] = cin.nextInt() }

        var answerCnt = 0
        numbers.forEach { currentNum ->
            // これやるならforで回してcntした方がいいかも・・
            val posCnt = numbers.count { currentNum - it in 0..1 }
            val negCnt = numbers.count { currentNum - it in -1..0 }
            val moreCnt = if(posCnt > negCnt) posCnt else negCnt
            if(answerCnt < moreCnt) {
                answerCnt = moreCnt
            }
        }
        println(answerCnt)
    }

    /**
     * angryProfessorの、標準入力の値保持用のデータクラス
     */
    private data class MathClass(
            val studentsNum: Int,
            val thresholdStudentsNum: Int,
            val arriveStudentTimes: IntArray)

    /**
     * ある大学で数学の講義を受け持つ教授がいる。
     * 生徒はとても欠席率が高く、教授は怒って生徒の出席数が一定数を満たさなかった場合、教授は怒って講義をキャンセルすることにした。
     * 教授が怒って講義をキャンセルする場合は「YES」、生徒数が満たされて講義を開始する場合は「NO」と出力する。
     * 入力は以下の通り。
     * 1行目: テストケース数
     * 2行目: 講義を取っている生徒数 教授が講義を開く生徒数の閾値
     * 3行目: 生徒がその講義の教室に到着するまでの時間。0を講義開始時間として、マイナスは事前について着席していた生徒、プラスは遅刻した生徒
     *
     */
    private fun angryProfessor() {
        val mathClasses = mutableListOf<MathClass>()
        val cin = Scanner(System.`in`)
        val testCase = cin.nextInt()
        (0 until testCase).forEach {
            val studentsNum = cin.nextInt()
            val thresholdStudentsNum = cin.nextInt()
            val arriveTimes = IntArray(studentsNum)
            (0 until studentsNum).forEach { arriveTimes[it] = cin.nextInt() }
            mathClasses.add(MathClass(studentsNum, thresholdStudentsNum, arriveTimes))
        }

        mathClasses.forEach {
            val isCancel = isCancelClass(it.thresholdStudentsNum, it.arriveStudentTimes)
            if(isCancel) println("YES") else println("NO")
        }
    }

    private fun isCancelClass(thresholdStudentsNum: Int, arriveStudentTimes: IntArray): Boolean {
        val arrivedNum = arriveStudentTimes.count { it <= 0 }
        return arrivedNum < thresholdStudentsNum
    }

    /**
     * 初期は1m、夏に1m育って次の春に2倍育つ木がある。
     * 最初の入力は数（これいつもいらないだろと思う）、次のNでサイクル数をそれぞれ入力する。
     * 例
     *  0: 夏1回なので結果は 1
     *  1: 夏→春 なので結果は 2
     *  2: 夏→春→夏 なので結果は 3
     *  4: 夏(1m)→春(2m)→夏(3m)→春(6m)→夏(7m) となり結果は 7
     */
    private fun utopianTree() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val cycles = IntArray(cnt)
        (0 until cnt).forEach { cycles[it] = cin.nextInt() }

        cycles.forEach { println(growTreeMeter(it)) }
    }

    private fun growTreeMeter(cycle: Int): Int {
        var treeHeight = 0
        (0..cycle).forEach { treeHeight = if(it%2 == 0) treeHeight + 1 else treeHeight+treeHeight }
        return treeHeight
    }

    /**
     * 最初にa〜zの26個の重みを入力とする。
     * 次に単語を入力し、最大の重みと文字数をかけた値を出力する。
     *
     */
    private fun designerPDFViewer() {
        val cin = Scanner(System.`in`)
        val allWordPoints = IntArray(26)
        (0 until 26).forEach { allWordPoints[it] = cin.nextInt() }
        val words = cin.next()

        val wordPoints = words.map { it.toInt() - 'a'.toInt() }.toList()
        val maxPoint = allWordPoints.filterIndexed { index, _ -> wordPoints.any { it == index } }.max() ?: 0
        println(maxPoint * words.length)
    }

    /**
     * ジャンプレースゲームにおいて、以下を入力とする。
     * ・ハードル数
     * ・キャラのジャンプ力
     * ・ハードル（入力するのは高さ）
     * ジャンプ力がハードルの高さを超えていれば飛び越えられる。
     * ジャンプ力が足りなければアイテムを使用しジャンプ力を1プラスできる。
     * アイテムの効果は1回のゲームクリア時まで有効となる。
     * クリアするため最大何回アイテムを使用すれば良いか出力する。
     * 例:
     * 入力:
     *   5 4
     *   2 5 3 7 1
     * 出力: 3
     * これはハードル数は5つ、ジャンプ力4となる。
     * ハードル7を超えないとクリアできないため、アイテムを3回使用してジャンプ力を7にする必要がある。
     * したがって、出力は3となる。
     */
    private fun theHurdleRace() {
        val cin = Scanner(System.`in`)
        val hurdleNum = cin.nextInt()
        val jumpState = cin.nextInt()
        val hurdles = IntArray(hurdleNum)
        (0 until hurdleNum).forEach { hurdles[it] = cin.nextInt() }

        val maxHurdleHeight = hurdles.max() ?: 0
        val itemUseCnt = if(jumpState < maxHurdleHeight) maxHurdleHeight - jumpState else 0
        println(itemUseCnt)
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