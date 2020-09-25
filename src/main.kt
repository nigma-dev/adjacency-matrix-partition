/*val data = listOf(
    listOf(1, 0, 1, 0),
    listOf(1, 0, 1, 0),
    listOf(1, 0, 1, 0),
    listOf(1, 1, 0, 1)
)*/

val data = listOf(
    listOf(1, 1, 1, 0, 1, 0, 1, 0),
    listOf(1, 1, 1, 1, 1, 1, 1, 1),
    listOf(1, 1, 1, 1, 0, 1, 1, 0),
    listOf(1, 1, 1, 1, 1, 0, 1, 1),
    listOf(1, 1, 1, 1, 1, 0, 1, 1),
    listOf(1, 1, 1, 1, 1, 1, 1, 1)
)

fun main() {
    val part = Partition(data)
    val size = part.checkNode()
    part.printNodes()
    println("partition -> $size")
}




