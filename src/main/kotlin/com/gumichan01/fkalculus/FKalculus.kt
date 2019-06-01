package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.ast.ResultValue
import com.gumichan01.fkalculus.eval.Normalizer
import com.gumichan01.fkalculus.parse.KalculusParser
import com.gumichan01.fkalculus.print.Printer
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

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

class FKalculus(val arguments: Arguments) {

    private val normalizer = Normalizer()

    fun start() {
        var quit = false

        while (!quit) {
            print("fkalculus > ")
            val text: Option<String> = readText()

            if (text is Some) {
                if (text.t.isNotBlank()) {
                    val kalculus: Option<FKalculusAST> = parse(text.t)

                    if (kalculus is Some) {
                        val result: Option<ResultValue> = eval(kalculus.t)
                        if (result is Some) {
                            print(result.t)
                        }
                    } else {
                        println("Invalid command, type \"help\" to get available commands.")
                    }
                }
            } else {
                quit = true
                println("Exit.")
            }
        }
    }

    private fun parse(text: String): Option<FKalculusAST> {
        return KalculusParser().parse(text)
    }

    private fun readText(): Option<String> {
        return try {
            Some(readLine()!!)
        } catch (e: NullPointerException) {
            None
        }
    }

    private fun eval(ast: FKalculusAST): Option<ResultValue> {
        return normalizer.eval(ast)
    }

    private fun print(result: ResultValue) {
        Printer().print(result)
    }
}