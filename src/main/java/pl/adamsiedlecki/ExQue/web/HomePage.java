package pl.adamsiedlecki.ExQue.web;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


@Route("")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PageTitle("Home page")
public class HomePage extends VerticalLayout {

    //private VerticalLayout root;

    @Autowired
    public HomePage(Environment env) {
        //root = new VerticalLayout();
        Label title = new Label("ExQue");

        Button codeButton = new Button("Github", new Icon(VaadinIcon.CODE));
        codeButton.addClickListener(e -> {
            UI.getCurrent().getPage().setLocation(env.getProperty("project.github.url"));
        });
        HorizontalLayout header = new HorizontalLayout(title, codeButton);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setPadding(true);
        header.setSpacing(true);

        Paragraph text = new Paragraph("API");
        Anchor answersLink = new Anchor("api/v1/answers", "api/v1/answers");
        Anchor questionsLink = new Anchor("api/v1/questions", "api/v1/questions");
        Anchor examCategoryLink = new Anchor("api/v1/exam-categories", "api/v1/exam-categories");

        VerticalLayout content = new VerticalLayout(text, answersLink, questionsLink, examCategoryLink);
        content.setPadding(true);

        Paragraph description = new Paragraph("Project for storing questions and ABCD answers and sharing it via REST api. (ExQue - Exam Questions)");
        VerticalLayout footer = new VerticalLayout(description);
        footer.setPadding(true);

        add(header, content, footer);
        //this.add(root);
    }
}
