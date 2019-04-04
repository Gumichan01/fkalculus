package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.ast.Help
import com.gumichan01.fkalculus.ast.HelpText
import com.gumichan01.fkalculus.ast.ResultValue
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class Evaluator {
    fun eval(ast: FKalculusAST): Option<ResultValue> {
        return when (ast) {
            is Help -> Some(HelpText("TODO help"))
            else -> None
        }
    }
}