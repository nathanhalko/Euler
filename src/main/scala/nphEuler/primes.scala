
package nphEuler

import scala.math._


/*
I have confusion over when to use objects, classes, defs and vals

 */

object primes {

  /*
     Generate all prime numbers 2,3, .. <= n
     Uses the sieve of eratosthenes
   */

  def genPrimes(n:BigInt): List[BigInt] = {

    var p:List[BigInt] = Nil
    var nums   = 2 :: List.range(3,n,2)   // 3 to n by 2

    while (!nums.isEmpty) {
      p    = nums(0) :: p
      nums = nums.filter(_ % nums(0) != 0)

    }

    p.reverse
  }

  /*
  Given a list of primes, return the next one
   */

  def nextPrime(primes:List[BigInt]): BigInt = {

    def findNext(c:BigInt): BigInt = {
      //if (primes.find( c % _ == 0).isEmpty) c
      if (isPrime(c,primes)) c
      else findNext(c+2)
    }

    findNext(primes.last+2)
  }

  /*
  Return the i'th prime
   */

  def apply(i:BigInt): BigInt = {
    var primes = genPrimes(10000)
    val len = primes.length
    if (i <= len) primes(i-1)
    else for {j <- len+1 to i} {
      primes = (nextPrime(primes) :: primes.reverse).reverse
    }
    primes.last
  }


  /*
  Test whether a number is prime.  Optionally pass in a list of
  all known primes up to sqrt(n)
   */

  def isPrime(n: BigInt, primes:List[BigInt] = Nil): Boolean = {
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
  def primeFactors(n:BigInt): List[(BigInt,Int)] = {
    val pfs = genPrimes(n).filter(n % _ == 0)

    def getMult(n:BigInt, f:BigInt, mult: Int = 0): Int = {
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

    (1 until n).filter{primeFactors(_).map(x => x._1).intersect(pfs).isEmpty}.length
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