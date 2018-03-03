package com.cefothe.bgjudge.user.repositories;

import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection.H2;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
public class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private User user;

    @Before
    public void before(){
        Role role = new Role("student");
        user = new User("test", "test", new UserInformation("Stefan", "Angelov", "cefothe@gmail.com"), role);
        roleRepository.save(role);
        userRepository.save(user);
    }

    @Test
    public void testFoundUserByUsername(){
        //THEN
        User founded = userRepository.findByUsername("test");
        //VERIFY
        Assert.assertEquals(user,founded);
    }

    @Test
    public void testFoundByEmail(){
        //THEN
        boolean result = userRepository.existsByUserInformationEmail("cefothe@gmail.com");
        //VERIFY
        Assert.assertTrue(result);
    }

}
