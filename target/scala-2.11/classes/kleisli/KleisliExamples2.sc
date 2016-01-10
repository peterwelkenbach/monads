import scalaz._
import Scalaz._


def opt(x: Int): Option[String] = Some( x.toString )

def list(x: String): List[String] = List(x)

// helper function to transform an Option
def optToValue[T]( o: Option[T]): T = o.get

// now we can compose the operations

val foo = optToValue(_)

val f= foo( Some("8"))
list( f )


// val funny = Kleisli( opt _ ) >==> list



