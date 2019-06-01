package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*

class Substitution {
    fun subst(substitution: Subst): Expression {
        return substitution.run {
            substitute(expr, variable, expr1)
        }
    }

    private fun substitute(expr: Expression, variable: String, expr1: Expression): Expression {
        return when (expr) {
            is Pi, is Exp1, is Const, is Identifier -> expr
            is Var -> if (expr.variable == variable) expr1 else expr
            is Binop -> {
                var e1 = substitute(expr.expr1, variable, expr1)
                var e2 = substitute(expr.expr2, variable, expr1)
                Binop(expr.operator, e1, e2)
            }
            is Sqrt -> Sqrt(substitute(expr.expr, variable, expr1))
            is Expo -> Expo(substitute(expr.expr, variable, expr1))
            else -> TODO("This is my job")
        }
    }
}

