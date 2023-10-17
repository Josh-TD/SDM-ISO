//package com.example.sdmisoback;
//
//import com.example.sdmisoback.model.Users;
//import com.example.sdmisoback.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseLoader implements CommandLineRunner {
//    private final UsersRepository repository;
//
//    @Autowired
//    public DatabaseLoader(UsersRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        this.repository.save(new Users("sdmisoteam@gmail.com", "sdm123"));
//    }
//}