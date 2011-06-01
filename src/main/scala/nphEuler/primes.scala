package nphEuler

import scala.math._
import scala.collection.mutable.ListBuffer


/*
I have confusion over when to use objects, classes, defs and vals

 */

/* -ljr
 *
 * Use a val for a value you won't change.  Try to work your code to prefer vals over vars.
 *
 * Use a var when the value will change.  This can occur because you will increment a variable for a count
 * or you are using an immutable structure that you want to change in place.  That last reason is a hint that
 * a tail-recursive def might be in order.
 *
 * Use a def when you want to calculate a value based on parameters.  defs sometimes masquarade as vals when
 * you want to calculate the value each time based on some bit of state (which probably isn't visible to the
 * end user because it's held in a private var).
 *
 * An object is pretty much equivalent to this code
 *
 *  // object Foo
 *  class FooClass { // Foo implementation }
 *  val Foo = new FooClass()
 *
 * .  It provides (only) a single instance of the implementation.
 *
 * Use a class when you want multiple instances of an implementation.  Consider Primes below.  You might be
 * tempted to make it an object thinking your code would only ever need one instance of a list of Primes but
 * each instance takes memory.  If you calculate small primes for most of your code and a single instance of
 * new Primes().isPrime((2e9).toInt + 11) you might not want to keep the instance around with all the storage
 * it consumed (2e4.5 Ints) to calculate that large a prime.
 *
 * Finally use a case class when you want multiple instances like a class but of instances that act like
 * values.  Case classes provide several bits of sugar making them easy to work with.  First, you don't have
 * to use new to instantiate a case class.
 *
 *   case class TriNum(n: Int, v: Int = (n*(n+1))/2)
 *
 *   val myTri = TriNum(285)
 *
 * they automatically implement unapply(), toString(), hashCode(), and equals().  The equals is structural
 * equality so Tri(285) == Tri(285).  (Default class equality is based on the instance itself so that
 * instanceA == instanceA != instanceB.)
 *
 * You can if you want get fancy
 *
 *   case class TriNum(n: Int, v: Int = (n*(n+1))/2)
 *
 *   object Tri {
 *     def apply(n: Int): TriNum = TriNum(n)
 *
 *     def unapply(v: Int): Option[Int] = {
 *       val n = math.floor(math.sqrt(x*2)).toInt
 *       if (Tri(n).v == v) Some(n) else None
 *     }
 *   }
 *
 * and now you can write
 *
 *   val t = Tri(285)   // TriNum(285, 40755)
 *   40755 match { case Tri(n) => println("Tri(%d)".format(n)); case _ => println("not triangular") }   // Tri(285)
 *   40754 match { case Tri(n) => println("Tri(%d)".format(n)); case _ => println("not triangular") }   // not triangular
 *
 * Read up on case classes and get a good grip on unapply().
 */

object primes {

  /*
     Generate all prime numbers 2,3, .. <= n
     Uses the sieve of eratosthenes
   */

  def genPrimes(n:Int): List[Int] = {

    var p:List[Int] = Nil
    var nums   = 2 :: List.range(3,n.toInt,2)   // 3 to n by 2

    while (!nums.isEmpty) {
      p    = nums(0) :: p
      nums = nums.filter(_ % nums(0) != 0)

    }

    p.reverse
  }

/* -ljr
 *
 * Building a list and reversing it is painful as is building a list of numbers you are just going to throw away.
 *
 *   import scala.annotation.tailrec
 *   import scala.collection.mutable
 *
 *   class Primes {
 *     private val ps = mutable.ArrayBuffer[Int](2,3,5,7) // indexable
 *     private var x = ps.last
 *
 *     @tailrec private def nextPrime(): Int = {
 *       x += 2
 *       @tailrec def isPrime(ix: Int): Boolean = {
 *         val p = ps(ix)
 *         if (p*p > x) true
 *         else if (x % p == 0) false
 *         else isPrime(ix+1)
 *       }
 *       if (isPrime(1)) ps += x
 *       else nextPrime()
 *     }
 *
 *     def apply(n: Int): Int = {
 *        genByCount(n)
 *        ps(n)
 *     }
 *
 *     def genByValue(n: Int) {
 *       while (n > ps.last) nextPrime()
 *     }
 *
 *     def genByCount(n: Int) {
 *       while (n > ps.size) nextPrime()
 *     }
 *
 *     def toSeq = ps.toSeq
 *     def toIndexedSeq = ps.toIndexedSeq
 *
 *     def isPrime(n: Int): Boolean = {
 *       @tailrec def step(ix: Int): Boolean = {
 *         val p = ps(ix)
 *         if (n == p) true
 *         else if (p*p > n) true
 *         else if (n % p == 0) false
 *         else step(ix+1)
 *       }
 *
 *       step(0)
 *     }
 *
 *     def primeFactors(n: Int): Map[Int,Int] = {
 *       @tailrec def step(x: Int, ix: Int, z: Map[Int,Int]): Map[Int,Int] = {
 *         if (x == 1) return z
 *
 *         val p = ps(ix)
 *         def incr(p): Int = z.getOrElse(p, 0) + 1
 *
 *         if (p*p > x) z + (x -> incr(x))
 *         else if (x % p == 0) step(x/p, ix, z + (p -> incr(p))
 *         else step(x, ix+1, z)
 *       }
 *
 *       step(n, 0, Map.empty[Int,Int])
 *     }
 *  }
 */

  /*
  Given a list of primes, return the next one
   */

  def nextPrime(primes:List[Int]): Int = {

    def findNext(c:Int): Int = {
      //if (primes.find( c % _ == 0).isEmpty) c
      if (isPrime(c,primes)) c
      else findNext(c+2)
    }

    findNext(primes.last+2)
  }

  /*
  Return the i'th prime
   */

  def apply(i:Int): Int = {
    var primes = genPrimes(10000) //.toBuffer
    val len = primes.length
    if (i <= len) primes(i-1)

    else for {j <- len until i} {

      primes = (nextPrime(primes) :: primes.reverse).reverse
      /*
      Ahhh some ridiculous reverses.  Need to change nextPrime to accept ListBuffer
      primes = nextPrime(primes) :: primes
       */
    }
    primes.last
  }


  /*
  Test whether a number is prime.  Optionally pass in a list of
  all known primes up to sqrt(n)
   */

  def isPrime(n: Int, primes:List[Int] = Nil): Boolean = {
    val N = ceil(sqrt(n)).toInt
    if (primes.isEmpty || primes.last <= N) {
      val primes = genPrimes(N)
      println(primes)
    }
    primes.find(n % _ == 0).isEmpty
  }


  /*
  Prime Factorization of n
  Return list of tuples (prime factor, multiplicity) so that
  n == primeFactors(n).map(x => pow(x._1, x._2)).product
   */
  def primeFactors(n:Double): List[(Int,Int)] = {
    val pfs = genPrimes(ceil(sqrt(n)).toInt).filter(n % _ == 0)

    def getMult(n:Double, f:Int, mult: Int = 0): Int = {
      require(f != 1)
      if (n % f != 0) mult
      else {
        getMult(n/f,f, mult+1)
      }
    }
    pfs.zip(pfs.map(f => getMult(n,f)))
  }


  /*
  Euler's totient function.  Returns the number of positive integers that are less than
  n and coprime to n
   */

def totient(n:Int): Int = {
    val pfs = primeFactors(n).map(x => x._1)

    List.range(1,n-1).filter{primeFactors(_).map(x => x._1).intersect(pfs).isEmpty}.length
  }

}




class Rational(n:Int,d:Int) extends Ordered[Rational] {
  require(d != 0)
  override def toString = n + "/" + d
  def this(n:Int) = this(n,1)  //auxiliary constructor

  private val g = gcd(n.abs,d.abs)
  val numer = n / g
  val denom = d / g

  def + (that: Rational) = {
    new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
  }

  def - (that: Rational) = {
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
  }

  def * (that: Rational) = {
    new Rational(numer * that.numer, denom * that.denom)
  }

  def / (that: Rational) = {
    new Rational(numer * that.denom, denom * that.numer)
  }

  /*
  def lessThan(that: Rational) = {
    numer * that.denom < that.numer * denom
  }
  */

  def compare(that: Rational) = {
    numer * that.denom - that.numer * denom
  }

  def max(that: Rational) = {
    if (this < that) that
    else this
  }

  private def gcd(a:Int, b:Int): Int = {
    if (b == 0) a
    else gcd(b, a % b)
  }

}

/*
Polynomials p(x) of degree n passed as Lists of length n+1 with the coefficients
of (x^n , x^(n-1) , ... , x , Const)
 */

class Polynomial(p: List[Int]) {

  def show(p: List[Int]) = {
  val pp = p.zip(p.length-1 to 0 by -1)

  pp.init.filter{a=>a._1!=0}.foreach{a=>a;print("%dx^%d + ".format(a._1,a._2))}
  if (p.last!=0) print(p.last)
  }
  // override def toString ??  I want to print 4x^3 + 10x^2 + 3 when : val p = new Polynomial(List(4,10,0,3))
  // scala.AnyRef has 'toString' method-> all classes implicitly extend this class

  val coeffs = p

  /*
    TODO:  add,multiply polys of different degrees
  */


  def + (that: Polynomial) = {
    coeffs.zip(that.coeffs).map( c => c._1 + c._2 )  // probably a slicker way to do this common task?
  }

  def * (that: Polynomial) = {
    val deg = coeffs.length - 1   // must be the same as that.coeffs.length

    val c1 = coeffs.zip(deg to 0 by -1)
    val c2 = that.coeffs.zip(deg to 0 by -1)

    val multCoeffs = c1.map(a => c2.map(b => (a._1*b._1,a._2+b._2))).flatten

    for (d <- 2*deg to 0 by -1) yield {
      multCoeffs.filter{a => a._2 == d}.map(a => a._1).sum  //returns scala.collection.immutable.IndexedSeq[Int] = Vector(...) not List ??
    }                                                       //But it works!!
  }
}
