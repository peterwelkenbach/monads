
trait Foo[A, B] {
  def foo(a: A)( f: A => B): B
}

object Int2StringFoo extends Foo[Int, String] {
  override def foo(a: Int)(f: Int => String): String = f(a)
}

object Double2StringFoo extends Foo[Double, String] {
  override def foo(a: Double)(f: Double => String): String = f(a)
}

object String2IntFoo extends Foo[String,Option[Int]] {
  override def foo(a: String)(f: String => Option[Int]): Option[Int] = f(a)
}

def f(a: Int): String =  "String is " + a.toString
def ff(a: Double): String ="this String is " + a.toString
def b(s: String): Option[Int] = Option(s.toInt)

val res1 = Int2StringFoo.foo(22)(f)
val res2 = Double2StringFoo.foo(11.1)(ff)
val res3 = String2IntFoo.foo("23")(b)
//val res4 = String2IntFoo.foo("2A")(b)


object Int2StringFoo2  {
  def foo(a: Int)(implicit f: Int => String): String = f(a)
}


implicit def yaya(y: Int): String = "yaya " + y.toString


val res5 = Int2StringFoo2.foo(44)
