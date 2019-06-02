package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Const
import com.gumichan01.fkalculus.ast.Expression
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestEnvironment {

    @Test
    fun `test basic environment creation`() {
        Environment()
    }

    @Test
    fun `test structural equality of two fresh environments`() {
        val env1 = Environment()
        val env2 = Environment()

        assert(env1 == env1)
        assert(env2 == env2)
        assert(env1 == env2)
    }

    @Test
    fun `test update environment and check new envrironment if different from the previous`() {
        val environment = Environment()
        val identifier = "v1"
        val expression = Const(42.0)

        val newEnvironment: Environment = environment.update(identifier, expression)

        assert(newEnvironment != environment)
    }

    @Test
    fun `test update environment once and get the value of the identifier from the new environment`() {
        val environment = Environment()
        val identifier = "v1"
        val expectedExpression = Const(42.0)

        val newEnvironment: Environment = environment.update(identifier, expectedExpression)
        val retrievedExpression: Expression = newEnvironment.find(identifier)

        assert(retrievedExpression == expectedExpression)
    }

    @Test
    fun `test update environment once and get the value of the identifier tha is not in the new environment`() {
        val environment = Environment()
        val identifier = "v1"
        val badIdentifier = "v2"
        val expression = Const(42.0)

        val newEnvironment: Environment = environment.update(identifier, expression)
        assertThrows<RuntimeException> {
            val retrievedExpression: Expression = newEnvironment.find(badIdentifier)
            println("WTF $retrievedExpression")
        }
    }

    @Test
    fun `test update environment once and get the value of the identifier from the old environment`() {
        val environment = Environment()
        val identifier = "v1"
        val expectedExpression = Const(42.0)

        environment.update(identifier, expectedExpression)
        assertThrows<RuntimeException> {
            val retrievedExpression: Expression = environment.find(identifier)
            println("WTF $retrievedExpression")
        }
    }
}