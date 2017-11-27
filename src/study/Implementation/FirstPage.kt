package study.Implementation

import study.AbstractStudy
import study.util.PrimeFactorizations
import java.util.*

class FirstPage: AbstractStudy(FirstPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 8
        when(targetNo) {
            1 -> bonAppetitInput()
            2 -> dayOfTheProgrammerInput()
            3 -> migratoryBirds()
            4 -> divisibleSumPairs()
            5 -> birthdayChocolate()
            6 -> breakingTheRecords()
            7 -> gradingStudents()
            8 -> betweenTwoSets()
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

        val overCharge = calcCharge(eatItems, notEatItemIdx, annaCharged)
        if(overCharge > 0) {
            println(overCharge)
        } else {
            println("Bon Appetit")
        }
    }

    private fun calcCharge(eatItems: IntArray, notEatItemIdx: Int, annaCharged: Int): Int {
        eatItems.filterIndexed { index, _ -> index != notEatItemIdx }
                .sum()
                .run {
                    return annaCharged - this/2
                }
    }

    private fun dayOfTheProgrammerInput() {
        val cin = Scanner(System.`in`)
        val year = cin.next().toInt()

        val result = dayOfTheProgrammer(year)
        println(result)
    }

    private fun dayOfTheProgrammer(year: Int): String =
            when {
                year == 1918 -> "26.09.1918"
                (year <= 1917 && year%4 == 0) || isLeapYear(year) -> "12.09.$year"
                else -> "13.09.$year"
            }

    private fun isLeapYear(year: Int): Boolean =
            (year%400 == 0 || (year%4 == 0 && year%100 != 0))

    private fun migratoryBirds() {
        val cin = Scanner(System.`in`)
        val birdCnt = cin.next().toInt()
        val birdTypes = IntArray(birdCnt)
        (0 until birdCnt).forEach { birdTypes[it] = cin.next().toInt() }

        val typeCountArray = IntArray(6)
        birdTypes.forEach { typeCountArray[it] += 1 }

        var maxCntIdx = 0
        var maxCnt = 0
        typeCountArray.forEachIndexed { index, count ->
            if(count > maxCnt) {
                maxCnt = count
                maxCntIdx = index
            }
        }
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
                    result += (j until size).filter { (currentNum + numbers[it])%div == 0 }.count()
                }
        println(result)
    }

    private fun birthdayChocolate() {
        val cin = Scanner(System.`in`)
        val size = cin.next().toInt()
        val numbers = IntArray(size)
        (0 until size).forEach { numbers[it] = cin.next().toInt() }

        // dは加算した合計値の判定
        val d = cin.next().toInt()
        // mは連続して加算する個数
        val m = cin.next().toInt()
        var count = 0

        numbers.filterIndexed { index, _ -> index + m <= size }
                .forEachIndexed { index, _ ->
                    val sum = (0 until m).sumBy { numbers[it + index] }
                    if(sum == d) count++
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
        val roundNum = it%5
        if(it >= 38 && roundNum >= 3) {
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
        val lcmInA = calcLcm(aIntArr).toInt()
        // Bの最大公約数を求める
        val gcdInB = calcGcd(bIntArr).toInt()
        // 2つの数値で割れる数を全て求める
        var count = 0
        (lcmInA..gcdInB).forEach { loopIndex ->
            val conditionA = aIntArr.all { loopIndex%it == 0 }
            val conditionB = bIntArr.all { it%loopIndex == 0 }
            if(conditionA && conditionB) count++
        }
        println(count)
    }

    private fun calcLcmAndGcdTest() {
        check(calcLcm(intArrayOf(12)) == 12.0)
        check(calcLcm(intArrayOf(2, 3)) == 6.0)
        check(calcLcm(intArrayOf(108, 56)) == 1512.0)
        check(calcLcm(intArrayOf(42, 72, 180)) == 2520.0)
        check(calcLcm(intArrayOf(12, 42, 72)) == 504.0)

        check(calcGcd(intArrayOf(12)) == 12.0)
        check(calcGcd(intArrayOf(12, 42)) == 6.0)
        check(calcGcd(intArrayOf(108, 56)) == 4.0)
        check(calcGcd(intArrayOf(12, 42, 72)) == 6.0)
    }

    /**
     * N個の数値の最小公倍数を求める
     */
    private fun calcLcm(numArray: IntArray): Double {
        val primeFactorizations = PrimeFactorizations(numArray)
        var result = 1.0
        primeFactorizations.getDivisors()
                .forEach {
                    result *= Math.pow(it.toDouble(), primeFactorizations.maxIndex(it).toDouble())
                }
        return result
    }

    private fun calcGcd(numArray: IntArray): Double {
        val primeFactorizations = PrimeFactorizations(numArray)
        var result = 1.0
        primeFactorizations.getDivisors()
                .forEach {
                    result *= Math.pow(it.toDouble(), primeFactorizations.minIndex(it).toDouble())
                }
        return result
    }
}