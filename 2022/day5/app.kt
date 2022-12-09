import java.io.File

fun main() {
    val p1 = "Part 1 : ${part1()}"
    val p2 = "Part 2 : ${part2()}"

    println(p1)
    println(p2)
}

fun part1() : String {
    val lines = File("input.txt").readLines()
    val tmp = lines
                .filter{ !it.isEmpty() && it[0] == '[' }
                .asReversed()
                .map{it.chunked(4)}

    val containers = mutableListOf<String>()
    for (i in 0..8) {
        containers += ""
        for (line in tmp) {
            containers[i] += line[i][1].toString().trim()
        }
    }
    containers.display()
    
    val actions = lines.filter{ !it.isEmpty() && it[0] == 'm' }
    
    actions.forEach{
        val orders = it.extractValues()
        println("Move ${orders.first} from ${orders.second} to ${orders.third}")
        val toMove = containers[orders.second].takeLast(orders.first)
        println("Moving ${toMove}")
        containers[orders.third] += toMove.reversed()
        containers[orders.second] = containers[orders.second].dropLast(orders.first)
        println()
        containers.display()
    }

    var result = ""
    containers.forEach{
        result += it.last()
    }
    return result
}

fun part2() : String {
    val lines = File("input.txt").readLines()
    val tmp = lines
                .filter{ !it.isEmpty() && it[0] == '[' }
                .asReversed()
                .map{it.chunked(4)}

    val containers = mutableListOf<String>()
    for (i in 0..8) {
        containers += ""
        for (line in tmp) {
            containers[i] += line[i][1].toString().trim()
        }
    }
    containers.display()
    
    val actions = lines.filter{ !it.isEmpty() && it[0] == 'm' }
    
    actions.forEach{
        val orders = it.extractValues()
        println("Move ${orders.first} from ${orders.second} to ${orders.third}")
        val toMove = containers[orders.second].takeLast(orders.first)
        println("Moving ${toMove}")
        containers[orders.third] += toMove
        containers[orders.second] = containers[orders.second].dropLast(orders.first)
        println()
        containers.display()
    }

    var result = ""
    containers.forEach{
        result += it.last()
    }
    return result
}

fun String.extractValues() : Triple<Int, Int, Int> {
    val values = this.split(' ')
    return Triple(values[1].toInt(), values[3].toInt() -1, values[5].toInt() -1)
}

fun List<String>.display() : Unit {
    for ((index, current) in this.withIndex()) {
        println("${index} : ${current}")
    }
}