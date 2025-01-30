// Задача 1. Создание класса базы данных
object DatabaseConnection {
    init {
        println("Подключение к базе данных создано.")
    }

    fun connect() {
        println("Подключено к базе данных.")
    }
}

// Задача 2. Логирование в системе
object Logger {
    private val logs = mutableListOf<String>()

    fun log(message: String) {
        logs.add(message)
    }

    fun printLogs() {
        logs.forEach { println(it) }
    }
}

// Задача 3. Реализация статусов заказа
enum class OrderStatus {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}

class Order(var status: OrderStatus = OrderStatus.NEW) {
    fun changeStatus(newStatus: OrderStatus) {
        if (status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
            println("Невозможно отменить доставленный заказ.")
            return
        }
        status = newStatus
        println("Статус заказа изменен на: $status")
    }

    fun displayStatus() {
        println("Текущий статус заказа: $status")
    }
}

// Задача 4. Сезоны года
enum class Season {
    WINTER, SPRING, SUMMER, AUTUMN
}

fun getSeasonName(season: Season): String {
    return when (season) {
        Season.WINTER -> "Зима"
        Season.SPRING -> "Весна"
        Season.SUMMER -> "Лето"
        Season.AUTUMN -> "Осень"
    }
}

fun main() {
    // Задача 1: Проверка Singleton DatabaseConnection
    val db1 = DatabaseConnection
    val db2 = DatabaseConnection
    db1.connect()
    db2.connect()
    println("Сравнение экземпляров БД: ${db1 === db2}") // Должно быть true, так как это один и тот же объект


    // Задача 2: Проверка Singleton Logger
    Logger.log("Первое сообщение лога")
    Logger.log("Второе сообщение лога")
    Logger.printLogs()


    // Задача 3: Работа со статусами заказа
    val order = Order()
    order.displayStatus()
    order.changeStatus(OrderStatus.IN_PROGRESS)
    order.changeStatus(OrderStatus.DELIVERED)
    order.displayStatus()
    order.changeStatus(OrderStatus.CANCELLED) // Попытка отменить доставленный заказ
    order.displayStatus()

    val order2 = Order()
    order2.changeStatus(OrderStatus.CANCELLED)
    order2.displayStatus()


    // Задача 4: Работа с сезонами года
    val currentSeason = Season.AUTUMN
    println("Текущий сезон: ${getSeasonName(currentSeason)}")
}
