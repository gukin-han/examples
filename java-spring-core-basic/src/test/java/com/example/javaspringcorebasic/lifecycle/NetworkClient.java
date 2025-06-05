package com.example.javaspringcorebasic.lifecycle;

public class NetworkClient {

  private String url;

  public NetworkClient() {
    System.out.println("생성자 호출, url = " + url); // 아직 값이 없음
    connect(); // 아직 값이 없음
    call("초기화 연결 메시지");
  }

  public void setUrl(String url) {
    this.url = url;
  }

  // 서비스 시작시 호출
  public void connect() {
    System.out.println("connect: " + url);
  }

  public void call(String message) {
    System.out.println("call: " + url + ", message = " + message);
  }

  public void disconnect() {
    System.out.println("close: " + url);
  }
}
