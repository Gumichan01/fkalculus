package com.gumichan01.fkalculus.ast

import org.junit.jupiter.api.Assertions.assertTrue
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

class TestAst {

    @test
    fun `test help command`() {
        val help = Help
        println(help)
    }

    @test
    fun `test simple expression`() {
        Const(5.0)
    }

    @test
    fun `test help value`() {
        val help: ResultValue = HelpText("help")
        println(help)
        assertTrue(help is HelpText)
    }

    @test
    fun `test identifier value`() {
        val expression: ResultValue = IdentifierValue("v1", Const(5.0))
        assertTrue(expression is IdentifierValue)
    }

    @test
    fun `test identifier use`() {
        val expression: Expression = Identifier("v1")
        assertTrue(expression is Identifier)
    }

    @test
    fun `test simpl instruction`() {
        val expression: Command = Simpl(Identifier("v0"))
        assertTrue(expression is Simpl)

    }

    @test
    fun `test subst instruction`() {
        val expression: Command = Subst(Var("x"), "x", Const(5.0))
        assertTrue(expression is Subst)

    }

    @test
    fun `test solve instruction`() {
        val expression: Command = Solve(Binop(Plus, Var("x"), Const(5.0)), "x")
        assertTrue(expression is Solve)
    }

    @test
    fun `test derive instruction`() {
        val expression: Command = Derive(Var("x"), "x")
        assertTrue(expression is Derive)

    }

    @test
    fun `test integration instruction`() {
        val expression: Command = Integ(Var("x"), "x", Const(5.0), Const(10.0))
        assertTrue(expression is Integ)

    }
}