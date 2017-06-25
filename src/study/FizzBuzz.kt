package study

class FizzBuzz: BaseStudy(FizzBuzz::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
    }

    fun Int.divides(i: Int): Boolean {
        return (this % i == 0)
    }

    fun Int.multiDivides(i: Int, j: Int): Boolean {
        return (this % i == 0 && this % j == 0)
    }

    private fun exec() {
        (1..100).forEach {
            when {
                it.multiDivides(3, 5) -> println("fizz buzz")
                it.divides(3) -> println("fizz")
                it.divides(5) -> println("buzz")
                else -> println(it)
            }
        }
    }
}