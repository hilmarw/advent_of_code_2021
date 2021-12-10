package day10

import readInput

class Day10 {

    companion object {

        private const val day = "day10"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 26397)
            check(part2(testInput) == 288957L)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            return input.sumOf { row ->
                val list = mutableListOf<Char>()
                row.forEach {
                    when (it) {
                        ')' -> if (list.last() == '(') list.removeLast() else return@sumOf 3
                        ']' -> if (list.last() == '[') list.removeLast() else return@sumOf 57
                        '}' -> if (list.last() == '{') list.removeLast() else return@sumOf 1197
                        '>' -> if (list.last() == '<') list.removeLast() else return@sumOf 25137
                        else -> list.add(it)
                    }
                }
                0 as Int
            }
        }

        private fun part2(input: List<String>): Long {
            val scores = input.mapNotNull { row ->
                val list = mutableListOf<Char>()
                row.forEach {
                    when (it) {
                        ')' -> if (list.last() == '(') list.removeLast() else return@mapNotNull null
                        ']' -> if (list.last() == '[') list.removeLast() else return@mapNotNull null
                        '}' -> if (list.last() == '{') list.removeLast() else return@mapNotNull null
                        '>' -> if (list.last() == '<') list.removeLast() else return@mapNotNull null
                        else -> list.add(it)
                    }
                }
                return@mapNotNull list
            }
                .map { row ->
                    row.foldRight(0L) { char, acc ->
                        5 * acc + when (char) {
                            '(' -> 1
                            '[' -> 2
                            '{' -> 3
                            '<' -> 4
                            else -> throw IllegalArgumentException()
                        }
                    }
                }.sorted()
            return scores[scores.size / 2]
        }
    }
}