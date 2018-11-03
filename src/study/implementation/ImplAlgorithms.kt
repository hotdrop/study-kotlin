package study.implementation

import study.AbstractStudy

class ImplAlgorithms : AbstractStudy(ImplAlgorithms::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 3
        when (targetNo) {
            1 -> execBeautifulDaysAtTheMovies()
            2 -> execViralAdvertising()
            3 -> execSaveThePrisoner()
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

    private fun execViralAdvertising() {
        val res = viralAdvertising(10)
        println(res)
    }

    private fun viralAdvertising(n: Int): Int {
        var sharedNum = 5
        var liked: Int
        var cumulative = 0
        (1..n).forEach {
            liked = sharedNum / 2
            cumulative += liked
            sharedNum = liked * 3
        }
        return cumulative
    }

    fun execSaveThePrisoner() {
        saveThePrisoner(5, 2, 1).run { if (this != 2) throw IllegalStateException("case1 $this != 2") }
        saveThePrisoner(7, 19, 2).run { if (this != 6) throw IllegalStateException("case2 $this != 6") }
        saveThePrisoner(100, 1, 10).run { if (this != 10) throw IllegalStateException("case3 $this != 10") }
        saveThePrisoner(5, 11, 1).run { if (this != 1) throw IllegalStateException("case4 $this != 1") }
        saveThePrisoner(8, 3, 1).run { if (this != 3) throw IllegalStateException("case5 $this != 3") }
        saveThePrisoner(1, 5938385, 1).run { if (this != 1) throw IllegalStateException("case6 $this != 1") }
        saveThePrisoner(784893322, 849791807, 360911386).run { if (this != 425809870) throw IllegalStateException("case7 $this != 425809870") }
        saveThePrisoner(499999999, 999999997, 2).run { if (this != 499999999) throw IllegalStateException("case8 $this != 499999999") }
        saveThePrisoner(499999999, 999999998, 2).run { if (this != 1) throw IllegalStateException("case9 $this != 1") }
        saveThePrisoner(999999999, 999999999 ,1).run { if (this != 999999999) throw IllegalStateException("case10 $this != 999999999") }
        saveThePrisoner(352926151, 380324688, 94730870).run { if (this != 122129406) throw IllegalStateException("case11 $this != 122129406") }
        saveThePrisoner(649320641, 742902564, 647542323).run { if (this != 91803604) throw IllegalStateException("case12 $this != 91803604") }
    }

    private fun saveThePrisoner(n: Int, m: Int, s: Int): Int =
        when {
            m == 1 || n == 1 -> s
            s == 1 && n >= m -> m
            else -> {
                (m % n).let { rem ->
                    (rem + (s - 1)).let {
                        if (it <= n) {
                            it
                        } else {
                            it - n
                        }
                    }
                }
            }
        }
}