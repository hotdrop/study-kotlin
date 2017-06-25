import study.ExtendCollection
import study.FizzBuzz

/**
 * kotlin version 1.1.3
 */
fun main(args: Array<String>) {
    execFizzBuzz()
    execExtendCollection()
}

private fun execFizzBuzz() {
    println("start fizzBuzz")
    val fb = FizzBuzz()
    fb.execute()
    println("end")
}

private fun execExtendCollection() {
    println("start extend collection")
    val ec = ExtendCollection()
    ec.execute()
    println("end")
}
