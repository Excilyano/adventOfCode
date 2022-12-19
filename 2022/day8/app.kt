import java.io.File

lateinit var matrix : List<List<Int>>
var length : Int = 0

fun main() {
    matrix = parseFile()
    length = matrix.size

    val p1 = "Part 1 : ${part1()}"
    val p2 = "Part 2 : ${part2()}"

    println(p1)
    println(p2)
}

fun parseFile() : List<List<Int>> {
    return File("input.txt")
        .readLines()
        .map{it.toList().map{ v -> v.digitToInt()}}
}

fun part1() : Int {
    var cpt = 0
    for (i in 0 until length)
        for (j in 0 until length)
            if (isVisible(i, j))
                cpt++

    return cpt
}

fun part2() : Int {
    var higher = 0
    for (i in 1 until length-1)
        for (j in 1 until length-1) {
            val score = scenic(i, j)
            if (score > higher) higher = score
        }
    return higher
}

fun scenic(x: Int, y: Int): Int {
    val row = matrix.fold(IntArray(0)) {acc, v -> acc + v[y]}
    val column = matrix[x]
    val height = matrix[x][y]

    var i = x
    do {i--} while (i > 0 && row[i] < height)
    val tScore = x - i
    
    i = x
    do {i++} while (i < length-1 && row[i] < height)
    val bScore = i - x
    
    i = y
    do {i--} while (i > 0 && column[i] < height)
    val lScore = y - i
    
    i = y
    do {i++} while (i < length-1 && column[i] < height)
    val rScore = i - y

    val scenicScore = lScore * rScore * tScore * bScore
    return scenicScore
}

fun isVisible(x: Int, y: Int): Boolean {
    val row = matrix.fold(IntArray(0)) {acc, v -> acc + v[y]}
    val column = matrix[x]

    var visibleL = true
    var visibleR = true
    var visibleT = true
    var visibleB = true
    val value = matrix[x][y]

    for(i in 0 until x)
        if (row[i] >= value)
            visibleL = false
    
    for(i in x+1 until length)
        if (row[i] >= value)
            visibleR = false

    for (i in 0 until y)
        if (column[i] >= value)
            visibleT = false
    
    for (i in y+1 until length)
        if (column[i] >= value)
            visibleB = false
    
    return visibleL || visibleR || visibleT || visibleB
}