import java.io.File

fun main() {
    val p1 = "Part 1 : ${part1(4)}"
    val p2 = "Part 2 : ${part1(14)}"

    println(p1)
    println(p2)
}

fun part1(size : Int) : Int {
    return File("input.txt")
        .readLines()[0]
        .windowed(size)
        .map{it.toList().distinct()}
        .indexOfFirst{it.size == size}
        .plus(size)
}