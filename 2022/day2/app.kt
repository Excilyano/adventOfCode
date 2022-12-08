import java.io.File

fun main() {
    println("Part 1 : ${part1()}")
    println("Part 2 : ${part2()}")
}

fun part1() : Int {
    return File("input.txt").readLines().fold(0){
        acc, v -> when (v.get(0)) {
            'A' -> when (v.get(2)) {'X' -> acc + 4; 'Y' -> acc + 8; 'Z' -> acc + 3; else -> acc}
            'B' -> when (v.get(2)) {'X' -> acc + 1; 'Y' -> acc + 5; 'Z' -> acc + 9; else -> acc}
            'C' -> when (v.get(2)) {'X' -> acc + 7; 'Y' -> acc + 2; 'Z' -> acc + 6; else -> acc}
            else -> acc
        }
    }
}

fun part2() : Int {
    return File("input.txt").readLines().fold(0){
        acc, v -> when (v.get(0)) {
            'A' -> when (v.get(2)) {'X' -> acc + 3; 'Y' -> acc + 4; 'Z' -> acc + 8; else -> acc}
            'B' -> when (v.get(2)) {'X' -> acc + 1; 'Y' -> acc + 5; 'Z' -> acc + 9; else -> acc}
            'C' -> when (v.get(2)) {'X' -> acc + 2; 'Y' -> acc + 6; 'Z' -> acc + 7; else -> acc}
            else -> acc
        }
    }
}