package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Const
import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.Subst
import com.gumichan01.fkalculus.ast.Var

class Substitution {
    fun subst(substitution: Subst): Expression {
        return substitution.run {
            if (expr == Var("x")) {
                expr1
            } else {
                Const(42.0)
            }
        }
    }

}
