package com.example.javaspringcorebasic.singleton;

public class SingletonService {

    // 1. static 영역에 객체 1개 생성
    private static final SingletonService instance = new SingletonService();

    // 2. public 으로 객체 요청 대응
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자 private 선언하여 외부에서 new 키워드로 객체 생성할 수 없도록 방지
    private SingletonService() {
    }

    // 4. 싱글톤 객체에서 사용하고자 하는 로직
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
