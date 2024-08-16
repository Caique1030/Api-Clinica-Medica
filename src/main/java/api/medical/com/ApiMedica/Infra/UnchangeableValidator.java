package api.medical.com.ApiMedica.Infra;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UnchangeableValidator implements ConstraintValidator<Unchangeable, String> {
    private String originalValue;

    @Override
    public void initialize(Unchangeable constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // A lógica de validação deve garantir que o valor não tenha sido alterado
        return originalValue == null || originalValue.equals(value);
    }

    public void setOriginalValue(String originalValue) {
        this.originalValue = originalValue;
    }
}
