package com.project.blog_app.service.implementation;

import com.project.blog_app.entities.User;
import com.project.blog_app.exception.ResourceNotFoundException;
import com.project.blog_app.payload.UserDataTransfer;
import com.project.blog_app.repository.UserRepository;
import com.project.blog_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDataTransfer createUser(UserDataTransfer userDataTransfer) {

        User user=this.userDataTransferToUser(userDataTransfer);
        User savedUser=this.userRepository.save(user);
        return this.UserTouserDataTransfer(savedUser);
    }

    @Override
    public UserDataTransfer updateUser(UserDataTransfer userDataTransfer, Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        user.setName(userDataTransfer.getName());
        user.setEmail(userDataTransfer.getEmail());
        user.setPassword(userDataTransfer.getPassword());
        user.setAbout(userDataTransfer.getAbout());

        User updatedUser=userRepository.save(user);
        UserDataTransfer userDataTransfer1=UserTouserDataTransfer(updatedUser);

        return userDataTransfer1;
    }

    @Override
    public UserDataTransfer getUserById(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.UserTouserDataTransfer(user);
    }

    @Override
    public List<UserDataTransfer> getAllUsers() {
        List<User> users=this.userRepository.findAll();
        List<UserDataTransfer> userDataTransfers=users.stream().map(user -> this.UserTouserDataTransfer(user)).collect(Collectors.toList());

        return userDataTransfers;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepository.delete(user);

    }

    //ye method UserDataTransfer wale class ke object ko User class ke object me convert kr rha hai.
    //esa isliye krna pad rha hai kyoki uper ki saari methods me hum pass krenge User class ka object kyoki repository me to
    //User entity hi jaegi na, pr idhar humari paas hai UserDataTransfer ka object jo ki hmari saari methods ka arguement hai
    //To isliye hume UserDataTransfer ke object ko convert krna pdega User ke object me.
    private User userDataTransferToUser(UserDataTransfer userDataTransfer)
    {
//        User user=new User();
        //now we will create new user object using the bean and model mapper class for conversion of user to userdatatransfer.
        User user=this.modelMapper.map(userDataTransfer,User.class);
                                        //source    ,   class name
        //below set of lines we used when we didn't had model mapper.

//        user.setId(userDataTransfer.getId());
//        user.setName(userDataTransfer.getName());
//        user.setEmail(userDataTransfer.getEmail());
//        user.setPassword(userDataTransfer.getPassword());
//        user.setAbout(userDataTransfer.getAbout());
        return user;

    }

    public UserDataTransfer UserTouserDataTransfer(User user)
    {
//        UserDataTransfer userDataTransfer=new UserDataTransfer();
//        userDataTransfer.setId(user.getId());
//        userDataTransfer.setName(user.getName());
//        userDataTransfer.setPassword(user.getPassword());
//        userDataTransfer.setEmail(user.getEmail());
//        userDataTransfer.setAbout(user.getAbout());
        UserDataTransfer userDataTransfer=this.modelMapper.map(user,UserDataTransfer.class);
        return userDataTransfer;
    }
}
