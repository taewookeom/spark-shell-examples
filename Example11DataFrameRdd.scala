import sqlContext.implicits._
case class Person(name: String, age: Int)
val people = sc.textFile("examples/src/main/resources/people.txt").map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt)).toDF()
people.registerTempTable("people")
val teenagers = sqlContext.sql("SELECT name, age FROM people WHERE age >= 13 AND age <= 19")
teenagers.show()
val teenagersRdd = teenagers.rdd
teenagersRdd.toDebugString
teenagersRdd.collect()
