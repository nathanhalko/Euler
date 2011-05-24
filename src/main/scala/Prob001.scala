package com.spotinfluence

object Prob001 {
  def main(av: Array[String]) {

      val N : Int = 1000
      val f1: Int = 5
      val f2: Int = 3
/* -ljr
 *
 * Scala is good about figuring out the type of values so often if
 * the type is clear from the assignment you can leave it off.  I
 * will often decorate the type in there for extra documentation or
 * when the assignment isn't clear though so it's a judgement call
 * on when to do so or when not.
 *
 *    val N = 1000     // Int
 *    val M = 1000000L // Long
 *    val d = 1.0      // Double
 *    val f = 1.0f     // Float
 *    val m = Map("a" -> b)
 *
 *    val xs: Array[Double] = whoKnows()
 *
 */

     /* // val range = 1.until(N)
      var sum = 0

      for (i <- 1 to (N-1)) {
        if (i % f1 == 0 || i % f2 == 0)
          sum = sum + i
      }
      */

    val nums = for {
      n <- 1 until N
      if (n % f1 == 0 || n % f2 == 0)
    } yield n

      println("Sum of multiples of "+f1+" and "+f2+" less than "+N+" is:")
/* -ljr
 * A nice helper for formatting is scala's RichString.format()
 * It's much slower than string concat but is a bit easier to read
 *
 *    println( "Sum of multiples of %d and %d less than %d is:".format(f1, f2, N) )
 *
 */
      println(nums.sum)

    //println(com.spotinfluence.Prob007.findNextPrime(100))

   /*
    val nums = for {
      n <- 1 until 1000
      if n % 3 == 0 || n % 5 == 0
    } yield n

    println(nums.sum)
   */

  }
}
