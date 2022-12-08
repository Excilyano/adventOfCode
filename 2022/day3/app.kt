import java.io.File

val match=(('a'..'z').toList() + ('A'..'Z').toList()).joinToString("", "", "")

fun main() {
    println("Part 1 : ${part1()}")
    println("Part 2 : ${part2()}")
}

fun part1() : Int {
    var acc = 0
    File("input.txt").forEachLine{
        val strs = it.chunked(it.length / 2)
        val common = strs.get(0).filter{c -> strs.get(1).contains(c, false)}.get(0)
        acc += match.indexOf(common) +1
    }
    return acc
}

fun part2() : Int {
    return File("input.txt")
        .readLines()
        .chunked(3)
        .map{
            arrayStr -> arrayStr.fold(match){
                acc, str -> acc.filter{c -> str.contains(c)}
            }.get(0)
        }.map{c -> match.indexOf(c) + 1}
        .sum()
}