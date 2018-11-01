package com.codegym.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class User implements Validator {
    private String firstName;
    private String lastName;
    private String number;
    private String email;
    private int age;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String number = user.getNumber();
        String email = user.getEmail();
        int age = user.getAge();
        ValidationUtils.rejectIfEmpty(errors,"firstName","firstName.empty");
        ValidationUtils.rejectIfEmpty(errors,"lastName","lastName.empty");
        if(firstName.length()<5||firstName.length()>45){
            errors.rejectValue("firstName","firstName.length");
        }
        if(lastName.length()<5||lastName.length()>45){
            errors.rejectValue("lastName","lastName.length");
        }
        if(age<18||age>60){
            errors.rejectValue("age","age.size");
        }
        if(!number.matches("^0[0-9]{9,10}$")){
            errors.rejectValue("number","number.matches");
        }
        if(!email.matches("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")){
            errors.rejectValue("email","email.matches");
        }
    }
}
