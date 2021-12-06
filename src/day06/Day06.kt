package day06

import readInput

class Day06 {

    companion object {

        private const val day = "day06"
        private val filePath =
            "$day/${day.capitalize()}"


        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 5934)
            check(part2(testInput) == 26984457539)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            var fishes = IntArray(9)
            input.first().split(",").forEach { fishes[it.toInt()]++ }

            for (i in 0 until 80) {
                val newIterationOfFishes = IntArray(9)
                fishes.forEachIndexed { index, it ->
                    if (index == 0) {
                        newIterationOfFishes[6] += it
                        newIterationOfFishes[8] += it
                    } else {
                        newIterationOfFishes[index - 1] += it
                    }
                }
                fishes = newIterationOfFishes
            }
            return fishes.sum()
        }


        private fun part2(input: List<String>): Long {
            var fishes = LongArray(9)
            input.first().split(",").forEach { fishes[it.toInt()]++ }

            for (i in 0 until 256) {
                val newIterationOfFishes = LongArray(9)
                fishes.forEachIndexed { index, it ->
                    if (index == 0) {
                        newIterationOfFishes[6] += it
                        newIterationOfFishes[8] += it
                    } else {
                        newIterationOfFishes[index - 1] += it
                    }
                }
                fishes = newIterationOfFishes
            }
            return fishes.sum()
        }


    }
}