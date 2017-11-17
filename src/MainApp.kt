import study.*

enum class RunCode {
    BasicSyntax,
    FizzBuzz,
    ExtendCollection,
    LeapYear,
    FibonacciNumber,
    EasyLevelImpl
}

fun main(args: Array<String>) {

    val runCode = RunCode.EasyLevelImpl

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.EasyLevelImpl -> EasyLevelImpl().run()
    }
}


