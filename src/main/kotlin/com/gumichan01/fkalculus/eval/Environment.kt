package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Expression

class Environment {

    private val env: List<Pair<String, Expression>>

    constructor() {
        env = emptyList()
    }

    private constructor(env: List<Pair<String, Expression>> = emptyList()) {
        this.env = env
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Environment -> env == other.env
            else -> false
        }
    }

    fun update(identifier: String, expression: Expression): Environment {
        return Environment(env + Pair(identifier, expression))
    }

    fun find(identifier: String): Expression {
        return env.find { (id, _) -> id == identifier }?.second
                ?: throw RuntimeException("Identifier not found in environment")
    }
}
