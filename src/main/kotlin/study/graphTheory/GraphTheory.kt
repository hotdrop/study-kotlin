package study.graphTheory

import study.AbstractStudy
import java.util.*

class GraphTheory : AbstractStudy(GraphTheory::class.java.simpleName) {

    override fun execute() {
        super.execute()
        val targetNo = 1
        when (targetNo) {
            1 -> journeyToTheMoon()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 月への宇宙飛行士を様々な国から募集した。
     * 今回の旅では、異なる国の者同士が2人1組のペアで活動する方針とした。
     *
     * 今、飛行士に番号をつけた状態で、同じ国の者かどうか「ペアでのみ分かっている状態」である。
     * （全体でどれだけ同じ国の人が集まっているかすぐに把握できない。）
     *
     * 上記の制約のもと異なる国の者同士のペアが最大で幾つ作れるか求める。
     *
     * 入力:
     *  宇宙飛行士の総人数 同じ国の人のペア数
     *  同じ国の人のペア(番号)
     * 例:
     *  5 3 (5は人数、3は次のLineからの入力総数)
     *  0 1 (0と1の人は同じ国)
     *  2 3 (2と3の人は同じ国)
     *  0 4 (0と4の人は同じ国）
     *      つまり、0、1、4は同じ国の人
     * 出力: 6
     */
    private fun journeyToTheMoon() {
        val cin = Scanner(System.`in`)
        val astronautsNum = cin.nextInt()
        val cnt = cin.nextInt()
        val sameCountryPair = mutableListOf<SameCountryPair>()
        (0 until cnt).forEach {
            sameCountryPair.add(SameCountryPair(cin.nextInt(), cin.nextInt()))
        }

        val allPairNum = GraphTheoryUtility.calcPairCombinationNum(astronautsNum.toLong())
        val sameCountryPairNum = calcSameCountryPairNum(astronautsNum, sameCountryPair)
        println(allPairNum - sameCountryPairNum)
    }

    /**
     * 同じ国のペアを計算する。
     *
     */
    private fun calcSameCountryPairNum(astronautsNum: Int, sameCountryPairs: List<SameCountryPair>): Long {
        // 同じ国のパイロットに番号をつける。例えば6人でsameCountryPairsが[0 1, 2 3, 0 4]の場合
        // sameCountryGroup[0]: 1
        // sameCountryGroup[1]: 1
        // sameCountryGroup[2]: 2
        // sameCountryGroup[3]: 2
        // sameCountryGroup[4]: 1
        // sameCountryGroup[5]: 0
        val sameCountryGroup = IntArray(astronautsNum)
        var maxGroupNo = 0
        sameCountryPairs.forEach {
            val num1Group = sameCountryGroup[it.num1]
            val num2Group = sameCountryGroup[it.num2]
            val groupNo = when {
                num1Group == 0 && num2Group == 0 -> ++maxGroupNo
                num1Group == 0 || num2Group == 0 -> {
                    if (num1Group != 0) num1Group else num2Group
                }
                else -> {
                    // すでに両方がいずれかのグループに属している場合、片方のグループに寄せる。
                    // ここではnum2のグループを全てnum1のグループにする。
                    sameCountryGroup.forEachIndexed { index, i ->
                        if (i == num2Group) {
                            sameCountryGroup[index] = num1Group
                        }
                    }
                    num1Group
                }
            }

            sameCountryGroup[it.num1] = groupNo
            sameCountryGroup[it.num2] = groupNo
        }

        // sameCountryGroupで同じグループ番号のカウントをとって取りうるペア数の合計を求める。
        val sameCountryGroupNum = sameCountryGroup.filter { it > 0 }.groupBy { it }
        var pairCnt = 0L
        sameCountryGroupNum.forEach { _, u -> pairCnt += GraphTheoryUtility.calcPairCombinationNum(u.size.toLong()) }
        return pairCnt
    }

    private data class SameCountryPair(val num1: Int, val num2: Int)
}