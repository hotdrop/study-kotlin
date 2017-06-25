package study

class ExtendCollection: BaseStudy(ExtendCollection::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
    }

    fun <T> MutableList<T>.replace(newElements: T) {
        this.forEachIndexed { index, oldElements ->
            if(oldElements == newElements) {
                this[index] = newElements
            }
        }
    }

    private fun exec() {

        fun print(v: List<Elem>) {
            v.forEach { println(it.value) }
        }

        val v = mutableListOf(Elem(1, "1"), Elem(2, "2"), Elem(3, "3"))
        print(v)

        // 置換
        val newVal = Elem(2, "4")
        v.replace(newVal)
        print(v)
    }

    inner class Elem(var id: Int, var value: String) {
        override fun equals(other: Any?): Boolean = (other as Elem).id == id || super.equals(other)
    }
}