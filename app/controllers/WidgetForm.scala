package controllers

import org.checkerframework.checker.units.qual.Length

object WidgetForm {
  import play.api.data.Forms._
  import play.api.data.Form
  import play.api.data.format.Formats._

  /**
   * A form processing DTO that maps to the form below.
   *
   * Using a class specifically for form binding reduces the chances
   * of a parameter tampering attack and makes code clearer.
   */
  case class Rectangle(length: Double, width: Double) {
    require (length > 0, width > 0)

    lazy val area = length * width
    override def toString = s"length: $length, width: $width, area: $area"
  }

  object Rectangle {
    def fromLength(length: Double) = Rectangle(length, length)
    def fromLengthArea(length: Double, area: Double) = Rectangle(length, area / length)
    def fromWidthArea(width: Double, area: Double) = Rectangle(area / width, width)
    def area(length: Double, width:Double) = Rectangle(length, width).area
    def show(rect: Rectangle) = println(rect)
  }
  /**
   * The form definition for the "create a widget" form.
   * It specifies the form fields and their types,
   * as well as how to convert from a Data to form data and vice versa.
   */
  val form = Form(
    mapping(
      "length" -> of(doubleFormat),
      "width" -> of(doubleFormat),
    )(Rectangle.apply)(Rectangle.unapply)
  )




}
