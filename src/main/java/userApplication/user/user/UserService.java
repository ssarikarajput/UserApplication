package userApplication.user.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user){
        userRepository.save(user);
    }
    public List<User> getAllUser(){
                                    //List list=new ArrayList();
                                    //return list;
        return userRepository.findAll();
    }
    public User getUser(int userId){
        Optional<User> user= userRepository.findById(userId);
        return user.get();
    }
    public void updateUser(int userId, User user){
        user.setUserId(userId);
        userRepository.save(user);
    }
    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }
}
