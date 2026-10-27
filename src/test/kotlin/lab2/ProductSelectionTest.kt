package lab2

import lab2.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test

class ProductSelectionTest {
    @Test
    fun selectsAvailableProductsInRecommendationOrder() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", false)
        val monitor = Product(3, "Monitor", true)
        val webcam = Product(4, "Webcam", true)
        val products = listOf(keyboard, mouse, monitor, webcam)

        val result = selectProducts(
            products,
            listOf(3L, 2L, 3L, 99L, 1L, 4L),
            listOf(1L)
        )

        assertEquals(listOf(monitor, webcam), result)
    }

    @Test
    fun returnsEmptyListWhenThereAreNoRecommendations() {
        val products = listOf(Product(1, "Keyboard", true))

        val result = selectProducts(products, emptyList(), emptyList())

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnsEmptyListWhenCatalogIsEmptyAndRecommendationsArePresent() {
        val result = selectProducts(
            products = emptyList(),
            recommendedIds = listOf(1L, 2L),
            excludedIds = emptyList()
        )

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun selectsProductsWhenExcludedListIsEmpty() {
        val keyboard = Product(1, "Keyboard", true)
        val mouse = Product(2, "Mouse", true)
        val products = listOf(keyboard, mouse)

        val result = selectProducts(
            products = products,
            recommendedIds = listOf(2L, 1L),
            excludedIds = emptyList()
        )

        assertEquals(listOf(mouse, keyboard), result)
    }

    @Test
    fun returnDefaultSelectionWithDifferentCatalogue() {
        val mouse = Product(2, "Mouse", false)
        val keyboard = Product(1, "Keyboard", true)
        val webcam = Product(4, "Webcam", true)
        val monitor = Product(3, "Monitor", true)
        val products = listOf(keyboard, mouse, monitor, webcam)

        val result = selectProducts(
            products,
            listOf(3L, 2L, 3L, 99L, 1L, 4L),
            listOf(1L)
        )

        assertEquals(listOf(monitor, webcam), result)
    }

    @Test
    fun returnEmptyListWithEqualsRecommendationsAndExcludes() {
        val products = listOf(Product(1, "Keyboard", true))

        val result = selectProducts(
            products,
            listOf(3L, 2L, 3L, 99L, 1L, 4L),
            listOf(3L, 2L, 3L, 99L, 1L, 4L)
        )

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnEmptyListWhenThereAreNoAvailableOrNotExcludedOrNoCorrectProducts() {
        val products = listOf(
            Product(2, "Mouse", false),
            Product(3, "Monitor", true)
        )

        val result = selectProducts(
            products,
            recommendedIds = listOf(1L, 4L),
            excludedIds = listOf(3L)
        )

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun returnEmptyListWhenEmptyEntry() {
        val products = emptyList<Product>()
        val recommendedIds = emptyList<Long>()
        val excludedIds = emptyList<Long>()

        val result = selectProducts(products, recommendedIds, excludedIds)

        assertEquals(emptyList<Product>(), result)
    }

    @Test
    fun safetyOfEntryObject() {
        val products = listOf(
            Product(1, "Keyboard", true),
            Product(2, "Mouse", false),
            Product(3, "Monitor", true),
            Product(4, "Webcam", true)
        )
        val productsCopy = products.toList()

        val recommendedIds = listOf(3L, 2L, 3L, 99L, 1L, 4L)
        val recommendedIdsCopy = recommendedIds.toList()

        val excludedIds = listOf(1L)
        val excludedIdsCopy = excludedIds.toList()

        val check = selectProducts(products, recommendedIds, excludedIds)

        assertEquals(productsCopy, products)
        assertEquals(recommendedIdsCopy, recommendedIds)
        assertEquals(excludedIdsCopy, excludedIds)
    }

    @Test
    fun independenceOfResultsObjects() {
        val products = listOf(
            Product(1, "Keyboard", true),
            Product(2, "Mouse", false),
            Product(3, "Monitor", true),
            Product(4, "Webcam", true)
        )
        val recommendedIds = listOf(3L, 2L, 3L, 99L, 1L, 4L)
        val excludedIds = listOf(1L)

        val result_1 = selectProducts(products, recommendedIds, excludedIds)
        val result_2 = selectProducts(products, recommendedIds, excludedIds)

        assertEquals(result_1, result_2)
        assertNotSame(result_1, result_2)
    }
}
