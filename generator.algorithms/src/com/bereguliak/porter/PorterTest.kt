package com.bereguliak.porter

import com.bereguliak.generator.utility.log

fun main(args:Array<String>) {
    val porter = Porter()

    porter.stemer("суттєвий")?.log()
    porter.stemer("суттєвість")?.log()
    porter.stemer("суттєва")?.log()
}