package model.validation;

import model.Client;
import java.util.List;

public class ClientValidator {

    private final Client client;
    private List<String> errors;

    public ClientValidator(Client client)
    {
        this.client =client;
    }

    public boolean validate()
    {
        validatePersonalNumericalCode(client.getCNP());
        return errors.isEmpty();
    }

    private void validatePersonalNumericalCode(String cnp){
        if (cnp == null || cnp.trim().length() != 5){
            errors.add("Personal Numerical Code should be 5 digits long");
        }
        for (Character c: cnp.trim().toCharArray()){
            if (!Character.isDigit(c)){
                errors.add("Personal Numerical Code should contain only digits");
                return;
            }
        }
    }
    
}
