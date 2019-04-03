package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.parse.KalculusParser
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class FKalculus(val arguments: Arguments) {

    fun start() {
        var quit = false

        while (!quit) {
            print("fkalculus > ")
            val text: Option<String> = readText()

            if (text is Some) {
                val kalculus: Option<FKalculusAST> = parse(text.t)

                if (kalculus is Some) {
                    val result: Option<Expression> = eval(kalculus.t)
                    if (result is Some) {
                        print(result.t)
                    } else {
                        println("Unrecognized command, type \"help\" to get available commands.")
                    }
                } else {
                    println("Invalid command/expression to evaluate")
                }
            } else {
                quit = true
                println("Exit.")
            }
        }
    }

    private fun parse(text: String): Option<FKalculusAST> {
        return KalculusParser().parse(text)
    }

    private fun readText(): Option<String> {
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