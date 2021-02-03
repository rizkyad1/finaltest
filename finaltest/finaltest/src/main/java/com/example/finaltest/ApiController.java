package com.example.finaltest;

import com.example.finaltest.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@CrossOrigin()
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    private String uri = "http://dev3.dansmultipro.co.id/mock/preprod-web/scrt/esb/v1/offer/reseller?menu_id=ML3_DP_03";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private InitialRepository initialRepository;

    @Autowired
    private ProductRedisRepo productRedisRepo;

    //Nomor Dua
    @GetMapping("/api/product")
    public @ResponseBody List<OfferItem> getProduct(){

        ResponseEntity<ResponseProduct> response = restTemplate.getForEntity(uri, ResponseProduct.class);
        ResponseProduct offer = response.getBody();

        ProductRedis productRedis = new ProductRedis();
        for (int i=0; i<offer.getOffer().size(); i++){
            productRedis.setId(offer.getOffer().get(i).getId());
            productRedis.setName(offer.getOffer().get(i).getName());
            productRedis.setPrice(offer.getOffer().get(i).getPrice());
        }
        productRedisRepo.save(productRedis);

        return offer.getOffer();
    }

    //Nomor Tiga
    @PostMapping("/api/package-activation")
    public Initial createInitial(@RequestBody Initial initial){

        UUID uuid = UUID.randomUUID();
        initial.setUuid(String.valueOf(uuid));

        return initialRepository.save(initial);
    }

    @GetMapping("/api/package-activation")
    public Iterable<Initial> getAll(){
        return initialRepository.findAll();
    }

    @GetMapping("/api/package-activation/{id}")
    public Optional<Initial> findById(@PathVariable("id") String id){
        return initialRepository.findById(id);
    }

    //Nomor Empat
    @PostMapping("/api/package-activation/confirmation")
    public ResponseActivation getAct(@RequestBody Activation activation) {

        String final_uri = "http://dev3.dansmultipro.co.id/mock/sit-web/secure/esb/v1/order/reseller";

        Optional<Initial> initial = initialRepository.findById(activation.getToken());
        String id = initial.get().getProduct_id();

        Optional<ProductRedis> productRedis = productRedisRepo.findById(id);

        ResponseActivation res = new ResponseActivation();

        HttpHeaders requestHeaders = new HttpHeaders();
        // untuk ngatasi wildcard "*"
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseActivation> entity = new HttpEntity<>(res, requestHeaders);

        ResponseEntity<ResponseActivation> responseEntity = restTemplate.exchange(
                final_uri,
                HttpMethod.POST,
                entity,
                ResponseActivation.class
        );
        String activ_id = responseEntity.getBody().getTransaction().getTransactionId();

        Paket paket = new Paket();
        paket.setTransaction_id(activ_id);
        paket.setMsisdn(initial.get().getMsisdn());
        paket.setProduct_id(productRedis.get().getId());
        paket.setProduct_name(productRedis.get().getName());
        paket.setProduct_price(productRedis.get().getPrice());
        paket.setCreated_by(1);
        packageRepository.save(paket);

        return responseEntity.getBody();
    }

    //Nomor Lima
    @GetMapping("/api/transaction/history")
    public List<Paket> getPackage(){
        return packageRepository.findAll();
    }

    //Nomor Enam
    @GetMapping("/api/transaction/history/{id}")
    public Optional<Paket> getPackageById(@PathVariable(value = "id") String id){
        Optional<Paket> pack = packageRepository.findById(id);
        return pack;
    }

}
