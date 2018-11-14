package com.bereguliak.generator.utility

fun Exception.logError() {
    val e = this
    System.err.println("Error :: ${e.message}")
}

fun String.log() {
    System.out.println("Debug :: $this")
}