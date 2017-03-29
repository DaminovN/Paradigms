Тесты к курсу «Парадигмы программирования»
====

[Условия домашних заданий](http://www.kgeorgiy.info/courses/paradigms/homeworks.html)

Домашнее задание 5. Вычисление выражений
---
 * *Базовая*
    * Реализовать интерфейс [Expression](java/expression/Expression.java)
    * [Исходный код тестов](java/expression/ExpressionTest.java)
 * *Простая*
    * Реализовать интерфейс [DoubleExpression](java/expression/DoubleExpression.java)
    * [Исходный код тестов](java/expression/DoubleExpressionTest.java)
 * *Усложненная*
    * Реализовать интерфейсы [DoubleExpression](java/expression/DoubleExpression.java) и [TripleExpression](java/expression/TripleExpression.java)
    * [Исходный код тестов](java/expression/TripleExpressionTest.java)

Домашнее задание 4. Очередь на связном списке
---
 * *Базовая*
    * [Исходный код тестов](java/queue/QueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueTest.jar)
 * *Простая*
    * Добавить в интерфейс очереди и реализовать метод
      `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту
    * Исходная очередь должна оставаться неизменной
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/QueueToArrayTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueToArrayTest.jar)
 * *Усложненная*
    * Добавить в интерфейс очереди и реализовать методы
        * `filter(predicate)` – создать очередь, содержащую элементы, удовлетворяющие 
            [предикату](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html)
        * `map(function)` – создать очередь, содержащую результаты применения 
            [функции](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
    * Исходная очередь должна остаться неизменной
    * Тип возвращаемой очереди должен соответствовать типу исходной очереди
    * Взаимный порядок элементов должен сохраняться
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/QueueFunctions.java)
    * [Откомпилированные тесты](artifacts/queue/QueueFunctionsTest.jar)

Домашнее задание 3. Очередь на массиве
---
Модификации
 * *Базовая*
    * [Исходный код тестов](java/queue/ArrayQueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueTest.jar)
 * *Простая*
    * Реализовать метод `toArray`, возвращающий массив,
      содержащий элементы, лежащие в очереди в порядке
      от головы к хвосту.
    * Исходная очередь должна остаться неизменной
    * Дублирования кода быть не должно
    * [Исходный код тестов](java/queue/ArrayQueueToArrayTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueToArrayTest.jar)
 * *Простая 2*
    * Реализовать метод `toStr`, возвращающий сроковое представление
      очереди в виде '`[`' _голова_ '`, `' ... '`, `' _хвост_ '`]`'
    * Для получения строкового представления элементов используйте
      метод `toString`.
    * [Исходный код тестов](java/queue/ArrayQueueToStrTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueToStrTest.jar)
 * *Усложненная*
    * Реализовать методы
        * `push` – добавить элемент в начало очереди
        * `peek` – вернуть последний элемент в очереди
        * `remove` – вернуть и удалить последний элемент из очереди
    * [Исходный код тестов](java/queue/ArrayQueueDequeTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueDequeTest.jar)


Домашнее задание 2. Бинарный поиск
----

Модификации
 * *Базовая*
    * [Исходный код тестов](java/search/BinarySearchTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchTest.jar)
 * *Простая*
    * Если в массиве `a` отсутствует элемент, равный `x`, то требуется
      вывести индекс вставки в формате, определенном в 
      [`Arrays.binarySearch`](http://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#binarySearch-int:A-int-).
    * Класс должен иметь имя `BinarySearchMissing`
    * [Исходный код тестов](java/search/BinarySearchMissingTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchMissingTest.jar)
 * *Усложненная*
    * Требуется вывести два числа: начало и длину диапазона элементов,
      равных `x`. Если таких элементов нет, то следует вывести
      пустой диапазон, у которого левая граница совпадает с местом
      вставки элемента `x`.
    * Не допускается использование типов `long` и `BigInteger`.
    * Класс должен иметь имя `BinarySearchSpan`
    * [Исходный код тестов](java/search/BinarySearchSpanTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchSpanTest.jar)


Домашнее задание 1. Сумма чисел
----

Модификации
 * *Простая*
    * Входные данные помещаются в тип `long`
    * Класс должен иметь имя `SumLong`
    * [Исходный код тестов](java/sum/SumLongTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongTest.jar)
 * *Усложненная*
    * Входные данные помещаются в тип `long`
    * На вход подаются десятичные и шестнадцатеричные числа
    * Шестнадцатеричные числа имеют префикс `0x`
    * Ввод регистронезависим
    * Класс должен иметь имя `SumLongHex`
    * [Исходный код тестов](java/sum/SumLongHexTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongHexTest.jar)

Для того, чтобы протестировать исходную программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/sum/SumTest.jar))
 * Откомпилируйте `Sum.java`
 * Проверьте, что создался `Sum.class`
 * В каталоге, в котором находится `Sum.class` выполните команду 
    ```
       java -jar <путь к SumTest.jar>
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду 
    ```
        java -jar SumTest.jar
    ```
    
Исходный код тестов: 

* [SumTest.java](java/sum/SumTest.java), 
* [SumChecker.java](java/sum/SumChecker.java)
