package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;

public record User(
    String firstName,
    String lastaName,
    String address,
    String city,
    String state,
    Integer zipCode,
    String phone,
    String ssn,
    String userName,
    String password
) {

    final static Faker faker = new Faker();

    public static User generateUser(String type) {
     
        switch (type.toString().toUpperCase()) {
            case "VALIDO":
                return generateRandomData();
            case "VACIO":
                return generateEmptyUser();
            case "CONTRASEÑA_INVALIDA":
                return generataWrongPassword();
            case "CARACTER_ESPECIAL":
                return generateUserWithCaracterSpecial();
            default:
                throw new IllegalArgumentException("Tipo de usuario no valido");            
            }

    }     

    public static User generateRandomData() {

         
        final String firstName  = faker.name().firstName();
        final String lastaName = faker.name().lastName();
        final String address = faker.address().fullAddress();
        final String city = faker.address().city();
        final String state = faker.address().state();
        final int zipCode = faker.number().numberBetween(100000, 300000);
        final String phone = faker.phoneNumber().phoneNumber();
        final String ssn = faker.idNumber().ssnValid();
        final String userName = faker.name().username().replace(".", "");
        final String password = faker.internet().password(6,8);
        
        return new User(firstName, lastaName, address, city, state, zipCode, phone, ssn, userName, password);
    }

    public static User generateEmptyUser(){
        return new User("", "", "", "", "", null, "", "", "", "");
    }

    public static User generataWrongPassword(){
        final String firstName  = faker.name().firstName();
        final String lastaName = faker.name().lastName();
        final String address = faker.address().fullAddress();
        final String city = faker.address().city();
        final String state = faker.address().state();
        final int zipCode = faker.number().numberBetween(100000, 300000);
        final String phone = faker.phoneNumber().phoneNumber();
        final String ssn = faker.idNumber().ssnValid();
        final String userName = generateRandomWord(specialCharacters, 8);
        final String password = generateRandomWord(specialCharacters, 8);
        
        return new User(firstName, lastaName, address, city, state, zipCode, phone, ssn, userName, password);
    }

    public static User generateUserWithCaracterSpecial(){
        final String firstName  = generateRandomWord(specialCharacters, 10);
        final String lastaName = generateRandomWord(specialCharacters, 10);
        final String address = faker.address().fullAddress();
        final String city = faker.address().city();
        final String state = faker.address().state();
        final int zipCode = faker.number().numberBetween(100000, 300000);
        final String phone = faker.phoneNumber().phoneNumber();
        final String ssn = faker.idNumber().ssnValid();
        final String userName = faker.name().username().replace(".", "");
        final String password = faker.internet().password(6,8);
        
        return new User(firstName, lastaName, address, city, state, zipCode, phone, ssn, userName, password);
    }

    public static String generateRandomWord(List<String> list, int length){
        Random random = new Random();

        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++) {
            word.append(list.get(random.nextInt(list.size())));
            
        }

        return word.toString();
        
    }

    final static List<String> specialCharacters = new ArrayList<>(List.of("!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+",
        "[", "]", "{", "}", "|", "\\", ":", ";", "\"", "'", "<", ">", ",", ".", "/", "?",
        " ", 
        "±", "÷",  "√", "∞", "≈", "≠", "≤", "≥", // Mathematical symbols
        "€", "£", "¥", "₹", "₽", "₿" // Currency symbols)
        
    ));
    
    public static void main(String[] args) {
        String word = generateRandomWord(specialCharacters, 10);

        System.out.println(word);
    }




    
} 

   

