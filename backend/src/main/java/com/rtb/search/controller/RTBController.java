package com.rtb.search.controller;

import com.rtb.search.entity.AdRequest;
import com.rtb.search.service.ElasticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.cluster.ClusterHealth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RTBController {

    private final ElasticService elasticService;
    private static final Logger logger = LoggerFactory.getLogger(RTBController.class);

    @Autowired
    RTBController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<AdRequest>> search(@RequestBody AdRequest adRequest) {
        if(adRequest != null) {
            List<AdRequest> listAdRequest = elasticService.search(adRequest);
            if(listAdRequest.size() > 0) {
                return ResponseEntity.ok().body(listAdRequest);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<AdRequest> upload(@RequestBody AdRequest adRequest) {
        logger.info("UPload Request Received");

        if(adRequest != null) {
            logger.info("calling elastic serivce upload method ");
            AdRequest adRequest1 =elasticService.upload(adRequest);
            if(adRequest1 != null) {
                return ResponseEntity.ok().body(adRequest1);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/chceckconnection")
    public ResponseEntity<ClusterHealth> checkConnection() {
        logger.info("check connection");
        ClusterHealth result = elasticService.checkConnection();
        return ResponseEntity.ok().body(result);
    }

}
