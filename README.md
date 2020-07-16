# Util

Scala that could be useful for any project.

## Concurrency
LimitedExecution and implementations that can wrap functions that return futures and limit the number of
functions that are running.

## Hash
Hashing algorithms for various object types

## IO
IO-related classes.  Notably the package includes the function `stringToInputStream` that will create an InputStream
for a string that can greatly aid testing in this area.

## Models
Notably includes IdIterator for generating a unique sequence of ids.

## Path
DirectoryDeleter

## Time
ImplicitOrdering has ordering of LocalDateTime.

Now is a trait that gives the current date time.  AdvancingNow is an implementation useful for testing.

For setting a fixed time use:
```
import io.mdcatapult.util.time.nowUtc
val currentTime = nowUtc.now()
val time: Now = () => currentTime
```
