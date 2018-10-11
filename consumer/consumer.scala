import org.apache.spark.sql.types._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.functions.{explode, split}
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.catalyst.ScalaReflection
import java.sql.Timestamp

val spark = SparkSession.builder.appName("Spark-consumer").master("local[*]").getOrCreate()

import spark.implicits._

case class ItemStock(name: String, value: Int)
case class StockPrice(ticket: List[ItemStock])

val valueSchema = ScalaReflection.schemaFor[StockPrice].dataType.asInstanceOf[StructType]

val dfData = spark.readStream.format("kafka").option("kafka.bootstrap.servers", "171.69.1.4:9092").option("subscribe", "stocks").load().selectExpr("CAST(value AS STRING)", "CAST(timestamp AS Timestamp)").as[(String, Timestamp)].select(from_json($"value", valueSchema).as("parsed_data"), $"timestamp").select("parsed_data.*", "timestamp").withColumn("ticket", explode(col("ticket"))).select("ticket.*", "timestamp")

dfData.groupBy($"name", window($"timestamp","30 second").as("window_interval")).avg("value").sort(desc("window_interval"), $"name").writeStream.format("console").option("truncate", false).trigger(Trigger.ProcessingTime("30 seconds")).outputMode("complete").option("numRows", 10).start().awaitTermination()
