package vn.billbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import vn.billbooking.utils.ValidatorUtil;

@SpringBootTest
class WebappApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void testEmail() {
        String email = "abc@gmail.com";
        Assertions.assertTrue(ValidatorUtil.checkEmail(email));

        String email2 = "abcgmail.com";
        Assertions.assertFalse(ValidatorUtil.checkEmail(email2));

    }

}
