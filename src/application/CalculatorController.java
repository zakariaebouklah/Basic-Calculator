package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class CalculatorController implements Initializable{

    @FXML
    private Label Operations;

    @FXML
    private Label Result;

    @FXML
    private Button reset;

    @FXML
    private Button exit;

    private double n1 = 0.0;
    private double toto = 0.0;
    private String op = "";
    private boolean check = true;

    public double create(double n1, double n2, String op){

    	switch (op) {
		case "+":
			return n1 + n2;
		case "-":
			return n1 - n2;
		case "*":
			return n1 * n2;
		case "/":
			if (n2 == 0) {
				return 0.0;
			}
			return n1 / n2;
		default:
			break;
		}

    	return 0.0;
    }

    public void operation(String number){
    	Operations.setText(Operations.getText()+number);
    }

    public void operator(String operator){
    	Operations.setText(Operations.getText()+operator);
    }

    public void result(String number){
    	Result.setText(Result.getText()+number);
    }

    public void computingProcess(ActionEvent event){
    	if (check) {
			Operations.setText("");
			Result.setText("");

			check = false;
		}

    	Button B = (Button) event.getSource();
    	String value = B.getText();

    	operation(value);
    	result(value);
    }

    public void computingOperation(ActionEvent event){
    	Button B = (Button)event.getSource();
    	String value = B.getText();

    	if (!value.equals("ans")) {
			if (!op.isEmpty()) {
				return;
			}

			op = value;
			operator(op);
			n1 = Double.parseDouble(Result.getText());
			Result.setText("");

		}else {
			if (op.isEmpty()) {
				return;
			}
			double n2 = Double.parseDouble(Result.getText());
			toto = create(n1,n2,op);
			Result.setText(String.valueOf(toto));

			op = "";
			check = true;
		}

    }

    public void clearing(ActionEvent Event){
    	if (Event.getSource() == reset) {
			Result.setText("0");
			Operations.setText("");
		}
    }

    @FXML
    void enter(KeyEvent event) {
    	if (event.getCode().equals(KeyCode.ENTER)) {
    		Result.setText("0");
			Operations.setText("");
		}
    }

    @FXML
    void exit(ActionEvent event) {
    	if (event.getSource() == exit) {
    		Node  source = (Node)  event.getSource();
    	    Stage stage  = (Stage) source.getScene().getWindow();
    	    stage.close();
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

}

