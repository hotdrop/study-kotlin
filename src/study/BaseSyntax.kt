package study

class BaseSyntax: BaseStudy(BaseSyntax::class.java.simpleName) {

    override fun execute() {
        super.execute()
        studyFor()
        studyNull()
        studyDataClass()
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
        println("  長さは5になるはず。 $length")

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
}