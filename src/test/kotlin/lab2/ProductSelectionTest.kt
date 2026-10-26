package lab2

import lab2.Product
import org.junit.jupiter.api.Assertions.assertEquals
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

/* TODO: проверять тестами
    V обычную подборку и сохранение порядка при другом порядке каталога;
    V повторяющиеся рекомендации и исключения;
    V недоступные, исключённые и неизвестные товары;
    X пустой каталог, пустые рекомендации и пустые исключения;
    X случай, когда ни один товар не подходит;
    X сохранность входных данных и независимость результатов повторных вызовов.
 */
}
