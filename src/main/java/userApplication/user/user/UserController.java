package userApplication.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("status")
    public  String status(){

        return "{\"status\": \"Server is working fine\"}";
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody(required = true) User user){

        userService.registerUser(user);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

                        /*List<User> userList=new ArrayList<>();
                        User user=new User();
                        user.setUserId(1);
                        user.setfName("sarika");
                        user.setlName("singh");
                        user.setEmailId("sa@gmail.com");
                        user.setDob("1-feb-1993");
                        user.setOccupation("Engineer");

                        userList.add(user);
                        userList.add(user); */
         List<User> userList= userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    @GetMapping("{userid}")
    public ResponseEntity<User>getUser(@PathVariable(value = "userid") Integer userId){

                        /*User user=new User();
                        user.setUserId(1);
                        user.setfName("sarika");
                        user.setlName("singh");
                        user.setEmailId("sa@gmail.com");
                        user.setDob("1-feb-1993");
                        user.setOccupation("Engineer");*/
        System.out.println("UserId........"+ userId);
        User user= userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("{userid}")
    public  ResponseEntity updateUser(@PathVariable(value = "userid") Integer userId, @RequestBody(required = true) User user){

                        //System.out.println("User has been updated:...." );
        userService.updateUser(userId,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("{userid}")
    public ResponseEntity deleteUser(@PathVariable(value = "userid") Integer userId){

                        System.out.println("deleted user id:" +userId);
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("uploadfile")
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        userService.readFile(file);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/uploadToDrive", method=RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException{

        File convertFile= new File("D:\\upload\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout=new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);

    }
    //Search GetMapping
    @GetMapping("/search")
    public ResponseEntity<List <User>> getAllByQuery(@RequestParam(value = "fName",required = false)String fName){
        List<User> userList=userService.searchUser(fName);
        return new ResponseEntity(userList,HttpStatus.OK);
    }
}
