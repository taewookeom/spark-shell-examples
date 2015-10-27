val textFile = sc.textFile("README.md", 4)
val words = textFile.flatMap(line => line.split("[\\s]+"))
val realWords = words.filter(_.nonEmpty)
realWords.cache()
val wordTuple = realWords.map(word => (word, 1))
wordTuple.cache()

val groupBy = wordTuple.groupByKey(2)
val wordCount = groupBy.mapValues(value => value.reduce(_ + _))

wordCount.collect().sortBy(-_._2)
val wordCountReduceByKey = wordTuple.reduceByKey(_ + _, 2)

wordCountReduceByKey.collect().sortBy(-_._2)
realWords.countByValue().toArray.sortBy(-_._2)
