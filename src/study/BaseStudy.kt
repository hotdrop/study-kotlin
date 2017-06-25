package study

abstract class BaseStudy(val className: String) {

    fun run() {
        println("start $className")
        execute()
        println("end $className")
    }

    open protected fun execute() {/* no operation */}
}