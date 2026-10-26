package lab2

fun selectProducts(
    products: List<Product>,
    recommendedIds: List<Long>,
    excludedIds: List<Long>
): List<Product> {
    val productsById = products.associateBy { it.id }
    val selection = mutableListOf<Product>()
    val seen = HashSet<Long>()
    val excludedSet = excludedIds.toHashSet()

    for (id in recommendedIds) {
        if (!seen.add(id)) continue
        val product = productsById[id] ?: continue
        if (product.available && id !in excludedSet) { selection.add(product) }
    }

    return selection
}
