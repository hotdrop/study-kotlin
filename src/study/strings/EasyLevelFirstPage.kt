package study.strings

import org.jetbrains.annotations.Mutable
import study.AbstractStudy
import java.util.*

class EasyLevelFirstPage: AbstractStudy(EasyLevelFirstPage::class.java.simpleName) {

    override fun execute() {
        super.execute()

        //superReducedString()
        //camelCase()
        twoCharacters()
    }

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

    private fun camelCase() {
        val cin = Scanner(System.`in`)
        val letter = cin.next().toString()

        var wordCount = countWordWithCamelCase(letter)
        // This question condition: first word is lowercase
        wordCount += 1
        println(wordCount)
    }

    private fun countWordWithCamelCase(s: String) = s.count { it.isUpperCase() }

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