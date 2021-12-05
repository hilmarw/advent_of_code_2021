package day04

import readInput

class Day04 {

    companion object {

        private const val day = "day04"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 4512)
            check(part2(testInput) == 1924)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val (draws, results) = solveBoards(input)

            val minBoard = results.minByOrNull { it.second }
            val sumOfBoard = minBoard!!.first.sumOf { it.sumOf { int -> if (int == -1) 0 else int } }
            return sumOfBoard * draws[minBoard.second].toInt()
        }

        private fun part2(input: List<String>): Int {
            val (draws, results) = solveBoards(input)

            val maxBoard = results.maxByOrNull { it.second }
            val sumOfBoard = maxBoard!!.first.sumOf { it.sumOf { int -> if (int == -1) 0 else int } }
            return sumOfBoard * draws[maxBoard.second].toInt()
        }

        private fun solveBoards(input: List<String>): Pair<List<String>, MutableList<Pair<Array<IntArray>, Int>>> {
            val mutable = input.toMutableList()

            val draws = mutable.removeFirst().split(",")
            mutable.removeFirst()
            mutable.add("")

            val boards = mutableListOf<Array<IntArray>>()

            var board = mutableListOf<IntArray>()

            mutable.forEach {
                if (it.isNotBlank()) {
                    board.add(
                        it.chunked(
                            size = 3,
                            transform = { charSequence ->
                                charSequence.toString().replace("\\s".toRegex(), "").toInt()
                            }).toIntArray()
                    )
                } else {
                    boards.add(board.toTypedArray())
                    board = mutableListOf()
                }
            }

            val results = mutableListOf<Pair<Array<IntArray>, Int>>()

            boards.forEach { array ->
                for (i in draws.indices) {
                    checkDraw(array, draws[i].toInt())

                    if (checkIfSolved(array)) {
                        results.add(Pair(array, i))
                        break
                    }
                }
            }
            return Pair(draws, results)
        }

        private fun checkDraw(array: Array<IntArray>, draw: Int) {
            array.forEach { ints ->
                ints.forEachIndexed { index, i ->
                    if (i == draw) ints[index] = -1
                }
            }
        }

        private fun checkIfSolved(array: Array<IntArray>): Boolean {
            val ySize = array.size

            array.forEach {
                if (it.takeWhile { int -> int == -1 }.size == 5) {
                    return true
                }
            }
            for (i in 0 until ySize) {
                array.find { it[i] != -1 } ?: return true
            }
            return false
        }


    }
}