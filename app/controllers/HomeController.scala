package controllers

import javax.inject._
import models.Person
import play.api.http.{ContentTypeOf, ContentTypes, Writeable}
import play.api.i18n.I18nSupport
import play.api.mvc._
import scalatags.Text.Tag
import views.ScalaTags

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, tags: ScalaTags) extends BaseController
  with I18nSupport with ScalaTagsWritable {

  val people: List[Person] = List(
    Person("santa", 666, "200cm"),
    Person("bob", 50, "176cm"),
    Person("charles", 99, "150cm")
  )

  def twirl(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.twirl(people))
  }

  def tags(): Action[AnyContent] = Action { implicit request =>
    Ok(tags(people))
  }

}

/* Required in order to not need to write .as("text/html") on the end of scala tags views */
trait ScalaTagsWritable {

  implicit def tagWritable(implicit codec: Codec): Writeable[Tag] = {
    Writeable(data => codec.encode("<!DOCTYPE html>" + data.render))
  }

  implicit def contentType(implicit codec: Codec): ContentTypeOf[Tag] = {
    ContentTypeOf(Some(ContentTypes.HTML))
  }

}