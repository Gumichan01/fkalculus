package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*


class Simplification(private val environment: Environment) {
    fun simplify(simpl: Simpl): Expression = simplify(simpl.expr)

    private fun simplify(expression: Expression): Expression {
        return when (expression) {
            is Pi, is Exp1, is Const, is Var -> expression
            is Identifier -> simplify(environment.find(expression.name))
            is Binop -> simplifyBinop(expression)
            is Sqrt -> simplify(expression.expr)
            is Expo -> simplify(expression.expr)
            is Ln -> simplify(expression.expr)
            is Log10 -> simplify(expression.expr)
            is Log2 -> simplify(expression.expr)
            is Cos -> simplify(expression.expr)
            is Sin -> simplify(expression.expr)
            is Tan -> simplify(expression.expr)
            is Acos -> simplify(expression.expr)
            is Asin -> simplify(expression.expr)
            is Atan -> simplify(expression.expr)
            is Sec -> simplify(expression.expr)
            is Cosec -> simplify(expression.expr)
            is Cotan -> simplify(expression.expr)
            is Asec -> simplify(expression.expr)
            is Acosec -> simplify(expression.expr)
            is Acotan -> simplify(expression.expr)
            else -> throw UnsupportedOperationException("${stringOf(expression)} is not implemented yet")
        }
    }

    private fun simplifyBinop(binop: Binop): Expression {
        return binop.run {
            val simplifiedExpression1 = simplify(expr1)
            val simplifiedExpression2 = simplify(expr2)

            if (expr1 == Const(0.0)) {
                simplifiedExpression2
            } else if (expr2 == Const(0.0)) {
                simplifiedExpression1
            } else {
                Binop(operator, simplifiedExpression1, simplifiedExpression2)
            }
        }
    }
}
