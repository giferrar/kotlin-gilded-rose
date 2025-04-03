# Gilded Rose Refactoring Kata 🥋

The purpose of this Workshop is to solve the Gilded Rosa Kata, as well as to learn more about Kotlin testing.

## Rules
The Gilded Rose is a famous refactoring exercise that you can find online in different programming languages. 
The idea behind it is to simulate the situation in which developers find themselves working on a legacy project where they have to implement some new features.
Often the problem with legacy applications is that the code is messy and hard to understand, there are no tests and the requirements are very chatty, unclear or incomplete.

To solve the kata you have to proceed as follows:

- Write test cases that fully covers the code of [GildedRose#updateQuality](src/main/kotlin/GildedRose.kt)
- Refactor the existing code to make it readable and easy to understand and modify, keeping the tests green
- Implement the new feature (using TDD maybe?)

⚠️ Run the tests after every refactoring, to be sure you did not break something.

⚠️ Maintain an orderly project structure and do not create all the classes in the same package.

You can find my solution to the kata together with some commentary in the branche `solutions`.

## Some words about testing

### Reuse Test class
The Test class is annotated with:

> @TestInstance(TestInstance.Lifecycle.PER_CLASS)

With this annotation only one single instance of the test class will be used for every test, otherwise it will be re-initialized before every test.

### Power-assert compiler plugin
In this project we use the Kotlin compiler plugin `power-assert`, an experimental plugin (at the time of writing this project) that improves the logging of failure messages.
The way this plugin works is by transforming some test functions to enhance their error messages.

Here is the plugin configuration in [build.gradle.kts](build.gradle.kts):

>     plugins {
>       kotlin("plugin.power-assert") version "2.1.10"
>     }
>
>     powerAssert {
>       // Here we write the functions we want to transform
>       functions = listOf("kotlin.assert", "kotlin.test.assertTrue", "kotlin.test.assertEquals", "kotlin.test.assertNull")
>     }

### Semantic and Strategy

A very common way to write the test suite to solve this kata is by reading the specifications and extract from them a list of test cases to write.

If this is the approach, it could be useful to group the tests together by scope using `inner class` and the `@Nested` annotation, as follows:

```kotlin
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GildedRoseTest {
  @Nested
  inner class SulfuraItemTests {
    @Test
    fun `my first sulfura test`() {}
    @Test
    fun `my second sulfura test`() {} 
  }
  @Nested
  inner class BrieItemTests {
    @Test
    fun `my first brie test`() {}
    @Test
    fun `my second brie test`() {}
  }
}
```

This is indeed useful if we want to have more order while writing tests for a service or controller with different methods. Please note the use of backticks to write the method name as a description.

If we look at the different characteristics that unit tests may have, as described by Kent Beck in his [TestDesiderata](https://testdesiderata.com/), using this approach we have a high `Composability`. 
This means our tests isolate well different rules or parts of the logic of the code. `Readability` is also improved thanks to the use of backticks.

On the other hand, a problem with this approach is the quantity of tests to write, worsened `Writability`, as well as the reliance on specifications that often are incomplete or difficult to understand.

While this approach could makes sense for new developments, in case of legacy code (and for our workshop too) it is too time-consuming.
We will therefore opt for a different approach, one that emphasize `Writability` at the expenses of `Composability`: we will brute force full code coverage through parametrized tests.

To perform parametrized tests we have to import the following dependency to our [build.gradle.kts](build.gradle.kts) file:

> testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.0") // This is JUnit 5 compatible

We will have a single test function structured as follows:

```kotlin
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GildedRoseTest {
    @ParameterizedTest
    @MethodSource("createItems") // This is the name of s static method
    fun `parametrized example for update quality`(actual: Item, expected: Item) {
        // We create the GildedRose object and call the updateQuality method
        // We test our assertions
    }
  
    companion object {
        @JvmStatic
        fun createItems() = listOf(
            Arguments.of(Item("actual", 0, 0), Item("expected", -1, -1))
        )
    }
}
```

All we will have to do is to add `actual` and `expected` items to our list inside `createItems` method until we reach full coverage. An advantage of this strategy is that we do not really need to spend much time on the specification, looking at the coverage on the method will be enough. 

---
## Gilded Rose Requirements Specification

Hi and welcome to team Gilded Rose. As you know, we are a small inn with a prime location in a
prominent city ran by a friendly innkeeper named Allison. We also buy and sell only the finest goods.
Unfortunately, our goods are constantly degrading in `Quality` as they approach their sell by date.

We have a system in place that updates our inventory for us. It was developed by a no-nonsense type named
Leeroy, who has moved on to new adventures. Your task is to add the new feature to our system so that
we can begin selling a new category of items. First an introduction to our system:

- All `items` have a `SellIn` value which denotes the number of days we have to sell the `items`
- All `items` have a `Quality` value which denotes how valuable the item is
- At the end of each day our system lowers both values for every item

Pretty simple, right? Well this is where it gets interesting:

- Once the sell by date has passed, `Quality` degrades twice as fast
- The `Quality` of an item is never negative
- __"Aged Brie"__ actually increases in `Quality` the older it gets
- The `Quality` of an item is never more than `50`
- __"Sulfuras"__, being a legendary item, never has to be sold or decreases in `Quality`
- __"Backstage passes"__, like aged brie, increases in `Quality` as its `SellIn` value approaches;
    - `Quality` increases by `2` when there are `10` days or less and by `3` when there are `5` days or less but
    - `Quality` drops to `0` after the concert

We have recently signed a supplier of conjured items. This requires an update to our system:

- __"Conjured"__ items degrade in `Quality` twice as fast as normal items

Feel free to make any changes to the `UpdateQuality` method and add any new code as long as everything
still works correctly. However, do not alter the `Item` class or `Items` property as those belong to the
goblin in the corner who will insta-rage and one-shot you as he doesn't believe in shared code
ownership (you can make the `UpdateQuality` method and `Items` property static if you like, we'll cover
for you).

Just for clarification, an item can never have its `Quality` increase above `50`, however __"Sulfuras"__ is a
legendary item and as such its `Quality` is `80` and it never alters.

---

## Sources

[Gilded Rose Refactoring Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata), by Emily Bache

[Best Practices for Unit Testing in Kotlin](https://phauer.com/2018/best-practices-unit-testing-kotlin/), by Philipp Hauer's Blog

[Power-assert compiler plugin](https://kotlinlang.org/docs/power-assert.html)

[TestDesiderata](https://testdesiderata.com/), by Kent Beck

[Best Tests for Gilded Rose Kata | Kent Beck’s Desiderata](https://www.youtube.com/watch?v=vMww6pV6P7s), YouTube video by Emily Bache
