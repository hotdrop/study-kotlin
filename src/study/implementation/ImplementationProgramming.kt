package study.implementation

import study.AbstractStudy
import java.util.*

class ImplementationProgramming : AbstractStudy(ImplementationProgramming::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 8
        when (targetNo) {
            1 -> bonAppetitInput()
            2 -> dayOfTheProgrammerInput()
            3 -> migratoryBirds()
            4 -> divisibleSumPairs()
            5 -> birthdayChocolate()
            6 -> breakingTheRecords()
            7 -> gradingStudents()
            8 -> betweenTwoSets()
            9 -> sockMerchant()
            10 -> drawingBook()
            11 -> countingValleys()
            12 -> electronicsShop()
            13 -> catsAndMouse()
            14 -> theHurdleRace()
            15 -> designerPDFViewer()
            16 -> utopianTree()
            17 -> angryProfessor()
            18 -> pickingNumber()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    private fun bonAppetitInput() {
        val cin = Scanner(System.`in`)
        val eatItemCount = cin.next().toInt()
        val notEatItemIdx = cin.next().toInt()
        val eatItems = IntArray(eatItemCount)
        (0 until eatItemCount).forEach { eatItems[it] = cin.next().toInt() }
        val annaCharged = cin.next().toInt()

        val sum = ImplementationUtility.sumIntArrayExclusionIndex(eatItems, notEatItemIdx)
        val overCharge = annaCharged - sum / 2

        if (overCharge > 0) {
            println(overCharge)
        } else {
            println("Bon Appetit")
        }
    }

    private fun dayOfTheProgrammerInput() {
        val cin = Scanner(System.`in`)
        val year = cin.next().toInt()

        val result = when {
            year == 1918 -> "26.09.1918"
            (year <= 1917 && year % 4 == 0) || ImplementationUtility.isLeapYear(year) -> "12.09.$year"
            else -> "13.09.$year"
        }
        println(result)
    }

    private fun migratoryBirds() {
        val cin = Scanner(System.`in`)
        val birdCnt = cin.next().toInt()
        val birdTypes = IntArray(birdCnt)
        (0 until birdCnt).forEach { birdTypes[it] = cin.next().toInt() }

        val typeCountArray = IntArray(6)
        birdTypes.forEach { typeCountArray[it] += 1 }

        val maxCntIdx = ImplementationUtility.maxIndexOfHighestValue(typeCountArray)
        println(maxCntIdx)
    }

    private fun divisibleSumPairs() {
        val cin = Scanner(System.`in`)
        val size = cin.next().toInt()
        val div = cin.next().toInt()
        val numbers = IntArray(size)
        (0 until size).forEach { numbers[it] = cin.next().toInt() }

        var result = 0
        numbers.filterIndexed { index, _ -> index < size - 1 }
                .forEachIndexed { i, currentNum ->
                    val j = i + 1
                    result += (j until size).filter { (currentNum + numbers[it]) % div == 0 }.count()
                }
        println(result)
    }

    private fun birthdayChocolate() {
        val cin = Scanner(System.`in`)
        val size = cin.next().toInt()
        val numbers = IntArray(size)
        (0 until size).forEach { numbers[it] = cin.next().toInt() }

        // 加算した合計値の判定
        val d = cin.next().toInt()
        // 連続して加算する個数
        val m = cin.next().toInt()
        var count = 0

        numbers.filterIndexed { index, _ -> index + m <= size }
                .forEachIndexed { index, _ ->
                    val sum = (0 until m).sumBy { numbers[it + index] }
                    if (sum == d) count++
                }
        println(count)
    }

    private fun breakingTheRecords() {
        val cin = Scanner(System.`in`)
        val cnt = cin.next().toInt()
        val scores = IntArray(cnt)
        (0 until cnt).forEach { scores[it] = cin.next().toInt() }

        var bestCnt = 0
        var bestScore = scores[0]
        var worstCnt = 0
        var worstScore = scores[0]
        scores.forEach { score ->
            when {
                score > bestScore -> {
                    bestCnt++
                    bestScore = score
                }
                score < worstScore -> {
                    worstCnt++
                    worstScore = score
                }
            }
        }
        println("$bestCnt $worstCnt")
    }

    private fun gradingStudents() {
        val cin = Scanner(System.`in`)
        val cnt = cin.next().toInt()
        val inputGrades = mutableListOf<Int>()

        (1..cnt).forEach {
            inputGrades.add(cin.next().toInt())
        }

        calcGrading(inputGrades).forEach { println(it) }
    }

    private fun calcGrading(grades: List<Int>) = grades.map {
        val roundNum = it % 5
        if (it >= 38 && roundNum >= 3) {
            it + (5 - roundNum)
        } else {
            it
        }
    }

    private fun betweenTwoSets() {
        val cin = Scanner(System.`in`)
        val aCnt = cin.next().toInt()
        val bCnt = cin.next().toInt()
        val aIntArr = IntArray(aCnt)
        val bIntArr = IntArray(bCnt)

        (0 until aCnt).forEach {
            aIntArr[it] = cin.next().toInt()
        }
        (0 until bCnt).forEach {
            bIntArr[it] = cin.next().toInt()
        }

        // Aの最小公倍数を求める
        val lcmInA = ImplementationUtility.calcLcm(aIntArr).toInt()
        // Bの最大公約数を求める
        val gcdInB = ImplementationUtility.calcGcd(bIntArr).toInt()
        // 2つの数値で割れる数を全て求める
        var count = 0
        (lcmInA..gcdInB).forEach { loopIndex ->
            val conditionA = aIntArr.all { loopIndex % it == 0 }
            val conditionB = bIntArr.all { it % loopIndex == 0 }
            if (conditionA && conditionB) count++
        }
        println(count)
    }

    private fun calcLcmAndGcdTest() {
        check(ImplementationUtility.calcLcm(intArrayOf(12)) == 12.0)
        check(ImplementationUtility.calcLcm(intArrayOf(2, 3)) == 6.0)
        check(ImplementationUtility.calcLcm(intArrayOf(108, 56)) == 1512.0)
        check(ImplementationUtility.calcLcm(intArrayOf(42, 72, 180)) == 2520.0)
        check(ImplementationUtility.calcLcm(intArrayOf(12, 42, 72)) == 504.0)

        check(ImplementationUtility.calcGcd(intArrayOf(12)) == 12.0)
        check(ImplementationUtility.calcGcd(intArrayOf(12, 42)) == 6.0)
        check(ImplementationUtility.calcGcd(intArrayOf(108, 56)) == 4.0)
        check(ImplementationUtility.calcGcd(intArrayOf(12, 42, 72)) == 6.0)
    }

    /**
     * ある数値配列を入力とする。
     * その数値配列にて、2つの整数の差分の絶対値が1より小さくなる組み合わせの数値の最大数を出力する。
     * 文章だと結構意味不明なので例を示す。
     *   4 6 5 3 3 1
     * この中で差の絶対値が1より小さくなるのは[6 5] [5 4] [4 3]の3種類の組み合わせ
     * 3は2つあるため、この中で数値が最も多いのは[4 3 3]の3つで出力は 3 となる。
     *
     * 条件: 配列は必ず2つ以上の数値が入力される。
     */
    private fun pickingNumber() {
        val cin =  Scanner(System.`in`)
        val cnt = cin.nextInt()
        val numbers = IntArray(cnt)
        (0 until cnt).forEach { numbers[it] = cin.nextInt() }

        // 先頭から順に数値を取得
        //   前と同じ数値
        //     カウントを取っていく
        //   前と異なる数値 かつ 今の数値との差が1 かつ ペアフラグがOFF
        //     ペアフラグをONにする
        //     今のカウントを持続して次の数値をみる
        //   前と異なる数値
        //     今のカウントをanswerとして保持（ただしanswerの方が大きければ無視）
        //     ペアフラグをOFFにする
        //     カウントを初期化
        //  ループが終了したら最後のカウントをanswerに保持（ただしanswerの方が大きければ無視）
        val sortedNumbers = numbers.sorted()
        var answerCnt = 0
        // 自分が含まれるため、numCntは常に１から始まるものとする。
        var numCnt = 1
        var hasPair = false
        for(idx in 1 until sortedNumbers.size) {
            when {
                sortedNumbers[idx] == sortedNumbers[idx-1] -> numCnt++
                sortedNumbers[idx] == sortedNumbers[idx-1] + 1 && !hasPair -> {
                    numCnt++
                    hasPair = true
                }
                else -> {
                    if(answerCnt < numCnt) answerCnt = numCnt
                    hasPair = false
                    numCnt = 1
                }
            }
        }
        if(answerCnt < numCnt) answerCnt = numCnt
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