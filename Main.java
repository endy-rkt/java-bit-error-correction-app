package codageapplication;



import codageapplication.EntrelacePariteCode;
import codageapplication.HammingCode;
import codageapplication.RepetitionCode;
import codageapplication.SimplePariteCode;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Button button;
    Stage window;
   
    public void start(Stage primaryStage) throws Exception {
        // Setting the window
        window = primaryStage;
        
         // Setting the window
        window.setTitle("rakoto codage");
        
        // Grid settings
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);
        
        // Label type de codage
        Label codageLabel = new Label("Type de codage:");
        codageLabel.setId("labelColor");
        GridPane.setConstraints(codageLabel, 0, 0);
        
        //Parameter input
        TextField paramsInput=new TextField();
        paramsInput.setPromptText("Paramètre du codage...");
        paramsInput.setMinHeight(35);
        paramsInput.setDisable(true);
        GridPane.setConstraints(paramsInput, 0, 5);
        
        //Checkbox type de codage
        ChoiceBox<String> codageChoice=new ChoiceBox<>();
        codageChoice.getItems().addAll("codage Hamming","Codage par répétition","Codage par parité Entrelacé","Codage par parité simple");
        codageChoice.setValue("codage Hamming");
        codageChoice.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->handleCodageChoice(newValue,paramsInput));
        GridPane.setConstraints(codageChoice, 0, 1);
        
         //Checkbox  codage or decodage
        ChoiceBox<String> typeChoice=new ChoiceBox<>();
        typeChoice.getItems().addAll("Coder","Decoder");
        typeChoice.setValue("Coder");
        GridPane.setConstraints(typeChoice, 1, 1);
       
        // Label type de parité
        Label parityLabel = new Label("Type de parité:");
        parityLabel.setId("labelColor");
        GridPane.setConstraints(parityLabel, 0, 2);
        
        //Checkbox type de parité
        ChoiceBox<String> parityChoice=new ChoiceBox<>();
        parityChoice.getItems().addAll("Parité paire","Parité impaire");
        parityChoice.setValue("Parité paire");
        GridPane.setConstraints(parityChoice, 0, 3);
        
        //Parameter selection Label
        Label paramsLabel = new Label("Paramètre du codage:");
        paramsLabel.setId("labelColor");
        GridPane.setConstraints(paramsLabel, 0, 4);
        
        //Entry label
        Label entryLabel = new Label("Mot à coder:");
        entryLabel.setId("labelColor");
        GridPane.setConstraints(entryLabel, 0, 6);
        
        //Result input
        TextField entryInput=new TextField();
        entryInput.setPromptText("Entrée le message...");
        entryInput.setMinHeight(35);
        GridPane.setConstraints(entryInput, 0, 7);
        
        //Result label
        Label resultLabel = new Label("Résultat:");
        resultLabel.setId("labelColor");
        GridPane.setConstraints(resultLabel, 1, 6);
        
        //Result input
        TextField resultInput=new TextField();
        resultInput.setPromptText("Résultat du codage...");
        resultInput.setMinHeight(35);
        GridPane.setConstraints(resultInput, 1, 7);
        
        //launch button
        Button launchButton = new Button("Lancer");
        launchButton.setOnAction(e->{
            //Getting all user value 
            String message=entryInput.getText();
            String paramsValue=paramsInput.getText();
            String parityValue=parityChoice.getValue();
            String typeValue=typeChoice.getValue();
            String codageValue=codageChoice.getValue();
            String result="";
            
            if(Codage.isNumber(paramsValue))
                handleLaunchClicked(codageValue,typeValue,parityValue,paramsValue,message,result,resultInput);
            else
                AlertBox.display("Erreur", "Paramètre invalide");
            if(!result.equals(""))
                resultInput.setText(result);
        });
        GridPane.setConstraints(launchButton, 0, 8);
        
        //delete button
        Button deleteButton = new Button("Effacer");
        deleteButton.setOnAction(e->handleDeleteClicked(entryInput,resultInput));
        GridPane.setConstraints(deleteButton, 1, 8);
        
        // Layout
        grid.getChildren().addAll(codageLabel, codageChoice,typeChoice,parityLabel,parityChoice, paramsLabel, paramsInput, entryLabel,entryInput,resultLabel,resultInput,launchButton,deleteButton);
        Scene scene = new Scene(grid, 420,360);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        // Show the window
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void handleCodageChoice(String newValue,TextField paramsInput) {
         if(newValue.equals("codage Hamming") ||newValue.equals("Codage par parité simple")){
             paramsInput.setPromptText("Paramètre du codage...");
             paramsInput.setDisable(true);
         }
         else if ((newValue.equals("Codage par répétition"))){
             paramsInput.setDisable(false);
             paramsInput.setPromptText("Entrer le nombre de répétition...");
         }
         else if ((newValue.equals("Codage par parité Entrelacé"))){
             paramsInput.setDisable(false);
             paramsInput.setPromptText("Entrer le nombre de bloc...");
         }
    }

    private void handleLaunchClicked(String codageValue,String typeValue,String parityValue,String paramsValue,String message,String result,TextField resultInput) {
        if (message.equals(""))
            AlertBox.display("Erreur", "Entrée vide");
        else if (Codage.isBinary(message)==false)
            AlertBox.display("Erreur", "Entrée non binaire");
        else{
            launchFunctionality(codageValue,typeValue,parityValue,paramsValue,message,result,resultInput);
        }
    }

    private void handleDeleteClicked(TextField entryInput,TextField resultInput) {
       entryInput.clear();
       resultInput.clear();
    }

    private void launchFunctionality(String codageValue, String typeValue, String parityValue, String paramsValue, String message,String result,TextField resultInput) {
        if(typeValue.equals("Coder")){
            if(codageValue.equals("codage Hamming")){
                HammingCode.encode(message, result,resultInput);
            }
            else if(codageValue.equals("Codage par parité Entrelacé")){
                int params=1;
                if (!paramsValue.equals("")){
                    params=Integer.valueOf(paramsValue).intValue();
                }
                if(message.length()%params==0)
                    EntrelacePariteCode.encode(message,params,parityValue,resultInput);
                else
                    AlertBox.display("Erreur", "Paramètre invalide");
            }
            else if(codageValue.equals("Codage par parité simple")){
                SimplePariteCode.encode(message,parityValue,resultInput);
            }
            else{
                int params=1;
                System.out.println(params);
                if (!paramsValue.equals("")){
                    params=Integer.valueOf(paramsValue).intValue();
                }
                System.out.println(params);
                RepetitionCode.encode(message,params,resultInput);
            }
        }
        else{
            if(codageValue.equals("codage Hamming")){
                String verification="";
                HammingCode.decode(message, result, verification,resultInput);
            }
            else if(codageValue.equals("Codage par parité Entrelacé")){
                int params=1;
                if (!paramsValue.equals("")){
                    params=Integer.valueOf(paramsValue).intValue();
                }
                //if(message.length()%params==0)
                if (parityValue.equals("Parité paire"))
                    EntrelacePariteCode.decode(message,params,parityValue,resultInput);
                //else
                   // AlertBox.display("Erreur", "Paramètre invalide");
            }
            else if(codageValue.equals("Codage par parité simple")){
                SimplePariteCode.decode(message,parityValue,resultInput);
            }
            else{
                int params=1;
                if (!paramsValue.equals("")){
                    params=Integer.valueOf(paramsValue).intValue();
                }
                RepetitionCode.decode(message,params,resultInput);
            }
        }
    }
}
