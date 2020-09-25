class Partition(private val data: List<List<Int>>) {

    private val nodes = mutableListOf<Node>()

    private val dataSize = data.first().size

    /*val data = listOf(
        listOf(1, 0, 1, 0),
        listOf(1, 0, 1, 0),
        listOf(1, 1, 1, 0),
        listOf(1, 1, 0, 1)
    )*/

    fun checkNode(): Int {
        for ((x, row) in data.withIndex()) {
            indicator@ for ((y, index) in row.withIndex()) {
                if (index != 1) continue
                for (node in nodes) {
                    if (node.reference.contains(structNode(x, y, node))) {
                        continue@indicator
                    }
                }
                structRootNode(x, y)
            }
        }
        return nodes.size
    }

    fun printNodes() {
        nodes.forEach { base ->
            println("node -> ${base.index}")
            val children = base.reference.sortedBy { it.index }
            if (children.isEmpty()) {
                println("\t | ** Note this node has no child !!")
            }
            children.forEach { child ->
                val cord = child.coordinate
                println("\t | node  -> index :${getIndex(cord.x, cord.y)} \t| coordinate : [${cord.x},${cord.y}]")
            }
            println("-------------------------------------------------")
        }
    }

    private fun structRootNode(x: Int, y: Int) {
        val node = structNode(x, y)
        nodes.add(node)
        checkAdjacentIndex(node, x, y)
    }

    private fun checkAdjacentIndex(head: Node, x: Int, y: Int) {
        val x2 = x + 1
        if (validateRange(x2, y) && data[x2][y] == 1) {
            val newNode = structNode(x2, y, head)
            if (!head.reference.contains(newNode)) {
                head.reference.add(newNode)
                checkAdjacentIndex(head, x2, y)
            }
        }

        val y2 = y + 1
        if (validateRange(x, y2) && data[x][y2] == 1) {
            val newNode = structNode(x, y2, head)
            if (!head.reference.contains(newNode)) {
                head.reference.add(newNode)
                checkAdjacentIndex(head, x, y2)
            }
        }

        val x1 = x - 1
        if (validateRange(x1, y) && data[x1][y] == 1) {
            val newNode = structNode(x1, y, head)
            if (!head.reference.contains(newNode)) {
                head.reference.add(newNode)
                checkAdjacentIndex(head, x1, y)
            }
        }

        val y1 = y - 1
        if (validateRange(x, y1) && data[x][y1] == 1) {
            val newNode = structNode(x, y1, head)
            if (!head.reference.contains(newNode)) {
                head.reference.add(newNode)
                checkAdjacentIndex(head, x, y1)
            }
        }
    }


    private fun validateRange(x: Int, y: Int): Boolean {
        return x in data.indices && y in 0 until dataSize
    }

    private fun getIndex(x: Int, y: Int): Int {
        return ((x * dataSize) + y)
    }

    private fun structNode(x: Int, y: Int, head: Node? = null): Node {
        val coordinate = Cell(x, y)
        val index = getIndex(x, y)
        return Node(head, index, coordinate)
    }
}

data class Node(val head: Node? = null, val index: Int, val coordinate: Cell) {
    val reference = mutableListOf<Node>()
}

data class Cell(val x: Int, val y: Int)