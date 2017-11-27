package study.util

import study.AbstractStudy

class ExtendCollection: AbstractStudy(ExtendCollection::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
    }

    private fun <T> MutableList<T>.replace(newElements: T) {
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

        val newVal = Elem(2, "4")
        v.replace(newVal)
        print(v)
    }

    inner class Elem(private var id: Int, var value: String) {
        override fun equals(other: Any?): Boolean = (other as Elem).id == id || super.equals(other)
    }
}