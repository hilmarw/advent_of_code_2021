package day11

import readInput

class Day11 {

    companion object {

        private const val day = "day11"
        private val filePath = "$day/${day.capitalize()}"
        var flashes = 0
        lateinit var octopuses: List<Octopus>

        class Octopus(var energyLevel: Int, val x: Int, val y: Int) {
            var flashed = false
            fun flash() {
                flashes++
                flashed = true

                val bla = octopuses.filter {
                    it.x in x - 1..x + 1 && it.y in y - 1..y + 1
                }
                bla.forEach { octopus -> octopus.energyLevel++ }
            }
        }

        fun runDay() {
            println("${day.capitalize()}:")
            val testInput = readInput("${filePath}_test")
            check(part1(testInput) == 1656)
            check(part2(testInput) == 195)

            val input = readInput(filePath)
            println(part1(input))
            println(part2(input))
        }

        private fun part1(input: List<String>): Int {
            flashes = 0
            octopuses = input.flatMapIndexed { x, str -> str.mapIndexed { y, c -> Octopus(c.digitToInt(), x, y) } }

            for (i in 1..100) {
                step(octopuses)
                octopuses.forEach { octopus ->
                    if (octopus.flashed) octopus.energyLevel = 0
                    octopus.flashed = false
                }
            }
            return flashes
        }

        private fun part2(input: List<String>): Int {
            flashes = 0
            octopuses = input.flatMapIndexed { x, str -> str.mapIndexed { y, c -> Octopus(c.digitToInt(), x, y) } }

            var index = 0
            var turnFlashes = 0

            while (turnFlashes < 100) {
                step(octopuses)

                turnFlashes = octopuses.count { it.flashed }

                octopuses.forEach { octopus ->
                    if (octopus.flashed) octopus.energyLevel = 0
                    octopus.flashed = false
                }
                index++
            }
            return index
        }

        private fun step(octopuses: List<Octopus>) {
            octopuses.forEach { octopus -> octopus.energyLevel++ }
            while (octopuses.any {
                    if (!it.flashed && it.energyLevel > 9) {
                        it.flash()
                        true
                    } else false
                }) {
                // Nothing to do
            }


        }

    }
}