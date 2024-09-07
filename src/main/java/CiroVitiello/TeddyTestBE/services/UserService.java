package CiroVitiello.TeddyTestBE.services;

import CiroVitiello.TeddyTestBE.DTO.NewUserDTO;
import CiroVitiello.TeddyTestBE.entities.User;
import CiroVitiello.TeddyTestBE.exceptions.BadRequestException;
import CiroVitiello.TeddyTestBE.exceptions.NotFoundException;
import CiroVitiello.TeddyTestBE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO ud;


    public List<User> getAllUsers(){
        return  this.ud.findAll();
    }

    public User save(NewUserDTO body) {
        List<User> found = this.ud.findAllByEmail(body.email());
        if (found.isEmpty()) {
            User newUser = new User(body.name(), body.surname(), body.address(), body.location(), body.municipality(), body.province(), body.email(), body.notes());
            return this.ud.save(newUser);
        }
        else {
            throw new BadRequestException(" email " + found.get(0).getEmail() + " già in uso!");
        }

    }

    public User findById(int id) {
        return this.ud.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(NewUserDTO body, int userId) {
        User found = this.findById(userId);
        if (found.getEmail().equals(body.email())) {
            found.setName(body.name());
            found.setSurname(body.surname());
            found.setAddress(body.address());
            found.setLocation(body.address());
            found.setMunicipality(body.address());
            found.setProvince(body.province());
            found.setNotes(body.notes());
        }

        if (!found.getEmail().equals(body.email())) {
            List<User> userList = this.ud.findAllByEmail(body.email());
            if (userList.isEmpty()) {
                found.setName(body.name());
                found.setSurname(body.surname());
                found.setSurname(body.surname());
                found.setAddress(body.address());
                found.setLocation(body.address());
                found.setMunicipality(body.address());
                found.setProvince(body.province());
                found.setEmail(body.email());
                found.setNotes(body.notes());
            }
            else {
                throw new BadRequestException(" email " + userList.get(0).getEmail() + " già in uso!");
            }
        }

       return this.ud.updateById(found, userId);

    }

    public void findByIdAndDelete(int id) {
       this.ud.deleteById(id);
    }


}
