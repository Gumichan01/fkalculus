package com.gumichan01.fkalculus.arg

import com.gumichan01.fkalculus.Arguments
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ArgumentParser {

    fun parse(args: Array<String>): Arguments {
        return ArgParser(args).parseInto(::ParsedArgs).run { Arguments(verbose) }
    }
}

class ParsedArgs(kparser: ArgParser) {

    val verbose by kparser.flagging("-v", "--verbose", help = "enable verbose mode of instructions").default(false)
}
