import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

fun main() = runBlocking {
    // Список сайтов для проверки
    val websites = listOf(
        "https://www.google.com",
        "https://www.facebook.com",
        "https://www.github.com",
        "https://www.twitter.com",
        "https://www.instagram.com",
        "https://www.yandex.com",
        "https://www.vk.com",
        "https://www.reddit.com",
        "https://www.stackoverflow.com",
        "https://www.youtube.com"
    )

    // Запуск асинхронной проверки доступности сайтов
    val deferredResults = websites.map { url ->
        async {
            val isAvailable = checkWebsite(url)
            Pair(url, isAvailable)
        }
    }

    // Ожидание завершения всех корутин и вывод результатов
    deferredResults.forEach { deferred ->
        val (url, isAvailable) = deferred.await()
        val status = if (isAvailable) "доступен" else "недоступен"
        println("Сайт $url $status")
    }
}

// Функция для проверки доступности сайта
fun checkWebsite(url: String): Boolean {
    return try {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "HEAD"
        connection.connectTimeout = 5000
        connection.readTimeout = 5000
        connection.responseCode == HttpURLConnection.HTTP_OK
    } catch (e: Exception) {
        false
    }
}