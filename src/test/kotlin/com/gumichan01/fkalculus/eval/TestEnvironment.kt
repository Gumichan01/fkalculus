package com.gumichan01.fkalculus.eval

import org.junit.jupiter.api.Test

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

}