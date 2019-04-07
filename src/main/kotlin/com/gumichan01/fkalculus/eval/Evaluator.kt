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

        return binop.run {

            val value1 = evaluate(expr1)
            val value2 = evaluate(expr2)
            if (value1 is Const && value2 is Const) {
                applyBinop(operator, value1, value2)
            } else {
                Binop(binop.operator, value1, value2)
            }
        }
    }

    private fun applyBinop(operator: Operator, value1: Const, value2: Const): Const {
        return Const(when (operator) {
            Plus -> value1.value + value2.value
            Minus -> value1.value - value2.value
            else -> throw RuntimeException("Unsupported operation: $operator")
        })
    }

    private fun calculatePi() = asin(1.0) * 2.0
}