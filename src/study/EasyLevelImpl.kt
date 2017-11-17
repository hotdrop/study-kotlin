package study

import java.util.*
import kotlin.collections.HashMap

class EasyLevelImpl: AbstractStudy(EasyLevelImpl::class.java.simpleName) {

    override fun execute() {
        super.execute()
        //gradingStudents()
        //betweenTwoSets()
        println("Test Lcm")
        val result = calcLcm(108, 56)
        println(" 最小公倍数=$result")
        val result2 = calcGcd(108, 56)
        println(" 最大公約数=$result2")
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
     * ２つの数値の最小公倍数を求める
     */
    private fun calcLcm(a: Int, b: Int): Double {

        val aPFMap = calcPrimeFactorization(a)
        val bPFMap = calcPrimeFactorization(b)

        // それぞれのKeyを比較しValueの大きい方を掛け合わせる
        var result = 1.toDouble()
        val concatKeys = concatAndReturnList(aPFMap.keys, bPFMap.keys)
        concatKeys.forEach {
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
     * ２つの数値の最大公約数を求める
     */
    private fun calcGcd(a: Int, b: Int): Double {

        val aPFMap = calcPrimeFactorization(a)
        val bPFMap = calcPrimeFactorization(b)

        // それぞれのKeyを比較しValueの小さい方を掛け合わせる
        var result = 1.toDouble()
        val concatKeys = concatAndReturnList(aPFMap.keys, bPFMap.keys)
        concatKeys.forEach {
            val aVal = aPFMap.getOrDefault(it, 0).toDouble()
            val bVal = bPFMap.getOrDefault(it, 0).toDouble()
            result *= when {
                aVal < bVal -> Math.pow(it.toDouble(), aVal)
                else -> Math.pow(it.toDouble(), bVal)
            }
        }
        return result
    }

    /**
     * 素因数分解をTrial division法で行い、その結果をMapで取得する。
     * Keyは割った素数N、valueは指数とする。
     */
    private fun calcPrimeFactorization(num: Int): MutableMap<Int, Int> {

        var temp = num
        var divNum = 2
        val resultMap = mutableMapOf<Int, Int>()

        // 素因数分解アルゴリズムで最も単純なTrial division法（２から順にルートNまでの素数で割っていく）を採用する。
        // そのため、ループは「合成数xはNの2乗より大きくはならない」という性質を条件としている。
        while(divNum * divNum < num) {

            if(temp%divNum == 0) {
                if(resultMap.containsKey(divNum)) {
                    var count = resultMap[divNum] ?: 0
                    resultMap.replace(divNum, ++count)
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

    /**
     * これもっと良い方法はないものか・・
     */
    private fun concatAndReturnList(s1: MutableSet<Int>, s2: MutableSet<Int>): List<Int> {
        val tmp = s1.toMutableList()
        tmp.addAll(s2.toMutableList())
        return tmp.distinct()
    }

}