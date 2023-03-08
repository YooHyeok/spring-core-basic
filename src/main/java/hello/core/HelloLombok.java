package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 롬복 적용 클래스
 * Setter Getter 적용 및 ToString
 */
@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");
        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
    }
}
