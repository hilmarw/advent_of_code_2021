package day14

import readInput

class Day14 {

    companion object {

        private const val day = "day14"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 1588)
            check(part2(testInput) == 2188189693529)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            var polymer = input.first()

            val instructions = input.takeLastWhile { it.isNotEmpty() }
                .associate { it.substring(0, 2) to "${it.first()}${it.last()}${it[1]}" }

            for (i in 1..10) {
                polymer = polymer.windowed(2)
                    .map {
                        instructions[it]
                    }.reduce { acc, s -> (acc ?: "").dropLast(1) + s }!!
            }

            val result = polymer.groupBy { it }

            return result.maxOf { it.value.size } - result.minOf { it.value.size }
        }

        private fun part2(input: List<String>): Long {
            val polymer = input.first()
            val initial = polymer.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

            val instructions = input.takeLastWhile { it.isNotEmpty() }
                .associate { it.substring(0, 2) to it.last().toString() }

            val pairsCount = (1..40).fold(initial) { current, _ ->
                buildMap {
                    current.forEach { (pair, count) ->
                        val insertedChar = instructions[pair]!!
                        val first = pair.first() + insertedChar
                        val second = insertedChar + pair.last()
                        put(first, getOrDefault(first, 0) + count)
                        put(second, getOrDefault(second, 0) + count)
                    }
                }
            }

            val result = buildMap<Char, Long> {
                put(polymer.first(), 1)
                pairsCount.forEach { (pair, count) ->
                    put(pair.last(), getOrDefault(pair.last(), 0) + count)
                }
            }

            return result.maxOf { it.value } - result.minOf { it.value }
        }
    }
}