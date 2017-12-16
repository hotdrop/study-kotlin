package study.strings

import study.AbstractStudy
import java.util.*

class SecondPage : AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 2
        when(targetNo) {
            1 -> gemStones()
            2 -> alternatingCharacters()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * AまたはBのみで構成された文字列において、AとBが交互になるよう文字を削除した場合の
     * 削除文字数を出力する。
     * 例:
     *  AAAA -> 3 AAAを削除する
     *  ABBA -> 1 Bを削除する
     */
    private fun alternatingCharacters() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val strList = mutableListOf<String>()
        (0 until cnt).forEach { strList.add(cin.next()) }

        strList.forEach {
            val delCnt = countConsecutiveChars(it)
            println(delCnt)
        }
    }

    private fun countConsecutiveChars(str: String) = (1 until str.length).count { str[it] == str[it -1] }

    /**
     * 発見した様々な岩石の構成要素を調べることとした。
     * 岩石に含まれる要素はa〜zの識別子をつけ、どの岩石にも含まれている要素はGemStoneと呼ぶことにした。
     * そのGemStoneの種類数を出力する。
     * 入力
     *  岩石の数
     *  岩石に含まれる要素
     * 例として、3つの岩石があったとする。
     *  入力
     *   3      -> 岩石の数
     *   abosd  -> 1つ目の岩石の要素
     *   jtua   -> 2つ目の岩石の要素
     *   kfatdo -> 3つ目の岩石の要素
     *  この中で毎回出てくる要素は a で、これがGemStone。したがって出力は1
     */
    private fun gemStones() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val stones = mutableListOf<String>()
        (0 until cnt).forEach { stones.add(cin.next()) }

        val result = countGemStones(stones)
        println(result)
    }

    private fun countGemStones(stones: List<String>): Int {
        val elementCountList = IntArray(27)
        stones.forEach { stone ->
            stone.toCharArray()
                    .sorted()
                    .distinct()
                    .forEach {
                        val idx = it.toInt() - 'a'.toInt()
                        elementCountList[idx] += 1
                    }
        }
        val stonesCnt = stones.size
        return elementCountList.filter { it == stonesCnt }.count()
    }
}