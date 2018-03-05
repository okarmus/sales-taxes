package org.okarmus

sealed trait Parser[A]{
  def parse(input: => A): List[Item]
}

object DummyParser extends Parser[Unit] {               //TODO is it according to the good standard

  override def parse(input: => Unit): List[Item] = {
    List(
      Item(1, false, "book", Price(BigDecimal(12.49))),
      Item(1, false, "music CD", Price(BigDecimal(14.99))),
      Item(1, false, "chocolate bar", Price(BigDecimal(0.85)))
    )
  }
}
