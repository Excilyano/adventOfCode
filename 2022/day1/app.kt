import java.io.File

var currentAcc = 0
var sums = mutableListOf<Int>()
var maxs = mutableListOf<Int>()

fun main() {
    File("input.txt").forEachLine{read(it)}
    for (i in 1..3) {
        maxs += sums.filter{it !in maxs}.max()
    }
    println(maxs.sum())
}

fun read(line : String) {
    when {
        line.isEmpty() -> {
            sums += currentAcc
            currentAcc = 0
        }
        else -> currentAcc += line.toInt()
    }
}