package mk.ukim.finki.prva_aud_veb;

import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.Role;
import mk.ukim.finki.prva_aud_veb.model.exception.*;
import mk.ukim.finki.prva_aud_veb.repository.jpa.UserRepository;
import mk.ukim.finki.prva_aud_veb.service.implementation.UserServiceImplementation;
import org.hibernate.tool.schema.internal.IndividuallySchemaValidatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTestUnit {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImplementation userServiceImplementation;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surename", Role.ROLE_USER);
        Mockito.when(this.userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        this.userServiceImplementation = Mockito.spy(new UserServiceImplementation(this.userRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister(){
        User user = this.userServiceImplementation
                .register("username", "password", "password", "name", "surename", Role.ROLE_USER);
        Mockito.verify(this.userServiceImplementation)
                .register("username", "password", "password", "name", "surename", Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("name do not mach", "name", user.getName());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("surename do not mach", "surename", user.getSurname());
        Assert.assertEquals("password do not mach", "password", user.getPassword());
        Assert.assertEquals("username do not mach", "username", user.getUsername());

    }

    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userServiceImplementation
                        .register(null, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(null, "password", "password", "name", "surename", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userServiceImplementation
                        .register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userServiceImplementation
                        .register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.userServiceImplementation
                        .register(username, password, "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(username, password, "password", "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordDoNotMatch.class,
                () -> this.userServiceImplementation
                        .register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(username, password, confirmPassword, "name", "surename", Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        User user = new User("username", "password", "name", "surename", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExist.class,
                () -> this.userServiceImplementation
                        .register(username, "password", "password", "name", "surename", Role.ROLE_USER));
        Mockito.verify(this.userServiceImplementation)
                .register(username, "password", "password", "name", "surename", Role.ROLE_USER);
    }
}

