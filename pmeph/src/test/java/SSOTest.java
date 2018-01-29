import com.bc.pmpheep.general.service.UserService;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by lihuan on 2018/1/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-framework.xml"})//加载spring配置文件
public class SSOTest {

    @Autowired
    UserService userService;

    @Test
    public void testModifyPassword() throws IOException {
        int result = userService.modifyUser("13419658687", "haha");
        assertEquals(result, 1);
    }
}
