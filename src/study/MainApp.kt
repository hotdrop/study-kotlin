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
    ImplementationSecondPage,
    StringFirstPage,
    StringSecondPage,
    SortingFirstPage,
    GraphTheoryFirstPage,
    DynamicProgrammingFirst,
}

fun main(args: Array<String>) {

    val runCode = RunCode.StringSecondPage

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.ImplementationFirstPage -> study.implementation.ImplementationProgramming().run()
        RunCode.ImplementationSecondPage -> study.implementation.SecondPage().run()
        RunCode.StringFirstPage -> study.strings.StringProgramming().run()
        RunCode.StringSecondPage -> study.strings.SecondPage().run()
        RunCode.SortingFirstPage -> study.sorting.SortProgramming().run()
        RunCode.GraphTheoryFirstPage -> study.graphTheory.GraphTheory().run()
        RunCode.DynamicProgrammingFirst -> study.dynamic.DynamicProgramming().run()
    }
}


