import study.*
import study.sorting.FirstPage
import study.strings.EasyLevelFirstPage

enum class RunCode {
    BasicSyntax,
    FizzBuzz,
    ExtendCollection,
    LeapYear,
    FibonacciNumber,
    EasyLevelImpl,
    EasyLevelStringFirstPage,
    EasyLevelSortingFirstPage,
}

fun main(args: Array<String>) {

    val runCode = RunCode.FizzBuzz

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.EasyLevelImpl -> EasyLevelImpl().run()
        RunCode.EasyLevelStringFirstPage -> EasyLevelFirstPage().run()
        RunCode.EasyLevelSortingFirstPage -> FirstPage().run()
    }
}


