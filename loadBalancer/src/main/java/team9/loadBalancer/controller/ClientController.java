package team9.loadBalancer.controller;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import team9.loadBalancer.domain.Reservation;
import team9.loadBalancer.domain.Server;
import team9.loadBalancer.scheduler.ClientScheduler;
import team9.loadBalancer.service.LbService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class ClientController {

    private final LbService lbService;
    private final static int[] START_TIME = {12, 00, 00}; //12:00:00 12시 0분 0초를 의미함
    public ClientController(LbService lbService) {
        this.lbService = lbService;
    }

    @GetMapping("/")
    public String home(){ return "home"; }

    @GetMapping("/reservation")
    public String showGetReservedTiketPage(){ //예매한 티켓을 확인하는 ~

        return "reservation/reservedForm";
    }

    @PostMapping("/reservation") //예매라는 프로세스를 진행하는 것이니까 Post
    public String showReservationPage(){
        if(clientAccessible()) return "reservation/reservationForm";
        return "redirect:/"; //시간이 맞지 않으면 걸러준다 -> 로그 기록 필요없음
    }

    @PostMapping("/reservation/new") //예매~
    public String reserveTicket(ReservationForm form){
        Reservation rsv = new Reservation();
        rsv.setName(form.getName());
        rsv.setDateOfBirth(form.getDateOfBirth());
        rsv.setPhoneNumber(form.getPhoneNumber());
        rsv.setSeatNumber(form.getSeatNumber());

        ResponseEntity<String> response = lbService.reserveTicket(rsv);
        return "redirect:/"; //홈화면 -> 예매 성공 or 실패 화면
    }

    @GetMapping("/reservation/{id}") //저장된 티켓을~ 그러면 객체로 못보내지 않을까?
    public String getReservedTicket(ReservationForm form){
        Reservation rsrv = new Reservation();
        rsrv.setName(form.getName());
        rsrv.setDateOfBirth(form.getDateOfBirth());
        rsrv.setPhoneNumber(form.getPhoneNumber());
        rsrv.setSeatNumber(form.getSeatNumber());

        return "redirect:/"; //홈화면 -> 예매확인 화면
    }
    public boolean clientAccessible(){
        String[] timeArr = LocalDateTime.now()
                .toString().split("T")[1].split(":|\\.");
        //시간 데이터 -> 01:34:42.317187200 데이터가 01 , 34, 42, .34123~ 로 쪼개져있음
        return Integer.parseInt(timeArr[0]) >= START_TIME[0]  ? true: false; //분은 고려 안함
    }
    public String renewtime(){ return new String();} //현재 시간을 이쁘게 -> 근데 로그 라이브러를 쓰면 알아서 되지 않을까





//    @GetMapping("/") //Dsr
//    public String forwardClientIp(HttpServletRequest request){ //클라이언트의 ip주소를 받아서 전달~
//        System.out.println("forwardClientIpToServer");
//
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = restTemplate.headForHeaders("http://localhost:8081");
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
//        body.add("client_ip", "1234");
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body);
//
//        // HTTP POST 요청
//        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8081", HttpMethod.POST, entity, String.class);
//
//        // HTTP POST 요청에 대한 응답 확인
//        System.out.println("status : " + response.getStatusCode());
//        System.out.println("body : " + response.getBody());
//
//
//        return "error";
//    }

//    @GetMapping("/") //비동기 테스트
//    @ResponseBody
//    public ResponseEntity<String> server(HttpServletRequest request){ // 비동기식으로 개선
//        ResponseEntity<String>  response = lbService.getResponse();
//        //여기서 클라이언트의 요청을 알 수있는 방법이 없을 까
//        //클라이언트의 ip주소를 알고있음
//        request.getRemoteAddr();
//        System.out.println( request.getRemoteAddr());
//        RestTemplate restTemplate = new RestTemplate();
////        String url = "http://127.0.0.1:8079"; //
//        String url = "http://222.109.76.15:80";
//        ResponseEntity<String> r = restTemplate.getForEntity(url, String.class);
//        //get message는 이렇게만 써도 가능함
//        while(true) {if(r == null) break;}
//        System.out.println("여기 실행되면 이상해");
//        return response;
//    }


    /*    public String server(){
        String url;
        if(count == 0 ){url = server[0]; count = 1;}
        else{ count = 0; url = server[1]; }
        RestTemplate restTemplate = new RestTemplate();

//        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getHeaders().toString());
        System.out.println(response.getStatusCode().toString());
        System.out.println(response.getBody().toString());
        //get message는 이렇게만 써도 가능함
        return response.getBody(); //이렇게 넘겨주지말고 responseentitiy전체를 넘기면?
    }*/

//    @PostMapping("/date") //uri 다시 설계해야함
//    @ResponseBody
//    public String server2(){
//        String url = "http://localhost:8081";
//        RestTemplate restTemplate = new RestTemplate(); //동기라서
//                                                    // 추가 할점은? 비동기로 개선하
//        //서버에서 제공해주는 내용을 조금더 복잡하게 ? ?? 좀더 세련되 서버 수도 늘려야할것같고
//        //
//        HttpHeaders httpHeaders = restTemplate.headForHeaders(url); //헤더를 이렇게 사용!
//
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        String body = "";
//        HttpEntity<?> requestMessage = new HttpEntity<>(body, httpHeaders);
//
//        HttpEntity<String> response = restTemplate.getForEntity(url, String.class);
//        //get message는 이렇게만 써도 가능하네~?
//        return response.getBody();
//    }

/*    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<String> server(){

        ResponseEntity<String> response = lbService.getResponse();
        return response; //이렇게 해도 잘 되긴 함

  *//*      RestTemplate restTemplate = new RestTemplate(); //동기식

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//        System.out.println(response.getHeaders().toString());
//        System.out.println(response.getStatusCode().toString());
//        System.out.println(response.getBody().toString());
        //get message는 이렇게만 써도 가능함
        return response; //이렇게 해도 잘 되긴 함*//*
    }*/


}
