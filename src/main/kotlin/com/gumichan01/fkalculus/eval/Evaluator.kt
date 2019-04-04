package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class Evaluator {

    fun eval(ast: FKalculusAST): Option<ResultValue> {
        return try {
            Some(evaluateAst(ast))
        } catch (e: RuntimeException) {
            println("Failed to evaluate the following instruction: ${e.message}")
            None
        }
    }

    private fun evaluateAst(ast: FKalculusAST): ResultValue {
        return when (ast) {
            is Help -> HelpText("TODO help")
            is Expression -> evaluate(ast)
            else -> throw RuntimeException("Invalid instruction")
        }
    }

    private fun evaluate(expression: Expression): ResultValue {
        return when (expression) {
            is Const -> IdentifierValue("v0", expression)
            else -> throw RuntimeException("Cannot evaluate the expression")
        }
    }
}