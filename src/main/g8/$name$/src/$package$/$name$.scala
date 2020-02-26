/**
    Copyright <YEAR> <NAME>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

  */
package $package$

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object $name$ {
  var standalone = true

  val logger = LoggerFactory.getLogger("$name$")
  var spark: SparkSession = null
  var sc: SparkContext = null

  def getSparkSession(): SparkSession = {
    val builder = SparkSession.builder().appName("$name$")

    if (master.length > 0) {
      builder.master(master)
    }
    return builder.getOrCreate()
  }

  def cleanup() = {
    if (master.length > 0) {
      logger.info("Stopping Spark")
      spark.stop()
    }
    logger.info(s"Done")
  }

  def main(args: Array[String]) = {
    if (args.length > 0) {
      master = args(0)
    }

    logger.debug("java version  " + System.getProperty("java.runtime.version"))
    logger.debug("scala " + scala.util.Properties.versionString)

    logger.info("Initializing Spark Session")
    spark = getSparkSession()
    sc = spark.sparkContext
    logger.info("Successfuly initilized Spark Session")

    sc.range(0, 10).collect().foreach((println))

    cleanup()
  }
}
