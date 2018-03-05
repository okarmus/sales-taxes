package org.okarmus

import org.scalatest.FunSuite

class PriceSuite extends FunSuite {

  test("taxes should be properly applied on price") {

    assert(
      Price(BigDecimal(12.49)).tax(BigDecimal(0)) == TaxedPrice(BigDecimal(12.49), BigDecimal(0))
    )

    assert(
      Price(BigDecimal(14.99)).tax(BigDecimal(0.1)) == TaxedPrice(BigDecimal(16.49), BigDecimal(1.5))
    )

    assert(
      Price(BigDecimal(27.99)).tax(BigDecimal(0.15)) == TaxedPrice(32.19, 4.2)
    )
  }
}
