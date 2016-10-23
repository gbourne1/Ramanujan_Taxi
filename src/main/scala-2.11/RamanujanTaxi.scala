/**
  * Created by Geoffrey Bourne on 10/6/16.
  */

/*
* Ramanujan's Taxi
*
* Implementation of Ramanujan's Taxi in three different ways, seeing which algorithm has the best performance.
*
* G.H. Hardy: "I remember once going to see him when he was ill at Putney. I had ridden in taxi cab number 1729 and
* remarked that the number seemed to me rather a dull one, and that I hoped it was not an unfavorable omen.
* "No," he replied, "it is a very interesting number; it is the smallest number expressible as the sum of two cubes
* in two different ways." https://en.wikipedia.org/wiki/1729_(number)
*
* a^3 + b^3 = c^3 + d^3
* Where:
* a^3 <= n
* b^3 <= n
* c^3 <= n
* d^3 <= n
* a != c  & a != d
* b != c  & b != d
*/

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.SortedSet
import java.time.Instant
import java.time.Duration

object RamanujanTaxi {
  def main(args: Array[String]): Unit = {
    val n = 10000L

    println("Ramunajun's Taxi O(n^4)")
    time(ramtaxi(n))
    println("~~~~~~~~~~~~~~~~" + "\n")

    println("Ramunajun's Taxi O(n^3)")
    time(ramtaxi_O3(n))
    println("~~~~~~~~~~~~~~~~" + "\n")

    println("Ramunajun's Taxi O(n^2)")
    time(ramtaxi_O2(n))
    println("~~~~~~~~~~~~~~~~" + "\n")
  }

  // Timer of performance
  def time[T](op: => T): T = {
    val start = Instant.now
    val result = op
    println(Duration.between(Instant.now,start))
    result
  }

  // O(n^4)
  // Straight forward implementation
  def ramtaxi(n: Long) = {
    for (a <- 1L to n) {
      val a3 = a * a * a
      if (a3 <= n)
        for (b <- a to n) {             //start at 'a' so no dups
        val b3 = b * b * b
          if (b3 <= n)
            for (c <- a + 1 to n) {     //start at offset from 'a' so no dups
            val c3 = c * c * c
              if (c3 <= n)
                for (d <- c to n) {     //start at 'c' so no dups
                val d3 = d * d * d
                  if (d3 <= n && a3 + b3 == c3 + d3) {
                    println(a3 + b3 + " = " + a + "^3 + " + b + "^3 = " + c + "^3 + " + d + "^3")
                  }
                }
            }
        }
    }
  }

  // O(n^3)
  // Same as O(n^4), but calculate d^3 instead of testing for it
  def ramtaxi_O3(n: Long) = {
    for (a <- 1L to n) {
      val a3 = a * a * a
      if (a3 <= n)
        for (b <- a to n) {           //start at 'a' so no dups
        val b3 = b * b * b
          if (b3 <= n)
            for (c <- a + 1 to n) {   //start at offset from 'a' so no dups
            val c3 = c * c * c
              if (c3 <= n) {
                val d = Math.pow(a3 + b3 - c3, 1.0 / 3.0).toLong // calculate d^3 = a^3 + b^3 - c^3
                if (d != a && d != b) {
                  val d3 = d * d * d
                  if (d3 <= n && a3 + b3 == c3 + d3)
                    println(a3 + b3 + " = " + a + "^3 + " + b + "^3 = " + c + "^3 + " + d + "^3")
                }
              }
            }
        }
    }
  }

  // O(n^2)
  // Create a hashmap of all the results and track those that have >1 solutions
  def ramtaxi_O2(n: Long) = {
    val mapSums = mutable.HashMap.empty[Long, List[(Long, Long)]]
    var overOne = mutable.SortedSet[Long]()
    for (a <- 1L to n) {
      val a3 = a * a * a
      if (a3 <= n)
        for (b <- a to n) {
          val b3 = b * b * b
          if (b3 <= n) {
            val ab3 = a3 + b3
            mapSums += (ab3 -> ((a3, b3) :: mapSums.getOrElse(ab3, List())))
            if (mapSums(ab3).length > 1)
              overOne += ab3
          }
        }
    }
    overOne foreach {x => println(x + " = " + mapSums(x))}
  }
}
