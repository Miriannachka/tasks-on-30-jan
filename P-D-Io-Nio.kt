//Задание 1: Работа с потоками ввода-вывода
import java.io.*

fun main() {
    val inputFile = "input.txt"
    val outputFile = "output.txt"

    try {
        BufferedReader(FileReader(inputFile)).use { reader ->
            BufferedWriter(FileWriter(outputFile)).use { writer ->
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    writer.write(line!!.toUpperCase())
                    writer.newLine()
                }
            }
        }
        println("Файл успешно обработан и записан в $outputFile")
    } catch (e: FileNotFoundException) {
        println("Ошибка: Файл не найден - ${e.message}")
    } catch (e: IOException) {
        println("Ошибка ввода-вывода: ${e.message}")
    }
}
//Задание 2: Реализация паттерна Декоратор
//interface TextProcessor {
//    fun process(text: String): String
//}
//
//class SimpleTextProcessor : TextProcessor {
//    override fun process(text: String): String = text
//}
//
//class UpperCaseDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String = processor.process(text).toUpperCase()
//}
//
//class TrimDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String = processor.process(text).trim()
//}
//
//class ReplaceSpaceDecorator(private val processor: TextProcessor) : TextProcessor {
//    override fun process(text: String): String = processor.process(text).replace(" ", "_")
//}
//
//fun main() {
//    val processor: TextProcessor = ReplaceSpaceDecorator(UpperCaseDecorator(TrimDecorator(SimpleTextProcessor())))
//    val result = processor.process(" Привет, мир! ")
//    println(result)  // Вывод: ПРИВЕТ,_МИР!
//}

//Задание 3: Сравнение производительности IO и NIO
//import java.io.*
//import java.nio.ByteBuffer
//import java.nio.channels.FileChannel
//import java.nio.file.Files
//import java.nio.file.Paths
//
//fun main() {
//    val inputFile = "large_input.txt"
//    val outputFileIO = "output_io.txt"
//    val outputFileNIO = "output_nio.txt"
// IO
//    val startTimeIO = System.currentTimeMillis()
//    try {
//        BufferedReader(FileReader(inputFile)).use { reader ->
//            BufferedWriter(FileWriter(outputFileIO)).use { writer ->
//                var line: String?
//                while (reader.readLine().also { line = it } != null) {
//                    writer.write(line)
//                    writer.newLine()
//                }
//            }
//        }
//    } catch (e: IOException) {
//        println("Ошибка ввода-вывода: ${e.message}")
//    }
//    val endTimeIO = System.currentTimeMillis()
//    println("Время выполнения IO: ${endTimeIO - startTimeIO} мс")
//
//    // NIO
//    val startTimeNIO = System.currentTimeMillis()
//    try {
//        FileChannel.open(Paths.get(inputFile)).use { inputChannel ->
//            FileChannel.open(Paths.get(outputFileNIO), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.WRITE).use { outputChannel ->
//                val buffer = ByteBuffer.allocate(1024)
//                while (inputChannel.read(buffer) > 0) {
//                    buffer.flip()
//                    outputChannel.write(buffer)
//                    buffer.clear()
//                }
//            }
//        }
//    } catch (e: IOException) {
//        println("Ошибка ввода-вывода: ${e.message}")
//    }
//    val endTimeNIO = System.currentTimeMillis()
//    println("Время выполнения NIO: ${endTimeNIO - startTimeNIO} мс")
//}
//Задание 4: Программа с использованием Java NIO
//import java.io.IOException
//import java.nio.channels.FileChannel
//import java.nio.file.Paths
//import java.nio.file.StandardOpenOption
//
//fun main() {
//    val sourceFile = "source.txt"
//    val destFile = "destination.txt"
//
//    try {
//        FileChannel.open(Paths.get(sourceFile), StandardOpenOption.READ).use { srcChannel ->
//            FileChannel.open(Paths.get(destFile), StandardOpenOption.CREATE, StandardOpenOption.WRITE).use { destChannel ->
//                srcChannel.transferTo(0, srcChannel.size(), destChannel)
//            }
//        }
//        println("Файл успешно скопирован с использованием NIO.")
//    } catch (e: IOException) {
//        println("Ошибка ввода-вывода: ${e.message}")
//    }
//}
//Задание 5: Асинхронное чтение файла с использованием NIO.2
//import java.nio.ByteBuffer
//import java.nio.channels.AsynchronousFileChannel
//import java.nio.file.Paths
//import java.nio.file.StandardOpenOption
//import java.util.concurrent.Future
//
//fun main() {
//    val filePath = "large_input.txt"
//
//    try {
//        val fileChannel = AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ)
//        val buffer = ByteBuffer.allocate(1024)
//        val future: Future<Int> = fileChannel.read(buffer, 0)
//
//        while (!future.isDone) {
//            println("Чтение файла...")
//        }
//
//        val bytesRead = future.get()
//        buffer.flip()
//        val data = ByteArray(bytesRead)
//        buffer.get(data)
//        println(String(data))
//
//        fileChannel.close()
//    } catch (e: Exception) {
//        println("Ошибка: ${e.message}")
//    }
//}