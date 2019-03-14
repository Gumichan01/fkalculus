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
            print("fkalculus > ")
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
        return when (readString()) {
            is Some -> TODO("Parse the text")
            is None -> None
        }
    }

    private fun readString(): Option<String> {
        return try {
            Some(readLine()!!)
        } catch (e: NullPointerException) {
            None
        }
    }

    private fun eval(ast: FKalculusAST): Option<Expression> {
        TODO("Evaluate expression")
    }

    private fun print(expression: Expression) {
        TODO("print expression")
    }
}