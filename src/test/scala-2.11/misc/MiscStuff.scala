package misc

import java.util


trait Show {
  def show: String
}


class Foo extends Show {
  override def show = "I'm foo"
}

class Bar extends Show {
  override def show = "I'm bar"
}

object MiscStuff extends App {


  val list = List[Show]( new Foo, new Foo, new Bar)

  list.foreach {
    x => {
      val s = x.show
      println(s)
    }
  }

}
