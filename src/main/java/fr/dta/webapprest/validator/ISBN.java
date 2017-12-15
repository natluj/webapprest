package fr.dta.webapprest.validator;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=ISBNValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ISBN {
	String message() default "{validator.isbn}";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
	
}
