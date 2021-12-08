package day08

import readInput

class Day08 {

    companion object {

        private const val day = "day08"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 26)
            check(part2(testInput) == 61229)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val pairs = input.map { Pair(it.substringBefore(" | "), it.substringAfter(" | ")) }
            return pairs.sumOf {
                it.second.split(" ")
                    .filter { activeWires -> activeWires.length == 2 || activeWires.length == 3 || activeWires.length == 4 || activeWires.length == 7 }.size
            }
        }


        private fun part2(input: List<String>): Int =
            input.map { Pair(it.substringBefore(" | "), it.substringAfter(" | ")) }.sumOf {
                val groupedDigits = it.first.split(" ").groupBy { string -> string.length }

                val nine = groupedDigits[6]!!.find { string ->
                    string.toList().containsAll(groupedDigits[4]!!.first().toList())
                }!!

                it.second.split(" ").map { string ->
                    when (string.length) {
                        2 -> 1
                        3 -> 7
                        4 -> 4
                        7 -> 8
                        5 -> {
                            if (string.toList().containsAll(groupedDigits[2]!!.first().toList())) 3
                            else if (nine.toList().containsAll(string.toList())) 5
                            else 2
                        }
                        6 -> {
                            if (string.toList().containsAll(groupedDigits[4]!!.first().toList())) 9
                            else if (string.toList().containsAll(groupedDigits[2]!!.first().toList())) 0
                            else 6
                        }
                        else -> throw IllegalArgumentException("WTF")
                    }
                }.joinToString(separator = "") { number -> number.toString() }.toInt()
            }
    }
}