package study.implementation

import study.AbstractStudy

class ImplAlgorithms : AbstractStudy(ImplAlgorithms::class.java.simpleName) {

    override fun execute() {
        super.execute()

        val targetNo = 8
        when (targetNo) {
            1 -> bueautifulDaysAtTheMovies()
        }
    }

    private fun bueautifulDaysAtTheMovies() {

    }
}