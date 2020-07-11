package pl.adamsiedlecki.ExQue.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import pl.adamsiedlecki.ExQue.db.entity.Answer;
import pl.adamsiedlecki.ExQue.db.entity.Question;
import pl.adamsiedlecki.ExQue.db.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.vaadin.flow.component.icon.VaadinIcon.QUESTION_CIRCLE_O;


@Route("random-question")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PageTitle("Random question")
public class RandomQuestionPage extends VerticalLayout {

    private final QuestionService questionService;

    @Autowired
    public RandomQuestionPage(Environment env, QuestionService questionService) {
        this.questionService = questionService;

        Random rand = new Random();
        int num = rand.nextInt((int) questionService.count());
        Question question = questionService.findAll().get(num);

        if (question != null) {
            setWidth("100%");
            Label title = new Label("ExQue");

            HorizontalLayout header = new HorizontalLayout(title);
            header.setAlignItems(Alignment.CENTER);
            header.setPadding(true);
            header.setSpacing(true);

            Paragraph questionParagraph = new Paragraph(question.getContent());
            HorizontalLayout questionLayout = new HorizontalLayout(new Icon(QUESTION_CIRCLE_O), questionParagraph, new Label(" Category: " + question.getExamCategory().getName()));

            final List<Answer> possibleAnswers = question.getPossibleAnswers();
            List<Button> buttons = new ArrayList<>();
            final List<Character> letters = List.of('A', 'B', 'C', 'D');

            int counter = 0;
            for (Answer answer : possibleAnswers) {
                Button b = new Button();
                buttons.add(b);
                b.setText(letters.get(counter) + "   " + question.getPossibleAnswers().get(counter).getContent());
                b.setWidth("350px");
                b.isIconAfterText();
                b.setIcon(new Icon(VaadinIcon.ARROW_FORWARD));

                if (answer.getContent().equals(question.getCorrectAnswer().getContent())) {
                    Notification notification = new Notification("Congratulations! \"" + answer.getContent() + "" +
                            "\" is a correct one!", 4000, Notification.Position.TOP_CENTER);

                    b.addClickListener(event -> {
                        notification.open();

                    });
                } else {
                    Notification notification = new Notification("This is not a correct answer!", 4000, Notification.Position.TOP_CENTER);

                    b.addClickListener(event -> {
                        notification.open();
                    });
                }

                buttons.add(b);
                counter++;
            }

            add(header, new Anchor("/random-question", "REFRESH"), questionLayout);
            for (Button b : buttons) {
                add(b);
            }

        } else {
            add(new Label("Sorry, but there is no questions in the database ;)"));
        }

        setAlignItems(Alignment.CENTER);
    }
}
