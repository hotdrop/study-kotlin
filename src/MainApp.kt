import study.*
import study.strings.EasyLevelFirstPage

enum class RunCode {
    BasicSyntax,
    FizzBuzz,
    ExtendCollection,
    LeapYear,
    FibonacciNumber,
    EasyLevelImpl,
    EasyLevelStringFirstPage,
}

fun main(args: Array<String>) {

    val runCode = RunCode.EasyLevelStringFirstPage

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.EasyLevelImpl -> EasyLevelImpl().run()
        RunCode.EasyLevelStringFirstPage -> EasyLevelFirstPage().run()
    }
}


