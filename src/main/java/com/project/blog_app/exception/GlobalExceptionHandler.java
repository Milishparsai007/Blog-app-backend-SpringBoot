package com.project.blog_app.exception;

import com.project.blog_app.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
