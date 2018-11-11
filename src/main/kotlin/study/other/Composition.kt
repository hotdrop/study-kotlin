package study.other

import study.AbstractStudy
import java.util.concurrent.locks.Lock
import java.util.function.BiFunction

class Composition: AbstractStudy(Composition::class.java.simpleName) {

    override fun execute() {
        super.execute()
        val targetNo = 4
        when (targetNo) {
            1 -> functionCompose()
            2 -> tesCompose()
            3 -> moreFunctionCompose()
            4 -> testes()
            else -> println("Your set number:'$targetNo' is nothing question.")
        }
    }

    private fun functionCompose() {
        fun same(elem: Int) = elem
        fun twice(elem: Int) = elem * 2
        fun trice(elem: Int) = elem * 3
        println(same(twice(trice(2))))
        operator fun <T, R, V> ((T) -> R).plus(other: (R) -> V): ((T) -> V) { return { other(this(it)) } }
        println((::trice + ::twice + ::same)(2))
    }

    private fun moreFunctionCompose() {
        fun bai(elem: Int) =  elem * 2
        fun addHelloWorld(elem: Int): String { return "Hello! num=$elem" }
        fun printEx(elem: String) { println(elem) }

        printEx(addHelloWorld(bai(2)))

        operator fun <T, R, V> ((T) -> R).plus(other: (R) -> V): ((T) -> V) { return { other(this(it)) } }
        (::bai + ::addHelloWorld + ::printEx)(2)
    }

    private fun tesCompose() {
        fun String.toMerry(): String = this + " merry"
        fun String.toMerryEx(): String = toMerry() +  { println("aaa") }

        val hello = "Hello world"
        println(hello.toMerryEx())
    }

    private fun testes() {
        fun twice(i: Int) = i * 2
        fun addHello(i: Int): String { return "  Hello! this number is $i" }
        fun print(s: String) { println(s) }

        print(addHello(twice(2)))

        operator fun <T, U, R> ((T) -> U).plus(other: ((U) -> R)): ((T) -> R) { return {other(this(it))} }
        (::twice + ::addHello + ::print)(2)
    }
}