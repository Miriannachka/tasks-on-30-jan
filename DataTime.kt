import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.random.Random

object DateUtils {

    // 1. Основы LocalDate и LocalTime
    fun printCurrentDateTime() {
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        println("Текущая дата и время: ${formatter.format(LocalDateTime.of(currentDate, currentTime))}")
    }


    // 2. Сравнение дат
    fun compareDates(date1: LocalDate, date2: LocalDate): String {
        return when {
            date1.isAfter(date2) -> "Дата 1 больше даты 2"
            date1.isBefore(date2) -> "Дата 1 меньше даты 2"
            else -> "Даты равны"
        }
    }

    // 3. Сколько дней до Нового года?
    fun daysUntilNewYear(date: LocalDate = LocalDate.now()): Long {
        val newYear = LocalDate.of(date.year + 1, 1, 1)
        return ChronoUnit.DAYS.between(date, newYear)
    }

    // 4. Проверка високосного года
    fun isLeapYear(year: Int): Boolean {
        return Year.of(year).isLeap
    }

    // 5. Подсчет выходных за месяц
    fun countWeekendDays(month: Int, year: Int): Int {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
        var weekendDays = 0
        var currentDay = firstDayOfMonth

        while (!currentDay.isAfter(lastDayOfMonth)) {
            if (currentDay.dayOfWeek == DayOfWeek.SATURDAY || currentDay.dayOfWeek == DayOfWeek.SUNDAY) {
                weekendDays++
            }
            currentDay = currentDay.plusDays(1)
        }
        return weekendDays
    }

    // 6. Расчет времени выполнения метода
    fun <T> measureExecutionTime(block: () -> T): Pair<T, Long> {
        val startTime = System.currentTimeMillis()
        val result = block()
        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime
        return Pair(result, executionTime)
    }

    // 7. Форматирование и парсинг даты
    fun formatDateAndParse(dateString: String) {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(dateString, formatter)
        val newDate = date.plusDays(10)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        println("Исходная дата: $dateString, Новая дата: ${newDate.format(outputFormatter)}")
    }


    // 8. Конвертация между часовыми поясами
    fun convertTimeZone(dateTime: LocalDateTime, fromZone: ZoneId, toZone: ZoneId): ZonedDateTime {
        val zonedTime = ZonedDateTime.of(dateTime, fromZone)
        return zonedTime.withZoneSameInstant(toZone)
    }

    // 9. Вычисление возраста по дате рождения
    fun calculateAge(birthDate: LocalDate): Int {
        return Period.between(birthDate, LocalDate.now()).years
    }

    // 10. Создание календаря на месяц
    fun printCalendar(month: Int, year: Int) {
        val firstDayOfMonth = LocalDate.of(year, month, 1)
        val lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1)
        var currentDay = firstDayOfMonth

        println("Календарь на ${firstDayOfMonth.month.getDisplayName(java.time.format.TextStyle.FULL_STANDALONE, Locale("ru"))} $year:")
        while (!currentDay.isAfter(lastDayOfMonth)) {
            val dayType = if (currentDay.dayOfWeek == DayOfWeek.SATURDAY || currentDay.dayOfWeek == DayOfWeek.SUNDAY) "Выходной" else "Рабочий"
            println("${currentDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))} - $dayType")
            currentDay = currentDay.plusDays(1)
        }
    }

    // 11. Генерация случайной даты в диапазоне
    fun generateRandomDate(startDate: LocalDate, endDate: LocalDate): LocalDate {
        val daysBetween = ChronoUnit.DAYS.between(startDate, endDate)
        val randomDays = Random.nextLong(daysBetween + 1)
        return startDate.plusDays(randomDays)
    }


    // 12. Расчет времени до заданной даты
    fun timeUntil(dateTime: LocalDateTime): String {
        val now = LocalDateTime.now()
        val duration = Duration.between(now, dateTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds)
    }


    // 13. Вычисление количества рабочих часов
    fun calculateWorkHours(start: LocalDateTime, end: LocalDateTime): Long {
        var current = start
        var totalHours = 0L
        while(current.isBefore(end)){
            if(current.dayOfWeek != DayOfWeek.SATURDAY && current.dayOfWeek != DayOfWeek.SUNDAY){
                totalHours += 1
            }
            current = current.plusHours(1)
        }
        return totalHours
    }


    // 14. Конвертация даты в строку с учетом локали
    fun formatDateWithLocale(date: LocalDate, locale: Locale): String {
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale)
        return date.format(formatter)
    }


    // 15. Определение дня недели по дате
    fun getDayOfWeekRussian(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek
        return when (dayOfWeek) {
            DayOfWeek.MONDAY -> "Понедельник"
            DayOfWeek.TUESDAY -> "Вторник"
            DayOfWeek.WEDNESDAY -> "Среда"
            DayOfWeek.THURSDAY -> "Четверг"
            DayOfWeek.FRIDAY -> "Пятница"
            DayOfWeek.SATURDAY -> "Суббота"
            DayOfWeek.SUNDAY -> "Воскресенье"
        }
    }
}

fun main() {

    // 1. Основы LocalDate и LocalTime
    DateUtils.printCurrentDateTime()


    // 2. Сравнение дат
    val date1 = LocalDate.of(2023, 10, 20)
    val date2 = LocalDate.of(2023, 10, 25)
    println(DateUtils.compareDates(date1, date2))

    // 3. Сколько дней до Нового года?
    val daysToNewYear = DateUtils.daysUntilNewYear()
    println("Дней до Нового года: $daysToNewYear")

    // 4. Проверка високосного года
    println("2024 год - високосный: ${DateUtils.isLeapYear(2024)}")
    println("2023 год - високосный: ${DateUtils.isLeapYear(2023)}")

    // 5. Подсчет выходных за месяц
    println("Выходных в октябре 2023: ${DateUtils.countWeekendDays(10, 2023)}")

    // 6. Расчет времени выполнения метода
    val (result, time) = DateUtils.measureExecutionTime {
        var sum = 0
        for (i in 1..1000000) {
            sum += i
        }
        sum
    }
    println("Результат: $result, Время выполнения: $time мс")


    // 7. Форматирование и парсинг даты
    DateUtils.formatDateAndParse("25-10-2023")

    // 8. Конвертация между часовыми поясами
    val utcDateTime = LocalDateTime.of(2023, 11, 1, 12, 0)
    val moscowTime = DateUtils.convertTimeZone(utcDateTime, ZoneId.of("UTC"), ZoneId.of("Europe/Moscow"))
    println("Время в Москве: $moscowTime")

    // 9. Вычисление возраста по дате рождения
    val birthDate = LocalDate.of(1990, 5, 15)
    println("Возраст: ${DateUtils.calculateAge(birthDate)} лет")


    // 10. Создание календаря на месяц
    DateUtils.printCalendar(11, 2023)

    // 11. Генерация случайной даты в диапазоне
    val startDate = LocalDate.of(2023, 1, 1)
    val endDate = LocalDate.of(2023, 12, 31)
    val randomDate = DateUtils.generateRandomDate(startDate, endDate)
    println("Случайная дата: ${randomDate}")

    // 12. Расчет времени до заданной даты
    val eventDateTime = LocalDateTime.of(2023, 12, 31, 0, 0,0)
    println("Осталось до события: ${DateUtils.timeUntil(eventDateTime)}")


    // 13. Вычисление количества рабочих часов
    val workStart = LocalDateTime.of(2023,11,6,9,0)
    val workEnd = LocalDateTime.of(2023,11,10,17,0)
    println("Рабочих часов: ${DateUtils.calculateWorkHours(workStart, workEnd)}")



    // 14. Конвертация даты в строку с учетом локали
    val localeDate = LocalDate.of(2023,11,6)
    println("Дата с учетом локали ru: ${DateUtils.formatDateWithLocale(localeDate, Locale("ru"))}")
    println("Дата с учетом локали en: ${DateUtils.formatDateWithLocale(localeDate, Locale("en"))}")


    // 15. Определение дня недели по дате
    val someDate = LocalDate.of(2023,11,6)
    println("День недели: ${DateUtils.getDayOfWeekRussian(someDate)}")
}