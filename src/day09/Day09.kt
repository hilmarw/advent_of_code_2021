package day09

import readInput

class Day09 {

    companion object {

        private const val day = "day09"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 15)
            check(part2(testInput) == 1134)


            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            val array = input.map { it.map { digit -> digit.digitToInt() } }

            var counter = 0
            array.forEachIndexed { i, row ->
                row.forEachIndexed { j, digit ->
                    if ((i == 0 || array[i - 1][j] > digit) && (i == array.size - 1 || array[i + 1][j] > digit) && (j == row.size - 1 || array[i][j + 1] > digit) && (j == 0 || array[i][j - 1] > digit))
                        counter += digit + 1
                }

            }
            return counter
        }


        private fun part2(input: List<String>): Int {
            val array = input.map { it.map { digit -> digit.digitToInt() } }

            val list = mutableListOf<Int>()

            array.forEachIndexed { i, row ->
                row.forEachIndexed { j, digit ->
                    if ((i == 0 || array[i - 1][j] > digit) && (i == array.size - 1 || array[i + 1][j] > digit) && (j == row.size - 1 || array[i][j + 1] > digit) && (j == 0 || array[i][j - 1] > digit)) {
                        val visited = mutableListOf<Pair<Int, Int>>()
                        calculateSize(array, i, j, visited)
                        list.add(visited.size)
                    }
                }

            }
            list.sortDescending()
            return list[0] * list[1] * list[2]

        }

        private fun calculateSize(array: List<List<Int>>, i: Int, j: Int, visited: MutableList<Pair<Int, Int>>) {
            if (visited.contains(Pair(i, j))) return
            if (i == array.size || i == -1 || j == -1 || j == array[i].size) return
            if (array[i][j] == 9) return

            visited.add(Pair(i, j))

            calculateSize(array, i + 1, j, visited)
            calculateSize(array, i - 1, j, visited)
            calculateSize(array, i, j + 1, visited)
            calculateSize(array, i, j - 1, visited)
        }
    }
}