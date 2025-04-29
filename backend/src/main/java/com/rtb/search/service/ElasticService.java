package com.rtb.search.service;

import com.rtb.search.entity.AdRequest;
import com.rtb.search.repository.AdRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.cluster.ClusterHealth;
import org.springframework.data.elasticsearch.core.cluster.ClusterOperations;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticService {

    private final AdRequestRepository adRequestRepository;

    private final ElasticsearchTemplate elasticsearchRestTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ElasticService.class);

    @Autowired
    public ElasticService( AdRequestRepository adRequestRepository, ElasticsearchTemplate elasticsearchRestTemplate) {
        this.adRequestRepository = adRequestRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<AdRequest> search(AdRequest adRequest) {

        logger.info("Creating Search Criteria");

        List<Criteria> criteriaList = new ArrayList<>();

        //ID search
        if(adRequest.getId() != null) {
            logger.info("Creating Search Criteria with id : " + adRequest.getId());
            criteriaList.add(Criteria.where("id").is(adRequest.getId()));
        }

        //Imp search
        if(adRequest.getImp() != null) {
            for(AdRequest.Imp imp : adRequest.getImp()) {
                if(imp.getId() != null) {
                    logger.info("Creating Search Criteria with imp.id : " + imp.getId());
                    criteriaList.add(Criteria.where("imp.id").is(imp.getId()));
                }
                if(imp.getTagid() != null) {
                    logger.info("Creating Search Criteria with imp.tagid : " + imp.getTagid());
                    criteriaList.add(Criteria.where("img.tagid").is(imp.getTagid()));
                }
                
            }
        }

        //Device Search
        if(adRequest.getDevice() != null) {
            AdRequest.Device device = adRequest.getDevice();
            if(device.getMake() != null) {
                logger.info("Creating Search Criteria with device.make : " + device.getMake());
                criteriaList.add(Criteria.where("device.make").is(device.getMake()));
            }
            if(device.getGeo() != null) {
                AdRequest.Geo geo = device.getGeo();
                if(geo.getLat() != null) {
                    logger.info("Creating Search Criteria with device.geo.lat : " + geo.getLat());
                    criteriaList.add(Criteria.where("device.geo.lat").is(geo.getLat()));
                }
                if(geo.getLon() != null) {
                    logger.info("Creating Search Criteria with device.geo.lon : " + geo.getLon());
                    criteriaList.add(Criteria.where("device.geo.lon").is(geo.getLon()));
                }
            }
        }

        // App Search
        if(adRequest.getApp() != null) {
            AdRequest.App app = adRequest.getApp();
            if(app.getId() != null) {
                logger.info("Creating Search Criteria with app.id : " + app.getId());
                criteriaList.add(Criteria.where("app.id").is(app.getId()));
            }
            if(app.getName() != null) {
                logger.info("Creating Search Criteria with app.name : " + app.getName());
                criteriaList.add(Criteria.where("app.name").is(app.getName()));
            }
        }

        // User Search
        if(adRequest.getUser() != null) {
            AdRequest.User user = adRequest.getUser();
            if(user.getId() != null) {
                logger.info("Creating Search Criteria with user.id : " + user.getId());
                criteriaList.add(Criteria.where("user.id").is(user.getId()));
            }
        }

        // Combine all criteria
        Criteria combinedCriteria = new Criteria();
        if(!criteriaList.isEmpty()) {
            combinedCriteria = criteriaList.stream().reduce(Criteria::and).orElse(combinedCriteria);
        }

        // Create the query
        CriteriaQuery query = new CriteriaQuery(combinedCriteria);

        //Execute Query
        SearchHits<AdRequest> searchHits = elasticsearchRestTemplate.search(query, AdRequest.class);
        List<AdRequest> result = searchHits.stream().map(hit -> hit.getContent()).toList();
        logger.info("Search result size: " + result.size());
        return result;
    }

    public AdRequest upload(AdRequest adRequest) {
        logger.info("Uploading AdRequest inside Elasticsearch serivce");
        AdRequest result = adRequestRepository.save(adRequest);
        if(result != null) {
            return result;
        }
        
        return null;
    }

    public ClusterHealth checkConnection() {
        ClusterOperations clusterOperations = elasticsearchRestTemplate.cluster();
        ClusterHealth clusterHealth = clusterOperations.health();
        return clusterHealth;
    }
}
