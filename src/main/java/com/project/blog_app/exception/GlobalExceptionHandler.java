package com.project.blog_app.exception;

import com.project.blog_app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//ye annotation ye btata hai ki ye class exception handling wali class hai.
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    //ye annotation ye btata hai ki ye method call hogi jab exception aaega tab.
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {   //yaha par ho ye rha hai ki humne ek response entity bnai hai of type apiresponse class ki.
        //ye class humne declare kri hai hmari payload package me. Usme saare responses hai kya message display krwana hai
        //ek request aane ke baad.
        //ab humne is method ko ek object diya hai resourcenotfoundexception class ka.
        //ye object waha par jo message display kr rhe hai wo message ko fetch krega aur ek string me save krlega.
        //uske baad hum apirespone class ka ek object bnaenge aur uske constructor me hum msg and false pass krenge
        //because humne ye method call kri hai fail hone par i.e., user not found hone par.
        //last me hum return krenge responseentity with apirespone object and ok status.

        String msg= ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    //ye exception class tab imvoke hogi jab humne valid arguement pas nahi kiya hai response request me for eg. - jese ki email glt diya hai ya phir password ka req. mathc nahi hhui hai tb

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //exception ka naam hai MethodArguementNotValidException
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> response=new HashMap<>();
//        BindingResult holds the result of a validation and binding and contains errors that may have occurred.
//        The BindingResult must come right after the model object that is validated or else Spring fails to validate
//        the object and throws an exception.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError)(error)).getField();
            String message=error.getDefaultMessage(); // ye jo message aaya hai wo hmari userdatatransfer class ke arguements
            //ko declare krte waqt jo conditions lgai thi usme mentioned tha message wo display hoga yaha par.
            response.put(fieldName,message);
            //apne hasmap me fieldname as a key add hoga aur message as a value add hojaega.
            //iska mtlb saare errors jo bhi occur hue hai is exception ke under me wo saare hmare hashmap me add hojaenge.
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
