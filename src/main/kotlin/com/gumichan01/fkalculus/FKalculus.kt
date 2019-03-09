package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class FKalculus(val arguments: Arguments) {

    fun start() {
        var quit = false
        while (!quit) {
            val kalculus: Option<FKalculusAST> = read()
            when (kalculus) {
                is Some -> {
                    val result: Option<Expression> = eval(kalculus.t)
                    when (result) {
                        is Some -> print(result.t)
                        is None -> println("Unrecognized command, type \"help\" to get available commands.")
                    }
                }
                is None -> {
                    quit = true
                    println("Exit.")
                }
            }
        }
    }

    private fun read(): Option<FKalculusAST> {
        TODO("not implemented")
    }

    private fun readString(): Option<String> {
        TODO("not implemented")
    }

    private fun eval(ast: FKalculusAST): Option<Expression> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun print(expression: Expression): Unit {
        TODO("print expression")
    }
}