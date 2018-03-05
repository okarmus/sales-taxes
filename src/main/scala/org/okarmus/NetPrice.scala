package org.okarmus

import BigDecimal.RoundingMode.HALF_UP

trait Price{}

case class NetPrice(net: BigDecimal) extends Price {

  def tax(taxRate: BigDecimal): TaxedPrice = {
    val gross = (taxRate * net).setScale(2, HALF_UP).rounded

    TaxedPrice(gross, gross - net)
  }
}

case class TaxedPrice(gross: BigDecimal, tax: BigDecimal) extends Price {
  def + (other: TaxedPrice): TaxedPrice = TaxedPrice(gross + other.gross, tax + other.tax)
}
