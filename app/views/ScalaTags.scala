package views

import javax.inject.Singleton
import models.Person
import play.api.i18n.Messages
import scalatags.Text.TypedTag
import scalatags.Text.all._

@Singleton
class ScalaTags extends MainTemplate {

  def apply(people: List[Person])(implicit messages: Messages): TypedTag[String] = {
    mainTemplate("tags.title")(
      h1(messages("tags.heading")),
      people.map { person =>
        p(
          div(cls := "bold")(messages("person.name", person.name)),
          div(messages("person.age", person.age)),
          div(messages("person.height", person.height))
        )
      }
    )
  }

}
