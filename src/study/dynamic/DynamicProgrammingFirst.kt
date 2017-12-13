package study.dynamic

import study.AbstractStudy
import java.math.BigInteger
import java.util.*

class DynamicProgrammingFirst: AbstractStudy(DynamicProgrammingFirst::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 1
        when(targetNo) {
            1 -> fibonacciModified()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 拡張フィボナッチ数列を作る。
     * 通常フィボナッチ数列は Tn + Tn+1 = Tn+2 となるが、ここでは Tn + (Tn+1)^2 = Tn+2 とする。
     * 入力値は3つ
     *  t1: 数列の最初の値
     *  t2: 数列の次の値
     *  n: 数列の中で出力する番号(5だったら5つ目の値を出力する)
     * 例:
     *  0 1 10
     * 出力:
     *  84266613096281243382112
     *  0 1で始めると11個の拡張フィボナッチ数列は以下のようになる。
     *      0 1 1 2 5 27 734 538783 290287121823 84266613096281243382112 7100862082718357559748563880517486086728702367
     *  このうち、10番目の値を出力する。
     */
    private fun fibonacciModified() {
        val cin = Scanner(System.`in`)
        val t1 = cin.nextInt()
        val t2 = cin.nextInt()
        val n = cin.nextInt()

        val tn = calcExFibonacci(t1, t2, n)
        println(tn)
    }

    private fun calcExFibonacci(t1: Int, t2: Int, n: Int): BigInteger {
        var currentT = BigInteger.valueOf(t1.toLong())
        var nextT = BigInteger.valueOf(t2.toLong())

        (1 until n).forEach {
            val tmpNextT = nextT
            nextT = currentT + (nextT * nextT)
            currentT = tmpNextT
        }
        return currentT
    }
}