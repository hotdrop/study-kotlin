package study.dynamic

import study.AbstractStudy
import java.math.BigInteger
import java.util.*

class DynamicProgramming : AbstractStudy(DynamicProgramming::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 1
        when (targetNo) {
            1 -> fibonacciModified()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 拡張フィボナッチ数列を作る。
     * 入力値は3つ
     *  t1: 数列の最初の値
     *  t2: 数列の次の値
     *  n:  数列を先頭から順に数え、n番目を出力する。(5だったら5つ目の値を出力する)
     */
    private fun fibonacciModified() {
        val cin = Scanner(System.`in`)
        val t1 = cin.nextInt()
        val t2 = cin.nextInt()
        val n = cin.nextInt()

        val tn = DynamicUtility.calcExFibonacci(t1, t2, n)
        println(tn)
    }
}