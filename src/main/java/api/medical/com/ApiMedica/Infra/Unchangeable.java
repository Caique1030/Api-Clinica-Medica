package api.medical.com.ApiMedica.Infra;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnchangeableValidator.class)
public @interface Unchangeable {
    String message() default "Este campo não pode ser alterado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
