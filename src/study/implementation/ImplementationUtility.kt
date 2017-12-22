package study.implementation

object ImplementationUtility {

    /**
     * 指定したインデックスを除外して合計値を求める
     * これ切り出す意味ない気がするが・・・
     */
    fun sumIntArrayExclusionIndex(numArray: IntArray, exclusionIdx: Int) =
            numArray.filterIndexed { index, _ -> index != exclusionIdx }.sum()

    /**
     * 閏年か調べる
     */
    fun isLeapYear(year: Int): Boolean =
            (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))

    /**
     * 数値配列の最も大きな値のインデックスを取得する
     */
    fun maxIndexOfHighestValue(numArray: IntArray): Int {
        var maxCntIdx = 0
        var maxCnt = 0
        numArray.forEachIndexed { index, count ->
            if (count > maxCnt) {
                maxCnt = count
                maxCntIdx = index
            }
        }
        return maxCntIdx
    }

    /**
     * 最小公倍数を求める
     */
    fun calcLcm(numArray: IntArray): Double {
        val primeFactorizations = PrimeFactorizations(numArray)
        var result = 1.0
        primeFactorizations.getDivisors()
                .forEach {
                    result *= Math.pow(it.toDouble(), primeFactorizations.maxIndex(it).toDouble())
                }
        return result
    }

    /**
     * 最大公約数を求める
     */
    fun calcGcd(numArray: IntArray): Double {
        val primeFactorizations = PrimeFactorizations(numArray)
        var result = 1.0
        primeFactorizations.getDivisors()
                .forEach {
                    result *= Math.pow(it.toDouble(), primeFactorizations.minIndex(it).toDouble())
                }
        return result
    }
}