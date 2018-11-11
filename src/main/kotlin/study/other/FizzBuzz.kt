package study.other

import study.AbstractStudy

class FizzBuzz: AbstractStudy(FizzBuzz::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
        extendFizzBuzz()
    }

    /**
     * 普通のFizzBuzz
     */
    private fun exec() {
        (1..100).forEach {
            when {
                it % 15 == 0 -> println(" $it is fizz buzz. ")
                it % 3 == 0 -> println(" $it is fizz. ")
                it % 5 == 0 -> println(" $it is buzz. ")
            }
        }
    }

    /**
     * ちょっと進んだFizzBuzz
     * 通常は3と5ですが、elementsに追加された数値と文字列も除算対象とします。
     * loopNumRangeとelementsを編集すればその指定で動きます。
     *
     * 制約条件: 既存の数値の公倍数を指定すると大きい値の方で上書きされます。
     */
    private val loopNumRange = 1..100
    private val elements = arrayListOf(
            Element(3, "Fizz"),
            Element(5, "Buzz"),
            Element(7, "Gazz"),
            Element(9, "Uwagaki")
    )

    private data class Element(val divNum: Int, val printStr: String)

    /**
     * どうやろうか迷った結果、以下に落ち着きました。
     *  予め、elementsの内容を元に全ての組み合わせを作成します。
     *  組み合わせはビット列を参考にします。
     *  例えばelementsのsizeが3なら
     *  [100] [010] [001] [110] [101] [011] [111]
     *  という7つの文字列配列を作成します。
     *  この文字列はelementsのindexに対応しており、例えば[110]ならelements[0]とelements[1]を合わせる
     *  つまり3×5=15で割り切れる場合、文字列"Fizz"+"Buzz"を出力する、とします。
     *
     */
    private fun extendFizzBuzz() {
        // 予め、全ての組み合わせを作成
        val divPatternMap = makeAllDivPattern()

        // FizzBuzzの特性から、patternのkeyで降順ソートする
        val divPatternMapDesc = divPatternMap.toSortedMap(compareByDescending { it })

        // 指定の数の中で、作成したpatternのkeyで除算できた"最初の"パターンの文字を出力
        loopNumRange.forEach { num ->
            var str = num.toString()
            for(pattern in divPatternMapDesc) {
                if(num%pattern.key == 0) {
                    str = pattern.value
                    break
                }
            }
            println(str)
        }
    }

    /**
     * 指定されたelementsの内容を元に、除算する全組み合わせをあらかじめ作成する関数です。
     *  key: 除算する値
     *  value: 出力する文字列
     */
    private fun makeAllDivPattern(): Map<Int, String> {

        val divPatternMap = mutableMapOf<Int, String>()
        val patternList = makePatternList(elements.size)

        // 0と1で構成されているpatternにおいて、1の部分のみ取得し、乗算してMapに詰める
        patternList.forEach { pattern ->
            var key = 1
            val valueSb = StringBuilder()
            for(idx in 0 until pattern.length) {
                if(pattern[idx] == '1') {
                    key *= elements[idx].divNum
                    valueSb.append(elements[idx].printStr)
                }
            }
            divPatternMap.put(key, valueSb.toString())
        }

        return divPatternMap
    }

    private fun makePatternList(len: Int): List<String> {
        val patternList = mutableListOf<String>()
        val loopCnt = (0 until len).reduce { acc, i -> acc + Math.pow(2.0, i.toDouble()).toInt() }
        (1..loopCnt).forEach {
            val binStr = Integer.toBinaryString(it)
            patternList.add(binStr.padStart(len, '0'))
        }
        return patternList
    }
}