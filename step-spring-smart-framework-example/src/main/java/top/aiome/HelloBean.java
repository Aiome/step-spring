package top.aiome;

/**
 * Description: <br>
 *
 * @author mahongyan 2019-08-03 22:05
 */
public class HelloBean {
    private String name;
    private Integer age;

    public HelloBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
