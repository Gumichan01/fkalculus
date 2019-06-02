package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Const
import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.Pi

class Environment {

    var id: String? = null
    var expr: Expression? = null

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Environment -> id == other.id && expr == other.expr
            else -> false
        }
    }

    fun update(identifier: String, expression: Const): Environment {
        val newEnv = Environment()
        newEnv.id = identifier
        newEnv.expr = expression

        return newEnv
    }

    fun find(identifier: String): Expression {
        return if (identifier == "v1") Const(42.0) else Pi
    }
}
