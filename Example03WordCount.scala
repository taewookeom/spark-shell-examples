val textFile = sc.textFile("README.md", 4)
val words = textFile.flatMap(line => line.split("[\\s]+"))
val realWords = words.filter(_.nonEmpty)
val wordTuple = realWords.map(word => (word, 1))
val groupBy = wordTuple.groupByKey(2)
val wordCount = groupBy.mapValues(value => value.reduce(_ + _))
wordCount.collect().sortBy(-_._2)

wordCount.saveAsTextFile("wordcount.txt")
