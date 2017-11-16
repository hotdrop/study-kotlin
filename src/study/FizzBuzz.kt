package study

class FizzBuzz: AbstractStudy(FizzBuzz::class.java.simpleName) {

    override fun execute() {
        super.execute()
        exec()
    }

    private fun exec() {
        (1..100).forEach {
            when {
                it % 15 == 0 -> println(" $it is fizz buzz. ")
                it % 3 == 0 -> println(" $it is fizz. ")
                it % 5 == 0 -> println(" $it is buzz. ")
            }
        }
    }
}