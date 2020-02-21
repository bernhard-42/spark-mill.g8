/**
    Copyright 2020 Bernhard Walter

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

import java.io.File

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

import com.typesafe.scalalogging.Logger

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object $name$ {
    val logger = Logger("$name$")

    def main(args: Array[String]) {
        logger.debug(s"Starting Spark")

        val appName = "SparkApp"
        val master = "local[*]"

        val spark = SparkSession
            .builder()
            .appName(appName)
            .master(master)
            .getOrCreate()
        val sc = spark.sparkContext
        logger.debug("Spark Context created")

        sc.range(0,10).collect().foreach((println))

        logger.debug(s"Stopping Spark")
        spark.stop()
        logger.debug(s"Done")
    }
}

