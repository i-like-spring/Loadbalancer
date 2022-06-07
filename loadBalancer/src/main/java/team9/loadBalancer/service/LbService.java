package team9.loadBalancer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import team9.loadBalancer.domain.Reservation;
import team9.loadBalancer.domain.Server;
import team9.loadBalancer.scheduler.ClientScheduler;

@Service
public class LbService {

    private final ClientScheduler scheduler;
    private WebClient client;
    private RestTemplate restTemplate;
    public LbService(ClientScheduler scheduler) {
        this.scheduler = scheduler;
        restTemplate = new RestTemplate();
//        client = WebClient
//                .builder()
//                .baseUrl("") //Json으로 뿌려주면 그냥 웹 페이지가 알아서 해석해서 html로 바꾸는건가
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//                //추가적인 set들은 천천히
    }

    public ResponseEntity<String> reserveTicket(Reservation rsv){ //forward Client Request To Server
        Server server = scheduler.schedule();
        //post 방식으로 서버에게 전송해야함.

        //헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 파라미터 세팅
        MultiValueMap<String, String> body= new LinkedMultiValueMap<>();
        body.add("name",  rsv.getName());
        body.add("dateOfBirth", rsv.getDateOfBirth());
        body.add("phoneNumber", rsv.getPhoneNumber());
        body.add("seatNumber", rsv.getSeatNumber());

        // 요청 세팅 완료
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(server.getUrl(), request, String.class);

        System.out.println("this is~");
        return response;
    }


    public ResponseEntity<String> getReservedTicket(Reservation rsv){ //forward Client Request To Server
        Server server = scheduler.schedule();
        //post 방식으로 서버에게 전송해야함.

        //헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 파라미터 세팅
        MultiValueMap<String, String> body= new LinkedMultiValueMap<>();
        body.add("name",  rsv.getName());
        body.add("dateOfBirth", rsv.getDateOfBirth());
        body.add("phoneNumber", rsv.getPhoneNumber());
        body.add("seatNumber", rsv.getSeatNumber());

        // 요청 세팅 완료
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(server.getUrl(), request, String.class);

        System.out.println("this is~");
        return response;
    }


//    public String forwardClientIpToServer(String clientUrl){
//        System.out.println("forwardClientIpToServer");
//        Server server = scheduler.schedule();
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(5000); // 타임아웃 설정 5초
//        factory.setReadTimeout(5000); // 타임아웃 설정 5초
//
//        restTemplate = new RestTemplate(factory);
//        HttpHeaders headers = restTemplate.headForHeaders(server.getUrl());
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//        body.add("client_ip", clientUrl);
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body);
//
//        // HTTP POST 요청
//        ResponseEntity<String> response = restTemplate.exchange(server.getUrl(), HttpMethod.POST, entity, String.class);
//
//        // HTTP POST 요청에 대한 응답 확인
//        System.out.println("status : " + response.getStatusCode());
//        System.out.println("body : " + response.getBody());
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getForEntity(server.getUrl() ,String.class);
//
//        return "error";
//    }

//    public ResponseEntity<String> getResponse(){
//        Server server = scheduler.schedule();
//        String url = server.getUrl();
//
//        Mono<ResponseEntity<String>> result = client.get()
//                .uri(server.getUrl())
//                .retrieve()
//                .toEntity(String.class);
//        //5초이상 걸리면 set time out
//        return result.block();
//    }


//    public String server(){
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://localhost:8080/spring";
//        ResponseEntity<String> response
//                = restTemplate.getForEntity(url,String.class);
//        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("filename", "제발되면 좋겠다..");
//        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
//
//        HttpEntity<String> response = restTemplate.postForEntity(url, requestMessage, String.class);
//
//        return response.getBody();
//
//    }

/*    public String hello(){

   *//*     RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:7";

        HttpHeaders httpHeaders = restTemplate.headForHeaders(url); //헤더 생성
        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);*//*

        return"";
    }*/

    //    public ResponseEntity<String> getResponse(){
//        Server server = scheduler.schedule();
//        String url = server.getUrl();
// /*       HttpClient httpClient = HttpClientBuilder.create()
//                .setMaxConnTotal(120) // maxConnTotal은 연결을 유지할 최대 숫자
//                .setMaxConnPerRoute(60) // maxConnPerRoute는 특정 경로당 최대 숫자
//                .setConnectionTimeToLive(5, TimeUnit.SECONDS) // keep - alive
//                .build();*/
//
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000); // 읽기시간초과, ms
//        factory.setConnectTimeout(3000); // 연결시간초과, ms
////        factory.setHttpClient(httpClient); // 동기실행에 사용될 HttpClient 세팅
//
//        WebClient webClient = WebClient
//                .builder()
//                .baseUrl("http://localhost:8080")
//                .defaultCookie("쿠키","쿠키값")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//
//
//        RestTemplate restTemplate = new RestTemplate(factory);
//        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//        return response; //메시지 body에 html을 string으로 넣어줘도 실행이 되는게 신기함
//    }

}
