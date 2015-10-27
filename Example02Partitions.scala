val numbers = sc.parallelize(1 to 10)
numbers.partitions.length
numbers.glom().collect()
val numbersWith2Partitions = sc.parallelize(1 to 10, 2)
numbersWith2Partitions.partitions.length
numbersWith2Partitions.glom().collect()
