class Day01 {

    companion object {

        private const val day = "day01"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 7)
            check(part2(testInput) == 5)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        fun part1(input: List<String>): Int {
            val depths = input.map { it.toInt() }

            return calculate(depths)
        }

        fun part2(input: List<String>): Int {
            val depths = input.map { it.toInt() }
            val windowDepths =
                depths.mapIndexedNotNull { index, i ->
                    if (index < depths.size - 2)
                        i + depths[index + 1] + depths[index + 2]
                    else null
                }

            return calculate(windowDepths)

        }

        fun calculate(depths: List<Int>): Int {
            var depth = depths.first()
            var counter = 0
            depths.forEach {
                if (it > depth) counter++
                depth = it
            }
            return counter
        }

    }
}