package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import kotlin.math.asin

class Evaluator {

    fun eval(ast: FKalculusAST): Option<ResultValue> {
        return try {
            Some(evaluateInstruction(ast as Instruction))
        } catch (e: RuntimeException) {
            println("Failed to evaluate the following instruction: ${e.message}")
            None
        }
    }

    private fun evaluateInstruction(instruction: Instruction): ResultValue {
        return when (instruction) {
            is Help -> HelpText("TODO help")
            is Expression -> IdentifierValue("v0", evaluateExpression(instruction))
            else -> throw RuntimeException("Invalid instruction")
        }
    }

    private fun evaluateExpression(expression: Expression): Expression {
        return when (expression) {
            is Const, is Var -> expression
            is Pi -> Const(calculatePi())
            is Binop -> evaluateBinop(expression)
            else -> throw RuntimeException("Cannot evaluateExpression the expression")
        }
    }

    private fun evaluateBinop(binop: Binop): Expression {
        return binop.run {
            val value1 = evaluateExpression(expr1)
            val value2 = evaluateExpression(expr2)
            if (value1 is Const && value2 is Const) {
                applyBinop(operator, value1, value2)
            } else {
                Binop(operator, value1, value2)
            }
        }
    }

    private fun applyBinop(operator: Operator, const1: Const, const2: Const): Const {
        return Const(when (operator) {
            Plus -> const1.value + const2.value
            Minus -> const1.value - const2.value
            Mult -> const1.value * const2.value
            Div -> const1.value / const2.value
            else -> throw RuntimeException("Unsupported operation: $operator")
        })
    }

    private fun calculatePi() = asin(1.0) * 2.0
}