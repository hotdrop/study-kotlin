package study

import java.util.*

class BasicSyntax : AbstractStudy(BasicSyntax::class.java.simpleName) {

    override fun execute() {
        super.execute()
        studyFor()
        studyNull()
        studyDataClass()
        scopeFunction()
    }

    private fun studyFor() {
        val array = arrayOf(1,2,3,4,5)
        print("  forを使った結果=")
        for(i in array) {
            print(i)
        }
        println("")
        print("  forEachを使った結果=")
        array.forEach { print(it) }
        println("")
        print("  forを使った逆順=")
        for(i in 5 downTo 0) {
            print(i)
        }
        println("")
        print("  forEachを使った逆順=")
        (0..5).reversed().forEach { print(it) }
    }

    private fun studyNull() {
        var nullableStr: String? = "aiueo"
        var length = nullableStr?.length
        println("  5になるはず。 $length")

        nullableStr = null
        length = nullableStr?.length
        println("  nullになるはず。 $length")

        var res = if(nullableStr != null) nullableStr.length else -1
        println("  結果は−1になるはず。 $res")

        // エルビス演算子使う
        res = nullableStr?.length ?: -1
        println("  エルビス演算子使ったver。結果は−1になるはず。 $res")
    }

    private fun studyDataClass() {
        val myData = Person(1, "田中　太郎")
        val(id, name) = myData
        println("  idは $id 、 nameは $name です。")
    }

    private data class Person(val id: Int, var name: String)

    private fun scopeFunction() {
        val letHoge = "hoge".let {
            it.toUpperCase()
            //this: BasicSyntax
        }
        val applyHoge = "hoge".apply {
            toUpperCase()
            // this: String
        }
        val runHoge: String? = getHogeStr(false)
        val test = runHoge?.run {
            print("in run scope function.")
            val upperHoge = this.toUpperCase()
            println(" this = $upperHoge ")
            upperHoge
        }
        val withHoge = with("hoge") {
            toUpperCase()
            1
        }
        println("withHoge = $withHoge ")

        val alsoHoge = "hoge".also { str ->
            println("alsoHoge inside = ${str.toUpperCase()} ")
        }
        println("alsoHoge outside = $alsoHoge ")

        println("letの場合: $letHoge applyの場合 $applyHoge")
    }

    private fun getHogeStr(nullFlag: Boolean): String? {
        return if(nullFlag) null else "hoge"
    }

    private fun parseNum(number: Int) =
        when(number) {
            1 -> "one"
            2 -> "two"
            else -> null
        }

    private fun isLatinUppercase(c: Char) = c in 'A'..'Z'

    private fun String.isPhoneNumber(s: String) =
            s.length == 7 && s.all{ it.isDigit() }

    private fun find(name: String) = "1234567"

    private fun namedNum(): Pair<Int, String> = 1 to "one"

    private fun namedNumTest() {
        val (number, name) = namedNum()
    }

    private fun birthDay(person: Person) =
            person.copy(name = person.name + "copy")

    private fun updateProgress(value: Int) {
        val newValue = value.coerceIn(0, 100)
    }
    /**
     * N x Nのマトリックスの左上から斜めがけ、右上から斜めがけし
     * 差し引いた数の絶対値を表示する
     */
    fun testProblem3() {
        val cin = Scanner(System.`in`)
        val matrixCnt = cin.next().toInt()
        val matrixList = mutableListOf<Int>()
        for(i in 1..matrixCnt*matrixCnt) {
            matrixList.add(cin.nextInt())
        }

        // 左からの斜め掛け
        // 最初は1でmatrix+1インデックスを取得する
        val primaryDiagonal = matrixList.filterIndexed { index, _ -> index == 0 || index%(matrixCnt + 1) == 0 }.sum()
        // 右からの斜めがけ
        // 最初はmatrixでmatrix−1インデックスを取得していく。これだと最初と最後の値も取れてしまうためindexの範囲を決める
        val secondaryDiagonal = matrixList.filterIndexed { index, _ -> (index > 0 && index < matrixCnt*matrixCnt - 1) && index%(matrixCnt - 1) == 0 }.sum()
        val result = Math.abs(primaryDiagonal - secondaryDiagonal)
        print("$result")
    }
}