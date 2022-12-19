import java.io.File

abstract class Hierarchy(val parent: Folder?) {
    abstract fun fsize() : Int
    abstract fun display(tab: Int = 0)
    abstract fun part1() : Int
    abstract fun part2(limit : Int) : IntArray
}

class MaFile(val size: Int, parent: Folder?) : Hierarchy(parent) {
    override fun fsize() = size
    override fun display(tab: Int) = println("${"  "*tab}fichier : ${size}")
    override fun part1() = 0
    override fun part2(limit : Int) = arrayOf<Int>().toIntArray()
}

class Folder(val name: String, parent: Folder?, val content: MutableList<Hierarchy> = mutableListOf<Hierarchy>()) : Hierarchy(parent) {
    override fun fsize() = content.fold(0) {acc, v -> acc + v.fsize()}
    override fun display(tab: Int) {
        println("${"  "*tab}repertoire ${name} : (${fsize()})")
        content.forEach{it.display(tab+1)}
    }
    override fun part1() : Int {
        val size = fsize()
        return if (size < 100000) size + content.fold(0){acc, v -> acc + v.part1()}
        else content.fold(0){acc, v -> acc + v.part1()}
    }
    override fun part2(limit : Int) : IntArray {
        val size = fsize()
        var result : IntArray
        if (size >= limit) result = arrayOf(size).toIntArray()
        else result = arrayOf<Int>().toIntArray()
        content.forEach{
            result = result.union(it.part2(limit).toList()).toIntArray()
        }
        return result
    }
}

operator fun String.times(count: Int): String {
    var result = ""
    for (i in 1..count) result += this
    return result
}

fun main() {
    val tree = parseInput()
    //tree.display()

    val p1 = "Part 1 : ${tree.part1()}"
    val p2 = "Part 2 : ${part2(tree)}"

    println(p1)
    println(p2)
}

fun parseInput() : Folder {
    val root = Folder("/", null)
    var current = root
    File("input.txt").forEachLine{
        val action = it.substring(0..3)
        when (action) {
            "$ cd" -> {
                val dest = it.split(" ").last()
                when (dest) {
                    "/" -> current = root
                    ".." -> current = current.parent?:root
                    else -> {
                        val newF = Folder(dest, current)
                        current.content += newF
                        current = newF
                    }
                }
            }
            "$ ls", "dir " -> {}
            else -> {
                val values = it.split(" ")
                current.content += MaFile(values[0].toInt(), current)
            }
        }
    }
    return root
}

fun part2(root: Hierarchy) : Int {
    val MAX_SIZE = 70000000
    val REQUIRED = 30000000
    val AVAILABLE = MAX_SIZE - root.fsize()

    println(AVAILABLE)
    val candidates = root.part2(REQUIRED - AVAILABLE)

    return candidates.min()
}