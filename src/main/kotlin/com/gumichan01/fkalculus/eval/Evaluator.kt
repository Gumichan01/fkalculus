package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import kotlin.math.asin

class Evaluator {

    fun eval(ast: FKalculusAST): Option<ResultValue> {
        return try {
            Some(evaluate(ast))
        } catch (e: RuntimeException) {
            println("Failed to evaluate the following instruction: ${e.message}")
            None
        }
    }

    private fun evaluate(ast: FKalculusAST): ResultValue {
        return when (ast) {
            is Help -> HelpText("TODO help")
            is Expression -> IdentifierValue("v0", evaluate(ast))
            else -> throw RuntimeException("Invalid instruction")
        }
    }

    private fun evaluate(expression: Expression): Expression {
        return when (expression) {
            is Const, is Var -> expression
            is Pi -> Const(calculatePi())
            else -> throw RuntimeException("Cannot evaluate the expression")
        }
    }

    private fun calculatePi() = asin(1.0) * 2.0
}