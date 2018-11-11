package study.implementation

/**
 * 素因数分解及び約数、指数を算出するユーティリティクラス
 * 最大公約数と最小公倍数を求める際などで使用
 */
class PrimeFactorizations(numArray: IntArray) {

    private val divisorIndexMap: List<Map<Int, Int>> = numArray.map { calc(it) }

    private fun MutableMap<Int, Int>.exPutOrAddCount(key: Int) {
        if(this.containsKey(key)) {
            var count = this[key] ?: 0
            this.replace(key, ++count)
        } else {
            this.put(key, 1)
        }
    }

    /**
     * 指定した値を素因数分解する。
     * 素因数分解はTrial division法で行い、その結果をMapで取得する。
     * Keyは割った素数N、valueは指数とする。
     */
    fun calc(num: Int): MutableMap<Int, Int> {

        var temp = num
        var divNum = 2
        val resultMap = mutableMapOf<Int, Int>()

        // 素因数分解アルゴリズムで最も単純なTrial division法（２から順にルートNまでの素数で割っていく）を採用する。
        // そのため、ループは「合成数xはNの2乗より大きくはならない」という性質を条件としている。
        while(divNum * divNum <= num) {
            if(temp%divNum == 0) {
                resultMap.exPutOrAddCount(divNum)
                temp /= divNum
            } else {
                divNum++
            }
        }
        // 最後に割り切れなかった素数を追加する
        if(temp != 1) {
            resultMap.put(temp, 1)
        }
        return resultMap
    }

    /**
     * 重複を除いた約数をリスト形式で返す。
     */
    fun getDivisors() = divisorIndexMap.flatMap { it.keys.toMutableList() }.distinct()

    /**
     * 指定された約数において、最も大きい指数を返す。
     * divisorは必ず１つ存在するはずなので、もしなければNPEを発生させる・・
     */
    fun maxIndex(divisor: Int): Int =
            divisorIndexMap.map { it.getOrDefault(divisor, 0) }.max()!!

    /**
     * 指定された約数において、最も小さい指数を返す。
     * divisorは必ず1つ存在するはずなので、もしなければNPEを発生させる・・もっと良い方法が知りたい・・
     */
    fun minIndex(divisor: Int): Int =
            divisorIndexMap.map { it.getOrDefault(divisor, 0) }.min()!!
}