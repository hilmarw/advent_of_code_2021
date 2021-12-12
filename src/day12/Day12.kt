package day12

import readInput

class Day12 {

    companion object {

        private const val day = "day12"
        private val filePath = "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 10)
            check(part2(testInput) == 36)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val connections = input.flatMap { it.split('-').let { (a, b) -> listOf(a to b, b to a) } }

            var paths = listOf(listOf("start"))
            while (paths.any { it.last() != "end" }) {
                paths = paths.flatMap { path ->
                    if (path.last() == "end") return@flatMap listOf(path)
                    (connections.filter { it.first == path.last() }
                        .map { it.second } - path.filter { it.first().isLowerCase() }).map { path + it }
                }
            }
            return paths.size
        }

        private fun part2(input: List<String>): Int {
            val connections = input.flatMap { it.split('-').let { (a, b) -> listOf(a to b, b to a) } }

            var paths = listOf(listOf("start"))
            while (paths.any { it.last() != "end" }) {
                paths = paths.flatMap { path ->
                    if (path.last() == "end") return@flatMap listOf(path)
                    else (connections.filter { it.first == path.last() }
                        .map { it.second } - path.getNotAllowed() - listOf("start")).map { path + it }
                }
            }
            return paths.size
        }

        fun List<String>.getNotAllowed() = filter { it.first().isLowerCase() }.let { list ->
            if (list.groupBy { it }.any { it.value.size > 1 }) list else emptyList()
        }

    }
}