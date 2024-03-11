package com.project.blog_app.payload;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//user entity ka data transfer krne ke liye ye userdatatransfer class bnai hai.
//saari get post put delete requests me hum isi class ka object pass krenge.
//kyoki jo hmari User class/entity hai wo structure hai aur uske saath ched chad nahi krni hai hume in future
//kuch change krna pde to hum easily kr skte hai without difficulty in data transfer because we have another class for
// the user data transfer.

//same goes for all entities.

@NoArgsConstructor  //ye lombok ka inbuilt annotation hai isse automatically no args constructor create ho jata hai.
@Getter   //same goes for getter and setter methods
@Setter
public class UserDataTransfer {
    private int id;

    @NotEmpty//validation annotation used for validating the data.
    @Size(min=4,message = "Username should be minimum of 4 alphabets")
    private String name;
    @Email(message = "Please enter a valid email")  //validates that the email provided is a valid email or not.
    private String email;
    @NotEmpty
//    @Size(min=5,max = 10,message = "Password must be in range of 5-10")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit in range of 4-12")
    private String password;
    @NotEmpty
    private String about;
}
