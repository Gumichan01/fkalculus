package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Const
import com.gumichan01.fkalculus.ast.Expression

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
}
