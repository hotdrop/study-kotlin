package study.strings

import study.AbstractStudy
import java.util.*

class EasyLevelFirstPage: AbstractStudy(EasyLevelFirstPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 10
        when(targetNo) {
            1 -> superReducedString()
            2 -> camelCase()
            3 -> twoCharacters()
            4 -> caesarCipher()
            5 -> marsExploration()
            6 -> hackerRank()
            7 -> pangrams()
            8 -> weightedUniformStrings()
            9 -> separateTheNumbers()
            10 -> funnyString()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    /**
     * 入力文字列sとその逆順のrについて
     *  sのi文字目とi-1文字目の差=rのi文字目とi-1文字目の差
     * であれば「Funny」、そうでなければ「Not Funny」と出力する
     */
    private fun funnyString() {
        val cin = Scanner(System.`in`)
        val cnt = cin.next().toInt()
        val sArr = mutableListOf<String>()
        (0 until cnt).forEach { sArr.add(cin.next()) }

        sArr.forEach {
            if(isFunnyString(it)) println("Funny") else println("Not Funny")
        }
    }

    private fun isFunnyString(s: String) =
        (1 until s.length).all {
            Math.abs(s[it].toInt() - s[it-1].toInt()) == Math.abs(s[s.length - it].toInt() - s[s.length - it - 1].toInt())
        }

    /**
     * 入力文字列sは全て数値であることを条件に、sの連続した数値が1ずつインクリメント
     * されていれば美しい文字列と判断し、YESとその最初の文字を、されていなければNOを出力する。
     * ただし、0パディングされていたり0サプレスされている場合は美しい文字列と判断しない。
     * 例:
     *  9899100 -> 98, 99, 100 となり「YES 98」と出力する。
     *  010203 -> 「NO」と出力する。
     *  204206207208 -> 「NO」と出力する。
     * Note
     *  4バイトつまり2,147,483,648を超える数値の文字列が指定される可能性に考慮する。
     */
    private fun separateTheNumbers() {
        val cin = Scanner(System.`in`)
        val cnt = cin.next().toInt()
        val queries = mutableListOf<String>()
        (0 until cnt).forEach {
            queries.add(cin.next())
        }

        queries.map { checkBeautifulString(it) }.forEach {
            if(it.isNullOrEmpty()) {
                println("NO")
            } else {
                println("YES $it")
            }
        }
    }

    /**
     * 条件に一致した場合は最初の文字数値を返す。
     * 一致しない場合はnullを返す。
     */
    private fun checkBeautifulString(s: String): String? {
        // 1文字のみまたは先頭が0は除外
        if(s.length == 1 || s.startsWith('0')) {
            return null
        }
        (1..(s.length/2)).forEach {
            // 最初のsplit文字を取得する
            val currentStr = s.substring(0, it)
            if(isBeautifulString(s, currentStr)) {
                return currentStr
            }
        }
        return null
    }

    private fun isBeautifulString(s: String, currentStr: String): Boolean {
        // 最初にまず先頭と次の文字が条件を満たしているかチェックする。ここで合ってなければ処理の無駄なので
        val nextStr = (currentStr.toLong() + 1).toString()
        if(!s.startsWith(currentStr + nextStr)) {
            return false
        }
        // 最初の文字から、正解となる文字列を生成する
        val sb = StringBuilder()
        var currentNumber = currentStr.toLong()
        while(sb.toString().length < s.length) {
            sb.append(currentNumber.toString())
            currentNumber++
        }
        // 文字列が完全一致すればOK
        val makeExpectedString = sb.toString()
        return s == makeExpectedString
    }

    /**
     * 小文字のa〜zにそれぞれ1〜26までのweightを割り当てる。
     * ある文字列sに対し、uniformとして定義される形でそれぞれweightを割り当て、そのweightが入力された数値と
     * 一致すれば「Yes」、一致しなければ「No」と出力する。
     * 【Note】
     * uniformとは単一または連続した同一文字を示す。
     * 「c、ccc、a」はuniformであり、「bc、cd」などはuniformではない。
     * この場合、c=3、ccc=3×3=9、a=1 となる。
     * 厄介なのは連続した文字の場合、その組み合わせがweight対象になる。
     * 文章だと意味不明なので例で示す。
     * Input
     *  abccddde       -> ある文字s
     *  6              -> 入力weight数
     *  1 3 12 5 9 10  -> 入力weight
     * Output
     *  Yes Yes Yes Yes No No
     *
     *  【考え方】
     *  abccdddeを以下のようにuniformに分解する。
     *  [uniform : weight]
     *    a      : 1
     *    b      : 2
     *    c      : 3
     *    cc     : 6
     *    d      : 4
     *    dd     : 8
     *    ddd    : 12
     *    e      : 5
     *   入力値のうち、「1 3 12 5」はweightに存在するためYES、9と10はないためNOとなる。
     */
    private fun weightedUniformStrings() {
        val cin = Scanner(System.`in`)
        val s = cin.next()
        val cnt = cin.next().toInt()
        val inputWeight = IntArray(cnt)
        (0 until cnt).forEach {
            inputWeight[it] = cin.nextInt()
        }

        val weightArray = createWeightArray(s)
        inputWeight.forEach{
            if(containsWeight(it, weightArray)) {
                println("Yes")
            } else {
                println("No")
            }
        }
    }

    private fun createWeightArray(s: String): IntArray {
        // charとcountのMap作成
        val charCountMap = mutableMapOf<Char, Int>()
        var count = 1
        // kotlinっぽくないが・・
        s.forEachIndexed { index, c ->
            // 次の文字があってかつ同じ文字の場合
            if(index + 1 < s.length && c == s[index + 1]) {
                count++
            } else {
                // その文字がすでにありcountが大きい場合は置き換える
                if(charCountMap.containsKey(c)) {
                    val charCount = charCountMap.getOrDefault(c,1)
                    if(charCount < count) {
                        charCountMap.replace(c, count)
                    }
                } else {
                    charCountMap.put(c, count)
                }
                count = 1
            }
        }

        // 最初はMapでやっていたが、テストケースの中に巨大な文字列があったためIntArrayで計算したほうが早いと判断した
        // 1〜26の添え字をweightとして使用するためsizeは27とする
        val weightArray = IntArray(27)
        charCountMap.forEach {
            val index = it.key.toInt() - 96
            weightArray[index] = it.value
        }
        return weightArray
    }

    /**
     * 指定された値のweightがあるか計算する。
     *
     * この判定をするにあたって、必ずいずれかのweightの倍数になっているはずなので
     * 除算できてかつその値がcharの連続出現数未満であればweightありと判断して良いと考える。
     * 計算方法は色々と迷った結果、これに落ち着いた。
     *
     */
    private fun containsWeight(inputWeight: Int, weightArray: IntArray) =
            weightArray
                .filterIndexed {index, charCount ->
                    charCount > 0 &&
                    inputWeight%index == 0 && (inputWeight/index) <= charCount
                }.any()

    /**
     * Pangramかどうか判定する
     * Pangramとはアルファベットの全ての文字を少なくとも1回使用して構成された文章
     *
     */
    private fun pangrams() {
        val cin = Scanner(System.`in`)
        val s = cin.nextLine()

        if(isPangrams(s)) {
            println("pangram")
        } else {
            println("not pangram")
        }
    }

    private val startCharIndex = 'A'.toInt()
    private val endCharIndex = 'Z'.toInt()
    private fun isPangrams(s: String): Boolean {
        val charArr = s.toUpperCase()
                        .filter { it.toInt() in startCharIndex..endCharIndex   }
                        .toCharArray()
                        .sorted()
                        .distinct()
        var alphabetIndex = startCharIndex

        charArr.asSequence()
                .takeWhile { it.toInt() == alphabetIndex }
                .forEach { alphabetIndex++ }
        return (alphabetIndex == endCharIndex + 1)
    }

    /**
     * 与えられた文字列sに対し、「hackerrank」という単語の1つ1つの文字がこの
     * 順番で出現するかチェックし、成立すればYES、しなければNOと入力する。
     * 文字列sは複数個まとめて入力できる。
     * 例:
     *  入力文字:
     *      hereiamstackerrank
     *      hackerworld
     *      hackerhackerrankrank
     *  出力:
     *      YES
     *      NO
     *      YES
     */
    private fun hackerRank() {
        val cin = Scanner(System.`in`)
        val queryCnt = cin.next().toInt()
        val queries = mutableListOf<String>()
        (1..queryCnt).forEach {
            queries.add(cin.next())
        }

        queries.map { isAllAppearHackerRank(it) }
                .forEach { if(it) println("YES") else println("NO") }
    }

    private fun isAllAppearHackerRank(s: String): Boolean {
        val words = "hackerrank"
        var appearIndex = 0
        var isSuccess = false
        s.forEach {
            if(!isSuccess && it == words[appearIndex]) {
                appearIndex++
                if(appearIndex == words.length) {
                    isSuccess = true
                }
            }
        }
        return isSuccess
    }

    /**
     * 与えられた文字列sに対し、SOSの変換数を求める。
     * これは火星から送信されたSOSの文字のうち宇宙線によって変更されてしまった、という想定のようだ。
     * メッセージはSOSの連続で、必ず大文字、メッセージ長は必ず3の倍数。
     * 例
     *  入力文字: SOSSPSSQSSOR
     *  出力: 3
     *  期待した信号は「SOSSOSSOSSOS」とSOS4回の12文字である。受信した時は「SOSS P SS Q SSO R」
     *  とPとQとRがおかしくなっている。つまり変換されてしまった文字は3つ
     *
     */
    private fun marsExploration() {
        val cin = Scanner(System.`in`)
        val message = cin.next()

        // 3文字ごとにindexでループ
        var index = 0
        var count = 0
        while(index < message.length - 2) {
            if(message[index] != 'S') count++
            if(message[index + 1] != 'O') count++
            if(message[index + 2] != 'S') count++
            index += 3
        }
        println(count)
    }

    /**
     * 暗号
     * 与えられた文字列sに対し、keyの数だけASCII CODEに従ってシフトして表示する。
     * シフト対象は A-Z 及び a-z である。
     * keyは0〜100まで指定が可能であり、Zないしzの次はAないしaと巡回する。
     * 文字列sは任意の英数字。記号もあり。
     *  例:
     *   文字列: abz-AZ
     *   key: 2
     *   結果: cdb-CB
     *
     *   文字列: 159357lcfd
     *   key: 98
     *   結果: 159357fwzx
     */
    private val asciiCodeA = 65
    private val asciiCodeZ = 90
    private val asciiCodea = 97
    private val asciiCodez = 122

    private fun caesarCipher() {
        val cin = Scanner(System.`in`)

        val len = cin.next().toInt()
        val s = cin.next()
        val key = cin.next().toInt()

        val result = encryptCaesarCipher(s, key)
        println(result)
    }

    private fun encryptCaesarCipher(s: String, keyNum: Int) = s.map {
        val charCode = it.toInt()
        when (charCode) {
            in asciiCodeA..asciiCodeZ -> shiftAsciiCode(charCode, keyNum, asciiCodeA, asciiCodeZ).toChar()
            in asciiCodea..asciiCodez -> shiftAsciiCode(charCode, keyNum, asciiCodea, asciiCodez).toChar()
            else -> it
        }
    }.joinToString("")

    private fun shiftAsciiCode(charCode: Int, shiftNum: Int, rangeFirstAsciiCode: Int, rangeLastAsciiCode: Int): Int {
        val shiftNumInRange = shiftNum%(rangeLastAsciiCode - rangeFirstAsciiCode + 1)
        return when {
            shiftNumInRange == 0 -> charCode
            charCode + shiftNumInRange <= rangeLastAsciiCode -> charCode + shiftNumInRange
            else -> (rangeFirstAsciiCode - 1) + ((charCode + shiftNumInRange) - rangeLastAsciiCode)
        }
    }

    /**
     * 連続しない2種類の文字が最も長いものを抜き出してその長さを求める。
     * 文章だと分かりづらいため例を示す。
     *  元の文  abcdcabaeeba
     *  1. a,cを選んだ場合 = acacaa  これはaが連続しているためNG
     *  2. b,cを選んだ場合 = bccbb  これも連続いているためNG
     *  3. a,bを選んだ場合 = abababa  これはOK。長さは7
     *  4. c,dを選んだ場合 = cdc これはOK。長さは3
     *  ・・・
     *  とやっていき、一番長いのはa,bを選んだ場合で長さは7となる。
     */
    private fun twoCharacters() {
        val cin = Scanner(System.`in`)
        val strLength = cin.next().toInt()
        val str = cin.next().toString()

        // 文字をKey、文字数をValueとしてMapを作成する
        // このMapは答えを導きだすためのものであるため、最初から隣り合っている文字は不要
        val eraseStr = eraseContinuousChar(str)
        val charMap = makeCharMap(eraseStr)

        // 頻度の高い順にソートしてListにする
        val sortedCharArray = charMap.toList().sortedByDescending { (_, value) -> value }

        // 判定した中で最も長い値
        var maximumLength = 0
        sortedCharArray.filterIndexed { index, _ -> index < sortedCharArray.size - 1 }
                .forEachIndexed { index, pair ->

                    // 次以降の文字を全て確認する。
                    // これだと計算量が厳しい。もっと効率的な方法はないのか
                    (index + 1 until sortedCharArray.size).forEach {
                        val nextPair = sortedCharArray[it]
                        // 2つの文字の合計値が、現在の「最も大きい文字数」より大きければ判定を行う
                        val currentExpectedMaxLength = pair.second + nextPair.second
                        if(maximumLength < currentExpectedMaxLength) {
                            val checkOk = checkNotConsecutive(str, pair.first, nextPair.first)
                            if(checkOk) {
                                maximumLength = currentExpectedMaxLength
                            }
                        }
                    }

        }
        println(maximumLength)
    }

    private fun makeCharMap(s: String): MutableMap<Char, Int> {
        val charMap = mutableMapOf<Char, Int>()
        s.forEach { c ->
            if(charMap.containsKey(c)) {
                charMap[c]?.let { charMap.replace(c, it + 1) }
            } else {
                charMap.put(c, 1)
            }
        }
        return charMap
    }

    private fun checkNotConsecutive(original: String, oneChar: Char, twoChar: Char): Boolean {
        var appearOneChar = false
        var appearTwoChar = false
        original.forEach {
            when (it) {
                oneChar -> {
                    if(appearOneChar) return false
                    appearOneChar = true
                    appearTwoChar = false
                }
                twoChar -> {
                    if(appearTwoChar) return false
                    appearOneChar = false
                    appearTwoChar = true
                }
            }
        }
        return true
    }

    /**
     * キャメルケースで書かれたletterの単語数を数える。
     * ただし先頭１文字は必ず小文字となるがこれも単語として数える。
     * 例えば thisLetterIsTest.
     * と入力があった場合、単語は this Letter Is Test の4つ
     */
    private fun camelCase() {
        val cin = Scanner(System.`in`)
        val letter = cin.next().toString()

        var wordCount = countWordWithCamelCase(letter)
        // This question condition: first word is lowercase
        wordCount += 1
        println(wordCount)
    }

    private fun countWordWithCamelCase(s: String) = s.count { it.isUpperCase() }

    /**
     * 入力された文字列sに対し、隣り合う文字を全て除去して出力する。
     * 除去した結果、空になったら「Empty String」と出力する。
     * 例
     *  1. baeeabdd → 連続したee、ddが対象。除去する
     *  2. baab → 除去した結果、今度はaaが連続したため除去する
     *  3. bb → 除去した結果、今度はbbが連続したため除去
     *  4. Empty String → 結果、空文字になったため「Empty String」と表示する
     *
     */
    private fun superReducedString() {
        val cin = Scanner(System.`in`)
        val s = cin.next().toString()

        val result = reducedString(s)
        if(result.isNotEmpty()) println(result) else println("Empty String")
    }

    private fun reducedString(s: String): String {
        var reduceStr = s
        while(hasContinuousChar(reduceStr)) {
            reduceStr = eraseContinuousChar(reduceStr)
        }
        return reduceStr
    }

    private fun hasContinuousChar(s: String): Boolean {
        var prevChar = ' '
        s.forEach { if(it == prevChar) return true else prevChar = it }
        return false
    }

    private fun eraseContinuousChar(s: String): String {
        var index = 0
        var newStr = ""
        var skipChar = false
        while (index < s.length) {
            when {
                index == s.length - 1 -> newStr += s[index]
                s[index] == s[index + 1] -> skipChar = true
                else -> newStr += s[index]
            }
            index += if(skipChar) 2 else 1
            skipChar = false
        }
        return newStr
    }
}