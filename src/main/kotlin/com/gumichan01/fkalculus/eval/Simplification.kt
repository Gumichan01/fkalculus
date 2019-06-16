package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*


class Simplification(private val environment: Environment) {
    fun simplify(simpl: Simpl): Expression = simplify(simpl.expr)

    private fun simplify(expression: Expression): Expression {
        return when (expression) {
            is Pi, is Exp1, is Const -> expression
            is Identifier -> simplify(environment.find(expression.name))
            else -> throw UnsupportedOperationException("${stringOf(expression)} is not implemented yet")
        }
    }
}
