package day13

import readInput
import java.awt.Point

class Day13 {

    companion object {

        private const val day = "day13"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 17)
            check(part2(testInput) == 16)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val points =
                input.takeWhile { it.isNotBlank() }
                    .map { Pair(it.substringBefore(",").toInt(), it.substringAfter(",").toInt()) }
                    .map { Point(it.first, it.second) }

            val fold =
                input.takeLastWhile { it.isNotBlank() }
                    .map { Pair(it.removePrefix("fold along ").substringBefore("="), it.substringAfter("=").toInt()) }
                    .first()

            fold(fold, points)

            val reducedSet = points.toHashSet()
            return reducedSet.size
        }

        private fun part2(input: List<String>): Int {
            val points =
                input.takeWhile { it.isNotBlank() }
                    .map { Pair(it.substringBefore(",").toInt(), it.substringAfter(",").toInt()) }
                    .map { Point(it.first, it.second) }

            val folds =
                input.takeLastWhile { it.isNotBlank() }
                    .map { Pair(it.removePrefix("fold along ").substringBefore("="), it.substringAfter("=").toInt()) }

            folds.forEach { fold ->
                fold(fold, points)
            }

            val reducedSet = points.toHashSet()
            pointsToString(reducedSet)
            return reducedSet.size
        }

        private fun fold(fold: Pair<String, Int>, points: List<Point>) {
            when (fold.first) {
                "x" -> {
                    points.forEach { point ->
                        if (point.x > fold.second) {
                            point.x = 2 * fold.second - point.x
                        }
                    }
                }
                "y" -> {
                    points.forEach { point ->
                        if (point.y > fold.second) {
                            point.y = 2 * fold.second - point.y
                        }
                    }
                }
            }
        }

        private fun pointsToString(points: Set<Point>) {
            val matrix = Array(points.maxOf { it.y } + 1) { BooleanArray(points.maxOf { it.x } + 1) }
            points.forEach { matrix[it.y][it.x] = true }
            matrix.forEach { booleans -> println(booleans.joinToString("") { if (it) "#" else " " }) }
        }
    }
}