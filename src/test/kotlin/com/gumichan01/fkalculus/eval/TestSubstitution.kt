package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.math.*
import org.junit.jupiter.api.Test as test

/**
Copyright, Luxon JEAN-PIERRE (2019)

luxon.jean.pierre@gmail.com

This software is a computer program whose purpose is to [describe
functionalities and technical features of your software].

This software is governed by the CeCILL license under French law and
abiding by the rules of distribution of free software.  You can  use,
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info".

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability.

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or
data to be ensured and,  more generally, to use and operate it in the
same conditions as regards security.

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.
 */

class TestSubstitution {

    @org.junit.jupiter.api.Test
    fun `test init class`() {
        Substitution()
    }

    @test
    fun `test simple substitution`() {
        val ast = Subst(Var("x"), "x", Const(2.0))
        val result: Expression = Substitution().subst(ast)
        assert(result == Const(2.0))
    }

    @test
    fun `test simple substitution - free variable`() {
        val ast = Subst(Var("x"), "y", Const(2.0))
        val result: Expression = Substitution().subst(ast)
        assert(result == Var("x"))
    }

    @test
    fun `test less simple substitution`() {
        val ast = Subst(Cos(Binop(Plus, Var("x"), Const(1.0))), "x", Const(2.0))
        val result: Expression = Substitution().subst(ast)
        assert(result == Cos(Binop(Plus, Const(2.0), Const(1.0))))
    }

    @test
    fun `test bad substitution`() {
        val ast = Subst(Cos(Binop(Plus, Var("x"), Const(1.0))), "1.0", Const(2.0))
        val result: Expression = Substitution().subst(ast)
        assert(result == Cos(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test another less simple substitution`() {
        val ast = Subst(Ln(Binop(Plus, Var("x"), Var("y"))), "x", Const(2.0))
        val result: Expression = Substitution().subst(ast)
        assert(result == Cos(Binop(Plus, Const(2.0), Var("y"))))
    }
}