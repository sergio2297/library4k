package es.sfernandez.libraries.library4k

import java.io.*
import java.nio.charset.StandardCharsets

object FileResourceUtils {

    /**
     * Search the given filePath in src/main/resources. Works properly in any IDE, testing and .jar
     * @param filePath It must start from resources
     * @return InputStream with the file content
     * @throws IllegalArgumentException if no file found with the given path
     */
    fun getFileFromResourceAsStream(filePath: String): InputStream {
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream(filePath)
        return inputStream
            ?: throw IllegalArgumentException("Fichero no encontrado en la carpeta de resources. ($filePath)")
    }

    /**
     * Copy the inputStream's content in the received file.
     *
     * All previous data stored in the file will be overwritten.
     * @param inputStream InputStream to copy
     * @param file File where the inputStream will be copied
     * @throws IOException if something goes wrong during the process. i.e.: the file doesn't exists.
     */
    @Throws(IOException::class)
    fun copyInputStreamToFile(inputStream: InputStream, file: File) {
        writeInputStreamToFile(inputStream, file, false)
    }

    /**
     * Concat to the end of the file the inputStream's content.
     * @param inputStream InputStream to concat
     * @param file File where the inputStream will be print
     * @throws IOException if something goes wrong during the process. i.e.: the file doesn't exists.
     */
    @Throws(IOException::class)
    fun appendInputStreamToFile(inputStream: InputStream, file: File) {
        writeInputStreamToFile(inputStream, file, true)
    }

    @Throws(IOException::class)
    private fun writeInputStreamToFile(inputStream: InputStream, file: File, append: Boolean) {
        FileOutputStream(file, append).use { outputStream ->
            var read: Int
            val bytes = ByteArray(DEFAULT_BUFFER_SIZE)
            while (inputStream.read(bytes).also { read = it } != -1) {
                outputStream.write(bytes, 0, read)
            }
        }
    }

    /**
     *
     * Print the received InputStream.
     * @param inputStream InputStream to print
     */
    fun printInputStream(inputStream: InputStream?) {
        try {
            if (inputStream != null) {
                InputStreamReader(inputStream, StandardCharsets.UTF_8).use { streamReader ->
                    BufferedReader(streamReader).use { reader ->
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            println(line)
                        }
                    }
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
