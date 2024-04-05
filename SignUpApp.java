package com.example.signupapp;





import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUpApp extends Application {

    private Stage primaryStage;
    private VBox contactPage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Initial scene
        Scene signUpScene = createSignUpScene();
        primaryStage.setScene(signUpScene);
        primaryStage.setTitle("Sign Up Form");
        primaryStage.show();
    }

    private Scene createSignUpScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create menu buttons
        Button homeButton = createMenuButton("Home");
        homeButton.setOnAction(e -> switchToSignUpScene());
        Button contactButton = createMenuButton("Contact");
        contactButton.setOnAction(e -> switchToContactScene());
        Button aboutButton = createMenuButton("About");
        aboutButton.setOnAction(e -> switchToAboutScene());

        // Create header with menus
        HBox header = createHeader(homeButton, contactButton, aboutButton);

        // Add form fields to the grid
        addFormField(grid, "First Name:", 0, 1);
        addFormField(grid, "Last Name:", 0, 2);
        addFormField(grid, "Date of Birth:", 0, 3, new DatePicker());
        addFormField(grid, "Email:", 0, 4);
        addFormField(grid, "Address:", 0, 5);

        // Order form fields
        addFormField(grid, "Product Name:(add commas between product", 0, 6);
        addFormField(grid, "Quantity:", 0, 7, new TextField());

        // Submit Button
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> {
            System.out.println("Submission Received");
            switchToPaymentScene(); // Switch to payment scene after submission
        });
        grid.add(btnSubmit, 1, 8);

        // Main layout that combines header and grid
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(header, grid);

        return new Scene(mainLayout, 800, 600);
    }



    private void switchToContactScene() {
        primaryStage.setScene(createContactScene());
        primaryStage.setTitle("Contact");
    }

    private void switchToSignUpScene() {
        primaryStage.setScene(createSignUpScene());
        primaryStage.setTitle("Sign Up Form");
    }

    private void switchToAboutScene() {
        primaryStage.setScene(createAboutScene());
        primaryStage.setTitle("About");
    }

    private void switchToPaymentScene() {
        primaryStage.setScene(createPaymentScene());
        primaryStage.setTitle("Payment");
    }

    private Scene createPaymentScene() {
        GridPane paymentGrid = new GridPane();
        paymentGrid.setAlignment(Pos.CENTER);
        paymentGrid.setHgap(10);
        paymentGrid.setVgap(10);
        paymentGrid.setPadding(new Insets(25, 25, 25, 25));

        // Payment fields
        addFormField(paymentGrid, "First Name:", 0, 1);
        addFormField(paymentGrid, "Last Name:", 0, 2);
        addFormField(paymentGrid, "Address:", 0, 3);
        addFormField(paymentGrid, "Credit Card Number:", 0, 4);
        addFormField(paymentGrid, "Expiration Date:", 0, 5, new DatePicker());
        addFormField(paymentGrid, "CVV:", 0, 6, new PasswordField());

        // Submit Button
        Button btnSubmit = new Button("Submit Payment");
        btnSubmit.setOnAction(e -> System.out.println("Payment Submitted"));
        paymentGrid.add(btnSubmit, 0, 7);

        // Main layout for payment scene
        VBox paymentLayout = new VBox();
        paymentLayout.getChildren().addAll(paymentGrid); // Add the paymentGrid to the layout
        Button backButton = new Button("Back to Home");
        backButton.setOnAction(e -> switchToSignUpScene()); // Assign action to switch back to home scene
        paymentLayout.getChildren().add(backButton); // Add the back button to the layout

        return new Scene(paymentLayout, 800, 600);
    }

    private Scene createContactScene() {
        // Create menus
        Button homeButton = createMenuButton("Home");
        homeButton.setOnAction(e -> switchToSignUpScene());
        Button contactButton = createMenuButton("Contact");
        Button aboutButton = createMenuButton("About");
        aboutButton.setOnAction(e -> switchToAboutScene());

        // Create header with menus
        HBox header = createHeader(homeButton, contactButton, aboutButton);

        // Initialize contact page layout
        contactPage = createContactPage();

        // Main layout that combines header and contact page
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(header, contactPage);

        return new Scene(mainLayout, 800, 600);
    }

    private Scene createAboutScene() {
        // Create menus
        Button homeButton = createMenuButton("Home");
        homeButton.setOnAction(e -> switchToSignUpScene());
        Button contactButton = createMenuButton("Contact");
        contactButton.setOnAction(e -> switchToContactScene());
        Button aboutButton = createMenuButton("About");

        // Create header with menus
        HBox header = createHeader(homeButton, contactButton, aboutButton);

        // Create main content layout
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.CENTER); // Center the content vertically and horizontally
        mainLayout.setPadding(new Insets(20)); // Add padding around the content

        // Add paragraphs of text
        Text paragraph1Text = createLoremIpsumText();
        Text paragraph2Text = createMissionText();

        mainLayout.getChildren().addAll(paragraph1Text, paragraph2Text);

        // Create a shadow border for the header
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetY(5.0); // Set the shadow offset
        dropShadow.setColor(Color.DARKGRAY); // Set the shadow color
        header.setEffect(dropShadow); // Apply the shadow effect to the header

        // Set background color
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // Wrap main layout in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(mainLayout);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if needed

        // Create scene and set it to the stage
        Scene scene = new Scene(new BorderPane(scrollPane, header, null, null, null), 800, 600); // BorderPane to position header at the top
        return scene;
    }

    private Text createMissionText() {
        // Text for mission
        Text missionText = new Text("""
        Our mission is simple: to deliver unparalleled computer repair services with a focus on quality,
        integrity, and customer satisfaction. We strive to be the go-to destination for individuals and
        businesses seeking prompt and effective solutions for their computer-related issues.
        """);
        missionText.setFill(Color.BLACK);
        missionText.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        return missionText;
    }

    private Text createLoremIpsumText() {
        // Use a Text object for multiline text
        Text text = new Text("""
            At our computer repair shop, we understand the frustration and inconvenience that
             arises when your computer encounters issues. With a team of skilled technicians
              and a commitment to exceptional service, we are dedicated to providing reliable
               and efficient solutions to all your computer problems.
            """);
        text.setFill(Color.BLACK);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        return text;
    }

    private VBox createContactPage() {
        VBox contactPageLayout = new VBox(10);
        contactPageLayout.setAlignment(Pos.CENTER);
        contactPageLayout.setPadding(new Insets(20));

        // Title "Customer Service"
        Label customerServiceLabel = new Label("Customer Service");
        customerServiceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); // Increase font size

        // Additional Text (two lines)
        Text additionalText1 = new Text("Do you have any questions about using the app?");
        Text additionalText2 = new Text("Or do you have questions about the information about our services?");
        Text additionalText3 = new Text("You can reach out to us through our wonderful customer service:");
        additionalText1.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Adjust font size
        additionalText2.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Adjust font size
        additionalText3.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Adjust font size

        // Contact Information
        Label contactLabel = new Label("Contact Information");
        contactLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Font size

        Label nameLabel = new Label("Your Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18)); // Adjust font size

        Label phoneLabel = new Label("Phone: (604) 123-4567");
        phoneLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18)); // Adjust font size

        Label emailLabel = new Label("Email: wsmith@apollo.ca");
        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18)); // Adjust font size

        // Add some space between sections
        Region space = new Region();
        VBox.setMargin(space, new Insets(10, 0, 10, 0));

        Button backButton = new Button("Back"); // Initialize backButton
        backButton.setOnAction(e -> switchToSignUpScene()); // Define backButton action

        // Add all components to the VBox
        contactPageLayout.getChildren().addAll(customerServiceLabel, additionalText1, additionalText2, additionalText3, space, contactLabel, nameLabel, phoneLabel, emailLabel, backButton);

        return contactPageLayout;
    }

    private HBox createHeader(Button homeButton, Button contactButton, Button aboutButton) {
        HBox header = new HBox(10);
        header.setAlignment(Pos.CENTER_LEFT); // Align to the left
        header.setStyle("-fx-background-color: lightgrey;"); // Set light grey background for header
        header.setMinHeight(40); // Twice the height

        // Create APOLLO text
        Text apolloText = new Text("APOLLO");
        apolloText.setFill(Color.BLACK);
        apolloText.setFont(Font.font("Arial", FontWeight.BOLD, 20)); // Larger font size
        HBox.setMargin(apolloText, new Insets(0, 10, 0, 10)); // Add some padding to move it to the right

        // Add spacing between APOLLO text and menu buttons
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(apolloText, spacer, homeButton, contactButton, aboutButton);

        return header;
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        return button;
    }

    private void addFormField(GridPane grid, String labelText, int col, int row) {
        addFormField(grid, labelText, col, row, new TextField());
    }

    private void addFormField(GridPane grid, String labelText, int col, int row, Control inputControl) {
        Label label = new Label(labelText);
        grid.add(label, col, row);
        grid.add(inputControl, col + 1, row);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
