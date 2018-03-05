package org.okarmus

import org.okarmus.Programme.TaxedItem


case class Item(quantity: Int, imported: Boolean, name: String, price: Price) {

  def tax(taxValue: Item => BigDecimal): TaxedItem = {

    val taxedPrice = price.tax(taxValue(this))                  //TODO maybe it could be passed further as function
    (copy(price = Price(taxedPrice.gross)), taxedPrice)    //TODO passing this price is something i do not really like maybe taxed item should be a subtype of item
  }
}

case class Receipt(items: List[Item], taxes: TaxedPrice) {
  def add(item: Item, tax: TaxedPrice): Receipt = Receipt(item :: items, taxes + tax)
}

object Programme{

  type TaxedItem = (Item, TaxedPrice)
  def run(): String = {
    import OutputBuilder.implicits._
    import TaxOffice.taxRate

    DummyParser.parse()
        .map(_.tax(taxRate))
        .foldRight(Receipt(Nil, TaxedPrice(0, 0))){ case ((item, price), receipt) => receipt.add(item, price)}  //TODO this fold is ugly
        .build
  }
}


object Main {

  def main(args: Array[String]): Unit = {

    println(Programme.run())

  }

}
