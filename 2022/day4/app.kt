import java.io.File

fun main() {
    println("Part 1 : ${part1()}")
    println("Part 2 : ${part2()}")
}

fun part1() : Int {
    var acc = 0
    File("input.txt").forEachLine{
        val strs = it.split(",")
        val bornes1 = strs.get(0).split("-")
        val bornes2 = strs.get(1).split("-")
        val rng1 = bornes1.get(0).toInt()..bornes1.get(1).toInt()
        val rng2 = bornes2.get(0).toInt()..bornes2.get(1).toInt()
        val inter = rng1.intersect(rng2)

        if (rng1.toSet() == inter || rng2.toSet() == inter) acc++
    }
    return acc
}

fun part2() : Int {
    var acc = 0
    File("input.txt").forEachLine{
        val strs = it.split(",")
        val bornes1 = strs.get(0).split("-")
        val bornes2 = strs.get(1).split("-")
        val rng1 = bornes1.get(0).toInt()..bornes1.get(1).toInt()
        val rng2 = bornes2.get(0).toInt()..bornes2.get(1).toInt()
        val inter = rng1.intersect(rng2)

        if (inter.size > 0) acc++
    }
    return acc
}