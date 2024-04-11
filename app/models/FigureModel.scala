package models
case class RectangleModel(length: Double, width: Double) {
  require(length > 0 && width > 0, "Length and width must be greater than 0")

  lazy val area: Double = length * width

  override def toString: String = s"Rectangle: Length = $length, Width = $width, Area = $area"
}

case class CircleModel(radius: Double) {
  require(radius > 0, "Radius must be greater than 0")

  lazy val area: Double = math.Pi * math.pow(radius, 2)

  override def toString: String = s"Circle: Radius = $radius, Area = $area"
}

case class TriangleModel(sideA: Double, sideB: Double, sideC: Double) {
  require(sideA > 0 && sideB > 0 && sideC > 0, "Triangle sides must be greater than 0")
  require(sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA,
    "Triangle inequality theorem not satisfied")

  private val halfPerimeter: Double = (sideA + sideB + sideC) / 2

  lazy val area: Double = math.sqrt(halfPerimeter * (halfPerimeter - sideA) * (halfPerimeter - sideB) * (halfPerimeter - sideC))

  override def toString: String = s"Triangle: Side A = $sideA, Side B = $sideB, Side C = $sideC, Area = $area"
}



