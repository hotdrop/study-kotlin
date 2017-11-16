package study

class FibonacciNumber: AbstractStudy(FibonacciNumber::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
    }

    private fun exec() {
        var currentNum = 0
        var nextNum = 1
        (0..20).forEach {
            print("$currentNum ")
            val result = currentNum + nextNum
            currentNum = nextNum
            nextNum = result
        }
        println()
    }
}