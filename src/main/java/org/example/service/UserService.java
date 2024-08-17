package org.example.service;

import org.example.entity.User;
import org.example.entity.dto.JwtDTO;
import org.example.entity.dto.UserDTO;
import org.example.models.EnumRoles;
import org.example.repository.RoleRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByRole(EnumRoles.ROLE_USER));
        return userRepository.save(user);
    }

    public JwtDTO authUser(UserDTO user){
        Optional<User> userFromDB = userRepository.findByLogin(user.getLogin());
        if(userFromDB.isEmpty()){
            throw new BadCredentialsException("Пользователь с логином " + user.getLogin() + " не найден!");
        } else if(!passwordEncoder.matches(user.getPassword(), userFromDB.get().getPassword())){
            throw  new BadCredentialsException("Неправильный пароль!");
        }
        return new JwtDTO(jwtService.generate(user.getLogin()));
    }
}
