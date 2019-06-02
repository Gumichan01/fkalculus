package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Expression

class Environment {

    private val env: List<Pair<String, Expression>>

    constructor() {
        env = emptyList()
    }

    private constructor(env: List<Pair<String, Expression>>) {
        this.env = env
    }

    fun update(identifier: String, expression: Expression): Environment {
        return Environment(env + Pair(identifier, expression))
    }

    fun find(identifier: String): Expression {
        return env.find { (id, _) -> id == identifier }?.second
                ?: throw RuntimeException("Identifier not found in environment")
    }

    override fun equals(other: Any?): Boolean {
        return if (other is Environment) env == other.env else false
    }

    override fun hashCode(): Int {
        return env.hashCode()
    }
}
