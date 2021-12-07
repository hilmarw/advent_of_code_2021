package day07

import readInput
import kotlin.math.abs

class Day07 {

    companion object {

        private const val day = "day07"
        private val filePath =
            "$day/${day.capitalize()}"


        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 37)
            check(part2(testInput) == 168)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val crabs = input.first().split(",").map { it.toInt() }

            val array = IntArray(crabs.maxOf { it } + 1)
            crabs.forEach { i -> array[i]++ }

            var sumOfMoves = Int.MAX_VALUE

            for (i in crabs.indices) {
                var moves = 0
                array.forEachIndexed { index, amount -> moves += abs(i - index) * amount }
                if (moves < sumOfMoves) sumOfMoves = moves
            }
            return sumOfMoves
        }


        private fun part2(input: List<String>): Int {
            val crabs = input.first().split(",").map { it.toInt() }

            val array = IntArray(crabs.maxOf { it } + 1)
            crabs.forEach { i -> array[i]++ }

            var sumOfMoves = Int.MAX_VALUE

            for (i in crabs.indices) {
                var moves = 0
                array.forEachIndexed { index, amount -> moves += (abs(i - index) * (abs(i - index) + 1)) / 2 * amount } // Gaußsche Summenformel
                if (moves < sumOfMoves) sumOfMoves = moves
            }
            return sumOfMoves
        }
    }
}