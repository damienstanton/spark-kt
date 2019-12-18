import junit.framework.Assert.assertEquals
import org.apache.spark.sql.SparkSession
import org.junit.jupiter.api.Test

class SmokeTest {
    @Test
    fun smokeTest() {
        val file = this::class.java.getResource("/words.txt").toString()
        val spark = SparkSession
            .builder()
            .appName("Kotlin Smoke Test")
            .config("spark.master", "local")
            .orCreate
        val logData = spark
            .read()
            .textFile(file)
            .cache()

        val numAs = logData.filter{ it.contains("a") }.count()
        val numBs = logData.filter{ it.contains("b") }.count()

        assertEquals(1, numAs)
        assertEquals(0, numBs)

        spark.stop()
    }
}