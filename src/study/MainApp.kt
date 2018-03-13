package study

import study.dynamic.DynamicProgramming
import study.graphTheory.GraphTheory
import study.implementation.ImplementationProgramming
import study.network.HttpConnect
import study.other.FibonacciNumber
import study.other.FizzBuzz
import study.other.LeapYear
import study.sorting.SortProgramming
import study.strings.StringProgramming
import study.util.ExtendCollection

enum class RunCode {
    BasicSyntax,
    FizzBuzz,
    ExtendCollection,
    LeapYear,
    FibonacciNumber,
    ImplementationProgramming,
    StringProgramming,
    SortProgramming,
    GraphTheory,
    DynamicProgramming,
    Network,
}

fun main(args: Array<String>) {

    val runCode = RunCode.Network

    when(runCode) {
        RunCode.BasicSyntax -> BasicSyntax().run()
        RunCode.FizzBuzz -> FizzBuzz().run()
        RunCode.ExtendCollection -> ExtendCollection().run()
        RunCode.LeapYear -> LeapYear().run()
        RunCode.FibonacciNumber -> FibonacciNumber().run()
        RunCode.ImplementationProgramming -> ImplementationProgramming().run()
        RunCode.StringProgramming -> StringProgramming().run()
        RunCode.SortProgramming -> SortProgramming().run()
        RunCode.GraphTheory -> GraphTheory().run()
        RunCode.DynamicProgramming -> DynamicProgramming().run()
        RunCode.Network -> HttpConnect().run()
    }
}


