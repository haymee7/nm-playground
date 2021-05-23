package kr.co.zzimcar.codeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Optional1 {

    static List<Person> persons = new ArrayList<>();
    public static void main(String[] args) {
////////////////////////////////////////////   Optional 필요이유/정의   ////////////////////////////////////////////////////////////////
        // Person p = new Person()은 Person을 사용하기 위해 메모리를 할당 한 후, 그 레퍼런스를 p
        // 에게 할당하라는 뜻이다. 그러나 Person p = null 은 아무런 레퍼런스도 없으므로 NPE가 발생
//        Person p = null;
//        System.out.println(p.name);

////////////////////////////////////////////   Optional.empty()   ////////////////////////////////////////////////////////////////
//        Optional<Person> oP = Optional.empty(); // 이 Optional클래스는 Person타입의 오브젝트를 저장 할 것.
//                                                // Optional 오브젝트를 생성하되 안의 Person은 null로 지정 할 것
//
//        if(oP.isPresent()) {                    // Optional안에 Person오브젝트가 존재하나? return ture false
//            Person p = oP.get();                // Optional안의 Person 오브젝트 가져올것
//            System.out.println("p exists " + p.name);
//        }

////////////////////////////////////////////   Optional.of()   ////////////////////////////////////////////////////////////////
//        Optional<Person> oP = Optional.of(new Person("zzimcarOf", 17)); // of는 오브젝트가 확실히 null이 아닐경우
//        if(oP.isPresent()) {
//            Person p = oP.get();
//            System.out.println("p name " + p.name);
//        }
        
////////////////////////////////////////////   Optional.ofNullable()   ////////////////////////////////////////////////////////////////
//        Optional<Person> oP = Optional.ofNullable(new Person("zzimcarOfNull", 17)); // ofNullable는 오브젝트가 null인지 아닌지 알수 없음
//        if(oP.isPresent()) {
//            Person p = oP.get();
//            System.out.println("p name " + p.name);
//        }

////////////////////////////////////////////   Optional.orElse()   ////////////////////////////////////////////////////////////////
//        String name = "zzimcarOrElse";
//        // orElse는 오브젝트가 null이든 말든 일단 실행하고 본다.
//        Person p = Optional.ofNullable(search(name)).orElse(new Person(name, 17));
//        System.out.println("p name " + p.name + " p age = " + p.age);

////////////////////////////////////////////   Optional.orElseGet()   ////////////////////////////////////////////////////////////////
//        String name = "zzimcarOrElseGet";
//        // orElseGet은 오브젝트가 null일 경우에만 쓸 수 있다.
//        Person p = Optional.ofNullable(search(name)).orElseGet(() -> createPerson(name, 17));
//        System.out.println("p name " + p.name + " p age = " + p.age);

////////////////////////////////////////////   Optional.orElseThrow()   ////////////////////////////////////////////////////////////////
        Person person = null;
        Person p = Optional.ofNullable(person).orElseThrow(() -> new RuntimeException()); // orElseThrow()는 null 일경우 발생시킬 Exception을 쓸때 사용한다.
        System.out.println("p exists " + p.name + " " + p.age);
    }


////////////////////////////////////////////   Optional을 위한 코드   ////////////////////////////////////////////////////////////////
    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    public static Person search(final String name) {
        for(Person p : persons) {
            if(p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public static Person createPerson(final String name, final int age) {
        return new Person(name, age);
    }
}
