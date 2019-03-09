package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.arg.ArgumentParser

fun main(args: Array<String>) {
    println("Hello World!")
    val arguments = ArgumentParser().parse(args)
    println(arguments)
}