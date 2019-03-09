package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.arg.ArgumentParser

fun main(args: Array<String>) {
    val arguments = ArgumentParser().parse(args)
    FKalculus(arguments).start()
}