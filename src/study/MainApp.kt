package study

import study.other.FibonacciNumber
import study.other.FizzBuzz
import study.other.LeapYear
import study.util.ExtendCollection

enum class RunCode {
    BasicSyntax,
    FizzBuzz,
    ExtendCollection,
    LeapYear,
    FibonacciNumber,
    ImplementationFirstPage,
    StringFirstPage,
    SortingFirstPage,
}

fun main(args: Array<String>) {

    val runCode = RunCode.SortingFirstPage

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.ImplementationFirstPage -> study.Implementation.FirstPage().run()
        RunCode.StringFirstPage -> study.strings.FirstPage().run()
        RunCode.SortingFirstPage -> study.sorting.FirstPage().run()
    }
}


