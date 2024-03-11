package com.project.blog_app.controller;

import com.project.blog_app.payload.ApiResponse;
import com.project.blog_app.payload.UserDataTransfer;
import com.project.blog_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/new")
    //response entity isliye use hota hai taki hum response bhej sake hmari reuqest accept hone ke baad http response.
    //esa krne ke liye hume humari method ka type bhi response entity rkhna pdega
    public ResponseEntity<UserDataTransfer> createUser(@Valid @RequestBody UserDataTransfer userDataTransfer) //@Valid is for validation
            //@RequestBody :- These two annotations will take care of everything required to convert
            // JSON request to Java object by using HttpMessageConverter, so that you can work directly with object
            // without worrying about converting JSON to Java object form Request and converting Java object to JSON message
            // while sending response.
            //ye sidhi baat hai ki hmare json object ko java object me convert krta hai aur return krte waqt java object ko json object me.
    {
        UserDataTransfer createUserDataTransfer=this.userService.createUser(userDataTransfer);
        return new ResponseEntity<>(createUserDataTransfer, HttpStatus.CREATED);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDataTransfer>> getAllUsers()
    {
//        List<UserDataTransfer> userDataTransfers=this.userService.getAllUsers();
//        return new ResponseEntity<>(userDataTransfers,HttpStatus.OK);
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDataTransfer> getUserById(@PathVariable Integer userId)
    {
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.FOUND);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDataTransfer> updateUser(@Valid @RequestBody UserDataTransfer userDataTransfer,@PathVariable Integer userId)
    {
        UserDataTransfer updatedUser=this.userService.updateUser(userDataTransfer,userId);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
    {
        this.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }



}

