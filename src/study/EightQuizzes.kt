package study

class EightQuizzes: AbstractStudy(EightQuizzes::class.java.simpleName) {

    override fun execute() {
        one()
        two()
        three()
        four()
        five()
        six()
        seven()
        eight()
        nineTest()
    }

    private fun one() {
        println("-- Question one test start --")
        fun sum(a: Int, b: Int) = { a + b }
        println(sum(1,1))
    }

    private fun two() {
        println("-- Question two test start --")
        (1..5).forEach({ i ->
            if(i > 3) return
            println(i)
        })
        println("Done!")
    }

    private fun three() {
        // compile error. not build
        // val ___ = 010
        //println(val)
    }

    private fun four() {
        println("-- Question four test start --")
        println(B.x)
        println(C.x)
    }

    private fun five() {
        println("-- Question five test start --")
        println(Unit is Unit)
        println(null is Any)
        try {
            println(null!! is Nothing)
        } catch(npe: NullPointerException) {
            println("null!! is Nothing result: NullPointerException.")
        }
    }

    private fun six() {
        println("-- Question six test start --")
        D()
    }

    private fun seven() {
        println("-- Question seven test start --")
        println(127 as Int? === 127 as Int?)
        println(128 as Int? === 128 as Int?)
    }

    private fun eight() {
        println("-- Question eight test start --")
        println(2.5 in 1..3)
        println(3.5 in 1..3)
    }

    private fun nine(): Int = {
        println("nine")
        1
    }()

    private fun nineTest() {
        println("-- Question nine test start --")
        val nin = nine()
        println(nin) // 1
    }
}

// this open class and objects for question four.
open class A(val x: Any?)

object B: A(C)
object C: A(B)

// this class for question six.
class D {
    init {
        f()
    }

    private val a = "a"

    private fun f() {
        println(a)
    }
}