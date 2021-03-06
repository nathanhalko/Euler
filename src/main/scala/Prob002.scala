package com.spotinfluence


object Prob002 {
  def main(args: Array[String]) {


/*  var sum = 2
=======
  /*
  var sum = 2
>>>>>>> e0bfe4f874cad8a22845c4b57ee1fb46ba4f7b66
  var x   = 1
  var y   = 2
  var z   = 3

  while (sum < 4000000) {

    x = y
    y = z
    z = x + y


    if (z % 2 == 0) {
      sum = sum + z
      println("Partial sum: "+sum)
    }
  }
    */

     def fib(a: Int, b: Int, max: Int, z: List[Int] = Nil): List[Int] = {
       if (a > max) z.reverse
       else fib(b, a+b, max, a :: z)
      }

     val sum = fib(1,2,4000000).filter{_%2==0}.sum
     println("Sum of even Fibonacci numbers less than 4,000,000: "+sum)
  */

    def fib(a:Int, b:Int, max:Int, z:List[Int] = Nil): List[Int] = {
      if (a > max) z.reverse
      else fib(b, a+b, max, a :: z)
    }

/* -ljr
 *
 * In Scala you can avoid that reverse if you use a ListBuffer.
 *
 * def fib(a: Int, b: Int, max: Int, z: mutable.ListBuffer[Int] = mutable.ListBuffer[Int]()): List[Int] =
 *   if (a > max) z.result else fib(b, a+b, max, z += a)
 *
 * You can get at a list buffer as List.newBuilder[A] but the type won't be mutable.ListBuffer[A].
 * Useful in anonymous situations where the ListBuffer is never exposed.
 * -nph ??? no entiendo
 *
 * One nice thing
 * about ListBuffers is that they extend Seq so you can treat them as lists with the benefit of O(1)
 * length (and thus size) and append.
 *
 *  -nph  Thats good to know about the ListBuffer.  Curious why you would call z.result which converts back
 *  to a List?  This costs the same as 'reverse' and I like that List is immutable.  But I will definitely keep
 *  an eye out for chances to use ListBuffer when List is not doing the job.
 */

    val fibSum = fib(1,2,4000000).filter{_ % 2 ==0}.sum

    println("Sum of even Fibonacci numbers less than 4,000,000: %d".format(fibSum))
  }
}

/* -ljr

Lots of ways to solve this one.

A way to get rid of those vals is to consider that since 4M isn't all that big there
aren't that many elements in the fibonacci expansion so you could just do it as a list and sum.

  def fib(a: Int, b: Int, max: Int): List[Int] = if (a > max) Nil else a :: fib(b, a+b, max)

  fib(1, 2, 4000000).filter{_ % 2 == 0}.sum

This has a subtle problem in Scala because we don't have call-by-name semantics.  In a
language like Haskell that does we can make a list pretty much as big as we want and it
won't blow the stack.  Scala will if we try to make our list too big because each call
to fib() is sticking around waiting for the return value of the next call.

We can get around that problem with tail-recursion and an accumulator.  Tail-recursion
happens when the last call of a def is a call to itself in tail-call position.  Tail-call
position is a fancy name for "that which will be returned".  Now

  a :: fib(...)

isn't a tail-call because the current call frame has to be held on the stack until we get
a return value so we can prepend 'a' onto the resulting list.  If we define it this way
though

  def fib(a: Int, b: Int, max: Int, z: List[Int] = Nil): List[Int] = {
    if (a > max) z.reverse else fib(b, a+b, max, a :: z)
  }

then the recursive call to fib is in tail-call position.  This is important because tail
call recursion is equivalent to a while() loop and the compiler can drop the intermitent
stack frames meaning you won't blow the stack with deep recursion.

-nph  Nice, I have been seeing examples of how/why to use tail call recursion but this
is very clear now

(As an aside consider:

  def (var a: Int, var b: Int, max: Int, var z: List[Int] = Nil): List[Int] = {
    while (max < a) {
      z = a :: z
      a = b
      b = a + b
    }
    z.reverse
  }

)

*/

/* -ljr

One of the rules of thumb I go by is, "If you use something twice encapsulate it".  Now
solving Euler problems are pretty much one shot deals but you'll gain some practice
if you encapsulate stuff like fib() as defs when you come across them.  Basically use
your main() to define values and print output and everything else should be handled by
a def.  You'll also find code works with less bugs if your defs have as few side-effects
as possible (don't manipulate external data or receive extra input from external data)
and are as close to a mathematical function as possible (that is, for a given input you
will always get the same result).

*/
