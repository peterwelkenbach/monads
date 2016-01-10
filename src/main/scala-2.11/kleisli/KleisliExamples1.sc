import scalaz._
import Scalaz._


def toStr(x: Int): Option[String] = Some( x.toString )
def toInt(s: String): Option[Int] = Some( s.toInt )
def toDouble( i: Int): Option[Double] = Some( i.toDouble )

// composing the old way
def oldWayComposing(a: Int) =
  for {
    x <- toStr(a)
    y <- toInt(x)
    z <- toDouble( y )
  } yield z

oldWayComposing(6)

// FlatMap style
def flatMapChaining(a: Int) =
    toStr(a).flatMap( toInt ).flatMap( toDouble)

flatMapChaining(3)



// Kleisli way
val swissMagic = Kleisli( toStr _ ) >==> ( toInt _ ) >==> ( toDouble _ )

swissMagic(8)


// Kleisli way
val swissMagic2 = Kleisli( toStr _ ) >==> toInt  >==> toDouble

swissMagic2(9)


