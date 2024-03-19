package com.project.blog_app.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDataTransfer {
    private String title;
    private String content;
    private Date addedDate;
    private String img_name;

    private UserDataTransfer user;// ye isliye add kre hai taaki hum post aur user ko link kar ske database me
    private CategoryDataTransfer category;
    //ab yaha par note krne wali baat ye hai ki humner userdto add kiya hai na ki sirf user beacuse agar user add krye hai to
    //user wali class me list of post krkr ek mapping hai onetomany ab agar sirf user ka object lete hum yaha par aur response request
    //bhejte to phir wo infinite loop me chle jata because baar baar wo list return krte rehta.
    //userdatatransfer me esi koi list nahi hai na mapping to wo correct output retun krega.


}
