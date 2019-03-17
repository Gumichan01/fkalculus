package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.parse.Parser
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class FKalculus(val arguments: Arguments) {

    fun start() {
        var quit = false

        while (!quit) {
            print("fkalculus > ")
            val text : Option<String> = readText()

            when (text) {
                is Some -> {
                    val kalculus: Option<FKalculusAST> = parse(text.t)
                    when (kalculus) {
                        is Some -> {
                            val result: Option<Expression> = eval(kalculus.t)
                            when (result) {
                                is Some -> print(result.t)
                                is None -> println("Unrecognized command, type \"help\" to get available commands.")
                            }
                        }
                        is None -> {
                            println("Invalid command/expression to evaluate")
                        }
                    }
                }
                is None -> {
                    quit = true
                    println("Exit.")
                }
            }
        }
    }

    private fun parse(text: String): Option<FKalculusAST> {
        return Parser().parse(text)
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