package com.example.finaltest;

import com.example.finaltest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin()
public class ApiController {

    private String uri = "http://dev3.dansmultipro.co.id/mock/preprod-web/scrt/esb/v1/offer/reseller?menu_id=ML3_DP_03";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InitialRepository initialRepository;


    //Nomor Dua
    @GetMapping("/api/product")
    public @ResponseBody List<OfferItem> getProduct(){

        ResponseEntity<ResponseProduct> response = restTemplate.getForEntity(uri, ResponseProduct.class);
        ResponseProduct offer = response.getBody();

        return offer.getOffer();
    }

    //Nomor Tiga
    @PostMapping("/api/package-activation")
    public String createInitial(@RequestBody Initial initial){

        UUID uuid = UUID.randomUUID();
        initial.setUuid(String.valueOf(uuid));

        initialRepository.saveInitial(initial);
        return "Initial{" +
                "uuid='" + uuid + '\'' +
                ", status='" + HttpStatus.OK + '\'' +
                '}';
    }

//    @GetMapping("/api/package-activation")
//    public List<Initial> getAll(){
//        return initialRepository.getInitial();
//    }
//
//    @GetMapping("/api/package-activation/{id}")
//    public Initial findById(@PathVariable("id") String id){
//        return initialRepository.getById(id);
//    }

    //Nomor Empat Masih Error
    @PostMapping("/api/package-activation/confirmation")
    public @ResponseBody ResponseActivation getAct(@RequestParam String token,
                              @RequestParam String pin) {
        Initial initial = initialRepository.getById(token);

        String final_uri = "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller?"+
                "msisdn="+initial.getMsisdn() +
                "&productId="+initial.getProduct_id() +
                "&pin="+pin;

        ResponseActivation responseActivation = restTemplate.postForObject(final_uri, initial, ResponseActivation.class);

        return responseActivation;
    }

}
