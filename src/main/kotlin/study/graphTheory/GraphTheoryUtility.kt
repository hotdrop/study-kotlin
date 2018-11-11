package study.graphTheory

object GraphTheoryUtility {

    /**
     * 引数で指定された数値の総ペア数を求める。
     */
    fun calcPairCombinationNum(num: Long) = (num * (num - 1)) / 2
}