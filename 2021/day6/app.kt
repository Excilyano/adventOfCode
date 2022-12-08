import java.io.File

val FILE = "input2.txt"
val DAYS = 256
val mod = arrayOf (
    longArrayOf(0,1,0,0,0,0,0,0,0),
    longArrayOf(0,0,1,0,0,0,0,0,0),
    longArrayOf(0,0,0,1,0,0,0,0,0),
    longArrayOf(0,0,0,0,1,0,0,0,0),
    longArrayOf(0,0,0,0,0,1,0,0,0),
    longArrayOf(0,0,0,0,0,0,1,0,0),
    longArrayOf(1,0,0,0,0,0,0,1,0),
    longArrayOf(0,0,0,0,0,0,0,0,1),
    longArrayOf(1,0,0,0,0,0,0,0,0)
)

fun main() {
    val rawInput = File(FILE)
                    .readLines()
                    .get(0)
                    .split(",")
                    .map{c -> c.toLong()}
    val initial = arrayOf( LongArray(1), LongArray(1), LongArray(1), LongArray(1), LongArray(1), LongArray(1), LongArray(1), LongArray(1), LongArray(1) )
    for (i in 0..8) {
        initial[i][0] = rawInput.count{t -> t==i.toLong()}.toLong()
    }
    val result = multiplyMatrices(pow(mod, mod, DAYS), initial)
    //display(result)
    println("Result : ${result.fold(0L){acc, a -> acc + a.sum()}}")
}

fun pow(initialMatrix: Array<LongArray>, matrix : Array<LongArray>, nb : Int) : Array <LongArray> {
    return when (nb) {
        1 -> matrix
        else -> pow(initialMatrix, multiplyMatrices(matrix, initialMatrix), nb - 1)
    }
}

fun multiplyMatrices(firstMatrix: Array <LongArray>, 
                     secondMatrix: Array <LongArray>
                     ): Array <LongArray> {
    val r1 = firstMatrix.size
    val c1 = firstMatrix[0].size
    val c2 = secondMatrix[0].size
    val product = Array(r1) { LongArray(c2) }
    for (i in 0..r1 - 1) {
        for (j in 0..c2 - 1) {
            for (k in 0..c1 - 1) {
                product[i][j] += firstMatrix[i][k] * secondMatrix[k][j]
            }
        }
    }
    return product
}

fun display(matrix: Array<LongArray>) : Unit {
    for (row in matrix) {
        for (col in row) {
            print("${col} ")
        }
        println()
    }
}