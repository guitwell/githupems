import com.baizhi.GithupApplication;
import com.baizhi.entity.Deplement;
import com.baizhi.service.DeplementService;
import com.baizhi.service.EmployService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author : 张京斗
 * create_date : 2019/12/6 18:06
 * version : 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithupApplication.class)
public class DeptTest {
    @Autowired
    private DeplementService deplementService;
    @Autowired
    private EmployService employService;

    @Test
    public void test1() {
        System.out.println(deplementService.findByPage(1, 5));
    }

    @Test
    public void test2() {
        System.out.println(employService.findByPage(1, 5, "1"));
    }

    @Test
    public void test3() {
        System.out.println(deplementService.findByPage(1, 10));
    }

    @Test
    public void test4() {
        Deplement deplement = new Deplement("5", "105", "测试部", 0);
        deplementService.addOne(deplement);
    }

    @Test
    public void test5() {
        deplementService.deleteOne("5");
    }

}
