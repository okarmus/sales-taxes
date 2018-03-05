package org.okarmus


case class Item(quantity: Int, imported: Boolean, name: String)

case class Receipt(items: List[TaxedItem], taxes: TaxedPrice) {
  def add(item: TaxedItem): Receipt = Receipt(item :: items, taxes + item._2)
}

object Receipt {
  val empty: Receipt = Receipt(Nil, TaxedPrice(0, 0))
}

object Programme{

  def run(): String = {
    import OutputBuilder.implicits._
    import TaxOffice.taxRate

    DummyInputParser.parse()
        .map{ case (item, price) => (item, price.tax(taxRate(item)))}
        .foldRight(Receipt(Nil, TaxedPrice(0, 0)))((taxedItem, receipt) => receipt.add(taxedItem))
        .build
  }
}

object Main extends App {
    println(Programme.run())
}
