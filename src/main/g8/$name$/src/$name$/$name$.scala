/**
    Copyright 2018 Bernhard Walter

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

import org.apache.spark.SparkConf
import org.apache.spark.sql
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

// For implicit conversions like converting RDDs to DataFrames
// import org.apache.spark.implicits._

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

import org.apache.log4j.{Level, Logger}


object Test2 {

    def main(args: Array[String]) {
        val appName = "MyApp"
        val warehouseLocation = new File("spark-warehouse").getAbsolutePath

        val spark = SparkSession
            .builder()
            .appName(appName)
            .config("spark.sql.warehouse.dir", warehouseLocation)
            .config("spark.master", "yarn")
            .config("spark.submit.deployMode", "client")
            .config("spark.hadoop.yarn.resourcemanager.hostname", "$resourcemanager$")
            .config("spark.hadoop.yarn.resourcemanager.address", "$resourcemanager$:$resourcemanager_port$")
            .config("spark.yarn.access.hadoopFileSystems", "$hadoop_fs$")
            .config("spark.yarn.stagingDir", "$hadoop_fs$/user/$hadoop_user$/")

            // HDP Specials
            .config("spark.yarn.archive", "hdfs://master1.localdomain:8020/hdp/apps/$hdp_version$/spark2/spark2-hdp-yarn-archive.tar.gz")
            .config("spark.driver.extraJavaOptions", "-Dhdp.version=$hdp_version$")
            .config("spark.yarn.am.extraJavaOptions", "-Dhdp.version=$hdp_version$")

            .enableHiveSupport()
            .getOrCreate()

        spark.sparkContext.setLogLevel("ERROR")

        println(s"Hello Spark")
        spark.stop()
    }
}

