package day03

import readInput

class Day03 {

    companion object {

        private const val day = "day03"
        private val filePath =
            "$day/${day.capitalize()}"

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 198)
            check(part2(testInput) == 230)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            var gammaRate = ""
            var epsilonRate = ""
            val arrays = input.map { it.toCharArray() }

            for (i in 0 until arrays.first().size) {
                val gamma = calculateRate(arrays, i)
                gammaRate += if (gamma > 0) 1 else 0
                epsilonRate += if (gamma < 0) 1 else 0
            }
            return gammaRate.toInt(2) * epsilonRate.toInt(2)

        }

        private fun part2(input: List<String>): Int {
            val oxygens = input.map { it.toCharArray() }.toMutableList()
            val co2s = input.map { it.toCharArray() }.toMutableList()
            val size = oxygens.first().size

            for (i in 0 until size) {
                val oxygen = calculateRate(oxygens, i)
                if (oxygens.size > 1)
                    oxygens.removeIf {
                        when {
                            oxygen >= 0 -> it[i].digitToInt() == 0
                            oxygen < 0 -> it[i].digitToInt() == 1
                            else -> throw IllegalArgumentException()
                        }
                    }
            }

            for (i in 0 until size) {
                val co2 = calculateRate(co2s, i)
                if (co2s.size > 1)
                    co2s.removeIf {
                        when {
                            co2 >= 0 -> it[i].digitToInt() == 1
                            co2 < 0 -> it[i].digitToInt() == 0
                            else -> throw IllegalArgumentException()
                        }
                    }
            }
            return oxygens.first().concatToString().toInt(2) * co2s.first().concatToString().toInt(2)
        }

        private fun calculateRate(arrays: List<CharArray>, i: Int): Int =
            arrays.sumOf {
                return@sumOf when (it[i]) {
                    '1' -> 1
                    '0' -> -1
                    else -> throw IllegalArgumentException()
                } as Int
            }
    }
}