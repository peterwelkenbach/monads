val a = Some("45")

def f(s: String): Option[String] = Some( s + "1" )
def b(s: String): Option[Int] = Some( s.toInt )
def c(i: Int): Option[Int] = Some( i * 2 )

val res1 = a.flatMap( f ).flatMap( b ).flatMap( c )


val res2 = for {
  n <- f("32")
  m <- b(n)
  o <- c(m)
} yield o


val o1 = Some("24")
val o2 = Some(o1)

val res3 = o2.flatMap( _.flatMap(f) ).flatMap(b).flatMap(c)


val bar = Some("2")

val res4 = bar map( _ + "1") map( _.toInt ) map ( _ * 2)



import scalaz._
import Scalaz._

val s = Monad[Option].point("34")

//val res4 = s.lift( f )


