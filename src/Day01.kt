fun main() {
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

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
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
