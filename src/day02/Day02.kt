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

        fun part1(input: List<String>): Int {
            val controls = input.map { it.split(" ") }
            var horizontal = 0
            var vertical = 0
            controls.forEach {
                when (it.first()) {
                    "forward" -> horizontal += it[1].toInt()
                    "up" -> vertical -= it[1].toInt()
                    "down" -> vertical += it[1].toInt()
                }
            }

            return horizontal * vertical
        }

        fun part2(input: List<String>): Int {
            val controls = input.map { it.split(" ") }
            var horizontal = 0
            var vertical = 0
            var aim = 0
            controls.forEach {
                when (it.first()) {
                    "forward" -> {
                        horizontal += it[1].toInt()
                        vertical += aim * it[1].toInt()
                    }
                    "up" -> aim -= it[1].toInt()
                    "down" -> aim += it[1].toInt()
                }
            }
            return horizontal * vertical
        }
    }
}