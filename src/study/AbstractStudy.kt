package study

import java.util.*

abstract class AbstractStudy(private val className: String) {

    fun run() {
        println("start $className")
        val startTime = System.currentTimeMillis()
        execute()
        val endTime = System.currentTimeMillis()
        println("end $className. ${endTime - startTime} ms")
    }

    open protected fun execute() {/* no operation */}
}