package top.aiome;

import top.aiome.step.spring.smart.framework.annotation.Service;

/**
 * Description: <br>
 *
 * @author mahongyan 2019-08-03 22:05
 */
@Service
public class HelloService {

    public HelloBean getData() {
        return new HelloBean("马红岩" , 25);
    }
}
