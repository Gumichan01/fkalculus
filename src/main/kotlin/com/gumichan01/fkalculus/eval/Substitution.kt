package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*

class Substitution {
    fun subst(substitution: Subst): Expression {
        return substitution.run {
            substituteComplexExpression(expr, variable, expr1)
        }
    }

    private fun substituteComplexExpression(expr: Expression, variable: String, expr1: Expression): Expression {
        return when (expr) {
            is Pi, is Exp1, is Const -> expr
            is Var -> if (expr.variable == variable) expr1 else expr
            else -> TODO("This is my job")
        }
    }
}

