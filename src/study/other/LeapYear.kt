package study.other

import study.AbstractStudy
import java.util.*

class LeapYear : AbstractStudy(LeapYear::class.java.simpleName) {

    override fun execute() {
        super.execute()
        println("Please enter number with keyboard.")

        val cin = Scanner(System.`in`)
        val yearCnt = cin.next().toInt()
        val years = mutableListOf<Int>()

        for (i in 1..yearCnt) {
            years.add(cin.nextInt())
        }

        years.forEach {
            if(isLeapYear(it)) {
                println(" $it is a leap year.")
            } else {
                println(" $it is not a leap year.")
            }
        }
    }

    private fun isLeapYear(year: Int) = when {
        year % 4 == 0 -> !(year % 100 == 0 && year % 400 != 0)
        else -> false
    }

}
