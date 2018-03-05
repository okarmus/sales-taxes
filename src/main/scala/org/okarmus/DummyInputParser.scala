package org.okarmus

sealed trait InputParser[A] {
  def parse(input: => A): List[(Item, NetPrice)]
}

object DummyInputParser extends InputParser[Unit] { //TODO is it according to the good standard

  override def parse(input: => Unit): List[NotTaxedItem] = {
    List(
      (Item(1, false, "book"), NetPrice(BigDecimal(12.49))),
      (Item(1, false, "music CD"), NetPrice(BigDecimal(14.99))),
      (Item(1, false, "chocolate bar"), NetPrice(BigDecimal(0.85)))
    )
  }
}
