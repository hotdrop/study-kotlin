package study

import java.util.*
import kotlin.collections.HashMap

class EasyLevelImpl: AbstractStudy(EasyLevelImpl::class.java.simpleName) {

    override fun execute() {
        super.execute()
        //gradingStudents()
        //betweenTwoSets()
        println("Test Lcm")
        val result = calcLcm(16, 24)
        println(" result=$result")
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
        // 21:28-
        val cin = Scanner(System.`in`)
        val aSetCnt = cin.next().toInt()
        val bSetCnt = cin.next().toInt()
        val aSet = mutableListOf<Int>()
        val bSet = mutableListOf<Int>()

        (1..aSetCnt).forEach {
            aSet.add(cin.next().toInt())
        }
        (1..bSetCnt).forEach {
            bSet.add(cin.next().toInt())
        }
    }

    private fun calcBetweenTwoSets(aSet: List<Int>, bSet: List<Int>) {
        // 2 4     2 5
        // 16 32 96   2 4 8 16
        val lcmList = mutableListOf<Int>()
        aSet.forEach {

        }
    }

    /**
     * 最小公倍数を求める
     */
    private fun calcLcm(a: Int, b: Int): Double {

        val aPFMap = calcPrimeFactorization(a)
        val bPFMap = calcPrimeFactorization(b)

        val concatkeys = concatAndReturnList(aPFMap.keys, bPFMap.keys)

        // それぞれのKeyを比較しValueの大きい方を掛け合わせる
        var result = 1.toDouble()
        concatkeys.forEach {
            val aVal = aPFMap.getOrDefault(it, 0).toDouble()
            val bVal = bPFMap.getOrDefault(it, 0).toDouble()
            result *= when {
                aVal >= bVal -> Math.pow(it.toDouble(), aVal)
                else -> Math.pow(it.toDouble(), bVal)
            }
        }

        return result
    }

    /**
     * 最大公約数を求める
     */
    private fun calcGcd(a: Int, b: Int) {

    }

    /**
     * 素因数分解をして結果をMapで取得する
     * valueが指数となる。
     */
    private fun calcPrimeFactorization(num: Int): MutableMap<Int, Int> {

        var temp = num
        var divNum = 2
        val resultMap = mutableMapOf<Int, Int>()

        while(divNum * divNum < num) {

            if(temp%divNum == 0) {
                if(resultMap.containsKey(divNum)) {
                    var prevNum = resultMap[divNum] ?: 0
                    resultMap.replace(divNum, ++prevNum)
                } else {
                    resultMap.put(divNum, 1)
                }
                temp /= divNum
            } else {
                divNum++
            }
        }
        return resultMap
    }

    private fun concatAndReturnList(s1: MutableSet<Int>, s2: MutableSet<Int>): List<Int> {
        val tmp = s1.toMutableList()
        tmp.addAll(s2.toMutableList())
        return tmp.distinct()
    }

}