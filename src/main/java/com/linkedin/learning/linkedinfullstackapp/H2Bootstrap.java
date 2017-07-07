package com.linkedin.learning.linkedinfullstackapp;


import com.linkedin.learning.linkedinfullstackapp.entity.RoomEntity;
import com.linkedin.learning.linkedinfullstackapp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstraping data: ");

        roomRepository.save(new RoomEntity(405, "200"));
        roomRepository.save(new RoomEntity(406, "300"));
        roomRepository.save(new RoomEntity(407, "400"));

        System.out.println("Printing out data: ");
        Iterable<RoomEntity> all = roomRepository.findAll();
        all.forEach(r -> {
            System.out.println(r.getRoomNumber());
        });
    }
}
