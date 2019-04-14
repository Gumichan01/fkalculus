package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.ast.HelpText
import com.gumichan01.fkalculus.ast.IdentifierValue
import com.gumichan01.fkalculus.ast.ResultValue
import com.gumichan01.fkalculus.eval.Evaluator
import com.gumichan01.fkalculus.parse.KalculusParser
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

// TODO 1 - Test the application and publish v0.0.1
// TODO 1bis - Set licence and make the repository public
class FKalculus(val arguments: Arguments) {

    fun start() {
        var quit = false

        while (!quit) {
            print("fkalculus > ")
            val text: Option<String> = readText()

            if (text is Some) {
                val kalculus: Option<FKalculusAST> = parse(text.t)

                if (kalculus is Some) {
                    val result: Option<ResultValue> = eval(kalculus.t)
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

    // TODO 2 - Don't accept empty lines
    // TODO 3 - Implement a list of previous commands
    private fun readText(): Option<String> {
        return try {
            Some(readLine()!!)
        } catch (e: NullPointerException) {
            None
        }
    }

    private fun eval(ast: FKalculusAST): Option<ResultValue> {
        return Evaluator().eval(ast)
    }

    private fun print(result: ResultValue) {
        when (result) {
            is HelpText -> println(result.text)
            is IdentifierValue -> result.run { println("$identifier = $value") }
        }
    }
}