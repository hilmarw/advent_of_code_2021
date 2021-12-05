package day05

import readInput

class Day05 {

    data class Vector(
        val sourceX: Int,
        val sourceY: Int,
        val targetX: Int,
        val targetY: Int,
    )


    companion object {

        private const val day = "day05"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 5)
            check(part2(testInput) == 12)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val vectors = input.map { it.replace(" -> ", ",") }.map { it.split(",") }
                .map { Vector(it[0].toInt(), it[1].toInt(), it[2].toInt(), it[3].toInt()) }
                .filter { it.sourceX == it.targetX || it.sourceY == it.targetY }

            val array = Array(1000) { IntArray(1000) { 0 } }

            vectors.forEach {
                if (it.sourceX != it.targetX) {
                    for (i in it.sourceX toward it.targetX) {
                        array[it.sourceY][i]++
                    }
                } else if (it.sourceY != it.targetY) {
                    for (i in it.sourceY toward it.targetY) {
                        array[i][it.sourceX]++
                    }
                }
            }

            return array.sumOf { it.count { int -> int > 1 } }
        }


        private fun part2(input: List<String>): Int {
            val vectors = input.map { it.replace(" -> ", ",") }.map { it.split(",") }
                .map { Vector(it[0].toInt(), it[1].toInt(), it[2].toInt(), it[3].toInt()) }

            val array = Array(1000) { IntArray(1000) { 0 } }

            vectors.forEach {
                val xIterator = it.sourceX.toward(it.targetX).iterator()
                val yIterator = it.sourceY.toward(it.targetY).iterator()

                var i = 0
                var j = 0

                while (xIterator.hasNext() || yIterator.hasNext()) {
                    if (xIterator.hasNext()) i = xIterator.next()
                    if (yIterator.hasNext()) j = yIterator.next()
                    array[i][j]++
                }
            }
            return array.sumOf { it.count { int -> int > 1 } }
        }

        private infix fun Int.toward(to: Int): IntProgression {
            val step = if (this > to) -1 else 1
            return IntProgression.fromClosedRange(this, to, step)
        }
    }
}