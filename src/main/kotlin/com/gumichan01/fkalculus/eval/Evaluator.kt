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
            is Binop -> evaluateBinop(expression)
            else -> throw RuntimeException("Cannot evaluate the expression")
        }
    }

    private fun evaluateBinop(binop: Binop): Expression {

        val operator = binop.operator
        val expr1 = evaluate(binop.expr1)
        val expr2 = evaluate(binop.expr2)

        return when (operator) {
            Plus -> sum(expr1, expr2)
            else -> throw RuntimeException("Invalid binary operator $operator")
        }
    }

    private fun sum(expr1: Expression, expr2: Expression): Expression {

        return if (expr1 is Const && expr2 is Const) Const(expr1.value + expr2.value) else Binop(Plus, expr1, expr2)
    }

    private fun calculatePi() = asin(1.0) * 2.0
}