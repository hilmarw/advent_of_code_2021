class Day02 {

    companion object {

        private const val day = "day02"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 150)
            check(part2(testInput) == 900)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val controls = input.map { Pair(it.substringBefore(' '), it.substringAfter(' ').toInt()) }
            var horizontal = 0
            var vertical = 0
            controls.forEach {
                when (it.first) {
                    "forward" -> horizontal += it.second
                    "up" -> vertical -= it.second
                    "down" -> vertical += it.second
                }
            }
            return horizontal * vertical
        }

        private fun part2(input: List<String>): Int {
            val controls = input.map { Pair(it.substringBefore(' '), it.substringAfter(' ').toInt()) }
            var horizontal = 0
            var vertical = 0
            var aim = 0
            controls.forEach {
                when (it.first) {
                    "forward" -> {
                        horizontal += it.second
                        vertical += aim * it.second
                    }
                    "up" -> aim -= it.second
                    "down" -> aim += it.second
                }
            }
            return horizontal * vertical
        }
    }
}