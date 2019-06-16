package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*


class Simplification(private val environment: Environment) {
    fun simplify(simpl: Simpl): Expression = simplify(simpl.expr)

    private fun simplify(expression: Expression): Expression {
        return when (expression) {
            is Pi, is Exp1, is Const, is Var -> expression
            is Identifier -> simplify(environment.find(expression.name))
            is Binop -> simplifyBinop(expression)
            is FunCall -> simplifyFunCall(expression)
        }
    }

    private fun simplifyFunCall(mathFunCall: FunCall): Expression {
        return when (mathFunCall) {
            is Sqrt, is Expo, is Ln, is Log10, is Log2, is Cos, is Sin, is Tan, is Acos, is Asin, is Atan,
            is Sec, is Cosec, is Cotan, is Asec, is Acosec, is Acotan -> simplify(mathFunCall.expr)
        }
    }

    private fun simplifyBinop(binop: Binop): Expression {
        return binop.run {
            when(operator) {
                is Plus -> simplifyPlus(expr1, expr2)
                else -> throw UnsupportedOperationException("${stringOf(this)} is not implemented yet")
            }
        }
    }

    private fun simplifyPlus(expr1: Expression, expr2: Expression): Expression {
        val simplifiedExpression1 = simplify(expr1)
        val simplifiedExpression2 = simplify(expr2)

        return when {
            expr1 == Const(0.0) -> simplifiedExpression2
            expr2 == Const(0.0) -> simplifiedExpression1
            else -> Binop(Plus, simplifiedExpression1, simplifiedExpression2)
        }
    }
}

