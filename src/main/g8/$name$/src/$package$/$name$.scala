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

class SparkJob(master: String, name: String) {
  val logger = LoggerFactory.getLogger("SparkJob")

  var spark: SparkSession = _
  var standalone = false

  def getSparkSession(master: String): SparkSession = {
    logger.info("Initializing Spark Session")
    val builder = SparkSession.builder().appName(name)

    if (master.length > 0) {
      standalone = true
      builder.master(master)
    }

    val spark = builder.getOrCreate()
    logger.info("Successfuly initilized Spark Session")
    return spark
  }

  def cleanup() = {
    if (standalone) {
      logger.info("Stopping Spark")
      spark.stop()
    }
  }

  def run() {
    spark = getSparkSession(master)

    spark.sparkContext.range(0, 10).collect().foreach((println))

    cleanup()
  }
}

object $name$ {
  val logger = LoggerFactory.getLogger("$name$")

  def main(args: Array[String]) = {
    logger.debug("java version  " + System.getProperty("java.runtime.version"))
    logger.debug("scala " + scala.util.Properties.versionString)

    val master = if (args.length == 1) args(0) else ""
    new SparkJob(master, "$name$").run()

    logger.info("Done")
  }
}
