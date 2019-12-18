# A simple smoke test of Apache Spark Java API via Kotlin.

To run the single unit test:

```sh
$ gradle test
```

To build the consolidated jarfile:

```sh
$ gradle shadow
```

To submit the fat jar to Spark (being sure to build via `shadow` first):

```sh
$ spark-submit \
    --class "com.damienstanton.spark.Main" \
    --master local \ # or whatever node you are submitting to
    build/libs/spark-1.0-SNAPSHOT-all.jar \
    src/main/resources/words.txt # or whatever text file is used for testing
```

© 2019 Damien Stanton

See LICENSE for details.
