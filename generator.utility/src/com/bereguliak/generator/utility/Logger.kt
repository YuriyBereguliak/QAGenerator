package com.bereguliak.generator.utility

fun Exception.logError() {
    val e = this
    System.err.println("Error :: ${e.message}")
}

fun String.log() {
    System.out.println("Debug :: $this")
}

fun <T> MutableList<T>.log(type: String) {
    System.out.println("--------------$type----------------")
    forEach {
        System.out.println("$it \n")
    }
}