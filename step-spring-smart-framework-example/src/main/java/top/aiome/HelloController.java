package top.aiome;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import top.aiome.step.spring.smart.framework.annotation.Action;
import top.aiome.step.spring.smart.framework.annotation.Controller;
import top.aiome.step.spring.smart.framework.annotation.Inject;
import top.aiome.step.spring.smart.framework.bean.Data;
import top.aiome.step.spring.smart.framework.bean.Param;
import top.aiome.step.spring.smart.framework.bean.View;

/**
 * Description: <br>
 *
 * @author mahongyan 2019-08-03 22:03
 */
@Controller
public class HelloController {

    @Inject
    private HelloService mHelloService;

    @Action("get:/index")
    public View index(Param param) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());

        return new View("hello.jsp").addModel("currentTime", currentTime);
    }

    @Action("get:/json")
    public Data json(Param param) {
        return new Data(mHelloService.getData());
    }
}
