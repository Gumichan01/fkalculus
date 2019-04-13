package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import kotlin.math.*

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
            is Pi -> Const(PI)
            is Exp1 -> Const(E)
            is Binop -> evaluateBinop(expression)
            is Sqrt -> evaluateSqrt(expression)
            is Expo -> evaluateExpo(expression)
            is Ln -> evaluateLn(expression)
            else -> throw RuntimeException("Cannot evaluateExpression the expression")
        }
    }

    private fun evaluateLn(naturalLogFunCall: Ln): Expression {
        return naturalLogFunCall.run {
            val result = evaluateExpression(expr)
            if (result is Const) {
                Const(ln(result.value))
            } else {
                Ln(result)
            }
        }
    }

    private fun evaluateExpo(expFunCall: Expo): Expression {
        return expFunCall.run {
            val result = evaluateExpression(expr)
            if (result is Const) {
                Const(exp(result.value))
            } else {
                Expo(result)
            }
        }
    }

    private fun evaluateSqrt(sqrtFunCall: Sqrt): Expression {
        return sqrtFunCall.run {
            val result = evaluateExpression(this.expr)
            if (result is Const) {
                Const(sqrt(result.value))
            } else {
                Sqrt(result)
            }
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
            Pow -> Math.pow(const1.value, const2.value)
        })
    }
}