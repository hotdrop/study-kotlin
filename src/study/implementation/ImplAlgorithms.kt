package study.implementation

import study.AbstractStudy

class ImplAlgorithms : AbstractStudy(ImplAlgorithms::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 1
        when (targetNo) {
            1 -> execBeautifulDaysAtTheMovies()
        }
    }

    private fun execBeautifulDaysAtTheMovies() {
        val res = beautifulDays(20, 23, 6)
        println(res)
    }

    private fun beautifulDays(i: Int, j: Int, k: Int): Int {

        fun isBeautiful(day: Int, div: Int): Boolean =
                day.toString()
                        .reversed()
                        .toInt()
                        .let { ((day - it) % div) == 0 }

        fun countBeautifulDay(startDay: Int, endDay: Int, div: Int) =
                (startDay..endDay).count { isBeautiful(it, div) }

        return countBeautifulDay(i, j, k)
    }
}