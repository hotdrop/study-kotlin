package study.strings

import study.AbstractStudy
import java.util.*

class SecondPage : AbstractStudy(SecondPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 5
        when(targetNo) {
            1 -> gemStones()
            2 -> alternatingCharacters()
            3 -> beautifulBinaryString()
            4 -> theLoveLetterMystery()
            5 -> anagram()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 問題文が不明すぎる。
     * 入力された文字列を2つに割って、2つの文字列がアナグラムであれば0を出力する。
     * アナグラムでなければ、分割した文字列を「何文字変更すれば同じ文字になるか」の文字数を出力する。
     * 2つに割れなければ−1を出力する。
     * と思ったが違う。アナグラムではない
     * 例:
     *  aaabbb -> 3  : 分割すると「aaa」と「bbb」になる。全文字異なるため出力は3
     *  abc    -> -1 : 分割できないので−1
     *  abba   -> 0  : 分割すると「ab」と「ba」になる。アナグラムのため出力は0
     * 例２:
     *  上記のままだとダメなケースがある。
     *  半分に割った1つ目の文字列で、2つ目の文字列と一致しないものを全て求める。
     */
    private fun anagram() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val inputLines = mutableListOf<String>()
        (0 until cnt).forEach { inputLines.add(cin.next()) }

        inputLines.forEach { line ->
            when(line.length%2) {
                0 -> {
                    val appearChar = IntArray(27)
                    // 1つ目の文字列に含まれるアルファベットとその数を全て求める。
                    line.substring(0, line.length/2).forEach { appearChar[it.toInt() - 'a'.toInt()]++ }
                    // appearCharが１以上の文字に対し、2つ目の文字列を順番に調べて同じ文字があれば−１していく
                    line.substring(line.length/2, line.length).forEach {
                        val asciiChr = it.toInt() - 'a'.toInt()
                        if(appearChar[asciiChr] > 0) {
                            appearChar[asciiChr]--
                        }
                    }
                    // appearCharの数をsumして出力する
                    println(appearChar.sum())
                }
                else -> println(-1)
            }
        }
    }

    /**
     * 回文を作成するため文字変換の最小カウントを出力する。
     * ルールは以下のとおり。
     * 1. 文字変換はアルファベットの z -> a に向かってのみできる。つまりdをcにできるがcをdにすることはできない。
     * 2. 変換可能なのはaまで（aの次はzといったルールはない）
     *
     * 例:
     *  abc -> 1回目: abb 2回目: aba よってこのケースの出力は2
     */
    private fun theLoveLetterMystery() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val loveLetter = mutableListOf<String>()
        (0 until cnt).forEach { loveLetter.add(cin.next()) }

        loveLetter.forEach {
            val conversionCnt = countCharConversionToPalindrome(it)
            println(conversionCnt)
        }
    }

    private fun countCharConversionToPalindrome(s: String) =
            (0 until (s.length)/2).sumBy { Math.abs(s[it].toInt() - s[s.length - 1 - it].toInt()) }

    private fun beautifulBinaryString() {
        val cin = Scanner(System.`in`)
        val cnt = cin.nextInt()
        val binaryStr = cin.next()

        var idx = 0
        var beautifulCnt = 0
        while(idx < binaryStr.length - 2) {
            idx += if(binaryStr[idx] == '0' && binaryStr[idx+1] == '1' && binaryStr[idx+2] == '0') {
                beautifulCnt++
                3
            } else {
                1
            }
        }
        println(beautifulCnt)
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