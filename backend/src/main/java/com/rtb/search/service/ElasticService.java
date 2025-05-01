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
        if (adRequest.getBcat() != null) {
            logger.info("Creating Search Criteria with bcat : " + adRequest.getBcat());
            criteriaList.add(Criteria.where("bcat").is(adRequest.getBcat()));
        }
        if (adRequest.getBadv() != null) {
            logger.info("Creating Search Criteria with badv : " + adRequest.getBadv());
            criteriaList.add(Criteria.where("badv").is(adRequest.getBadv()));
        }

        //Imp search
        if(adRequest.getImp() != null) {
            for(AdRequest.Imp imp : adRequest.getImp()) {
                if(imp.getId() != null) {
                    logger.info("Creating Search Criteria with imp.id : " + imp.getId());
                    criteriaList.add(Criteria.where("imp.id").is(imp.getId()));
                }
                if(imp.getInstl() != null) {
                    logger.info("Creating Search Criteria with imp.instl : " + imp.getInstl());
                    criteriaList.add(Criteria.where("imp.instl").is(imp.getInstl()));
                }
                if(imp.getTagid() != null) {
                    logger.info("Creating Search Criteria with imp.tagid : " + imp.getTagid());
                    criteriaList.add(Criteria.where("imp.tagid").is(imp.getTagid()));
                }
                if(imp.getBidfloor() != null) {
                    logger.info("Creating Search Criteria with imp.bidfloor : " + imp.getBidfloor());
                    criteriaList.add(Criteria.where("imp.bidfloor").is(imp.getBidfloor()));
                }

                //Banner search Start Here
                if(imp.getBanner() != null) {
                    AdRequest.Banner banner = imp.getBanner();
                    logger.info("Creating Search Criteria with imp.banner : " + banner);

                    if (banner.getW() != null) {
                        logger.info("Creating Search Criteria with imp.banner.w : " + banner.getW());
                        criteriaList.add(Criteria.where("imp.banner.w").is(banner.getW()));
                    }
                    if (banner.getH() != null) {
                        logger.info("Creating Search Criteria with imp.banner.h : " + banner.getH());
                        criteriaList.add(Criteria.where("imp.banner.h").is(banner.getH()));
                    }
                    if (banner.getPos() != null) {
                        logger.info("Creating Search Criteria with imp.banner.pos : " + banner.getPos());
                        criteriaList.add(Criteria.where("imp.banner.pos").is(banner.getPos()));
                    }
                    if (banner.getBattr() != null) {
                        logger.info("Creating Search Criteria with imp.banner.battr : " + banner.getBattr());
                        criteriaList.add(Criteria.where("imp.banner.battr").is(banner.getBattr()));
                    }
                    if (banner.getBtype() != null) {
                        logger.info("Creating Search Criteria with imp.banner.btype : " + banner.getBtype());
                        criteriaList.add(Criteria.where("imp.banner.btype").is(banner.getBtype()));
                    }
                    if (banner.getApi() != null) {
                        logger.info("Creating Search Criteria with imp.banner.api : " + banner.getApi());
                        criteriaList.add(Criteria.where("imp.banner.api").is(banner.getApi()));
                    }
                } //Banner Search Ends Here
            }
        }  //Imp search Ends Here


        // App Search Start Here
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
            if(app.getCat() != null) {
                logger.info("Creating Search Criteria with app.cat : " + app.getCat());
                criteriaList.add(Criteria.where("app.cat").is(app.getCat()));
            }
            if(app.getVer() != null) {
                logger.info("Creating Search Criteria with app.ver : " + app.getVer());
                criteriaList.add(Criteria.where("app.ver").is(app.getVer()));
            }
            if (app.getBundle() != null) {
                logger.info("Creating Search Criteria with app.bundle : " + app.getBundle());
                criteriaList.add(Criteria.where("app.bundle").is(app.getBundle()));
            }
            if (app.getStoreurl() != null) {
                logger.info("Creating Search Criteria with app.storeurl : " + app.getStoreurl());
                criteriaList.add(Criteria.where("app.storeurl").is(app.getStoreurl()));
            }

            //Publisher Search Starts Here
            if(app.getPublisher() != null) {
                AdRequest.Publisher publisher = app.getPublisher();
                logger.info("Creating Search Criteria with app.publisher : " + publisher);

                if(publisher.getId() != null) {
                    logger.info("Creating Search Criteria with app.publisher.id : " + publisher.getId());
                    criteriaList.add(Criteria.where("app.publisher.id").is(publisher.getId()));
                }
                if(publisher.getName() != null) {
                    logger.info("Creating Search Criteria with app.publisher.name : " + publisher.getName());
                    criteriaList.add(Criteria.where("app.publisher.name").is(publisher.getName()));
                }
                if (publisher.getDomain() != null) {
                    logger.info("Creating Search Criteria with app.publisher.domain : " + publisher.getDomain());
                    criteriaList.add(Criteria.where("app.publisher.domain").is(publisher.getDomain()));
                }

            }
            //Publisher Search Ends Here

        }
        // App Search Ends Here


        //Device Search Starts HERE
        if(adRequest.getDevice() != null) {
            AdRequest.Device device = adRequest.getDevice();
            if(device.getDnt() != null) {
                logger.info("Creating Search Criteria with device.dnt : " + device.getDnt());
                criteriaList.add(Criteria.where("device.dnt").is(device.getDnt()));
            }
            if(device.getUa() != null) {
                logger.info("Creating Search Criteria with device.ua : " + device.getUa());
                criteriaList.add(Criteria.where("device.ua").is(device.getUa()));
            }
            if (device.getIp() != null) {
                logger.info("Creating Search Criteria with device.ip : " + device.getIp());
                criteriaList.add(Criteria.where("device.ip").is(device.getIp()));
            }
            if (device.getDpidsha1() != null) {
                logger.info("Creating Search Criteria with device.dpidsha1 : " + device.getDpidsha1());
                criteriaList.add(Criteria.where("device.dpidsha1").is(device.getDpidsha1()));
            }
            if (device.getDpidmd5() != null) {
                logger.info("Creating Search Criteria with device.dpidmd5 : " + device.getDpidmd5());
                criteriaList.add(Criteria.where("device.dpidmd5").is(device.getDpidmd5()));
            }
            if (device.getCarrier() != null) {
                logger.info("Creating Search Criteria with device.carrier : " + device.getCarrier());
                criteriaList.add(Criteria.where("device.carrier").is(device.getCarrier()));
            }
            if (device.getLanguage() != null) {
                logger.info("Creating Search Criteria with device.language : " + device.getLanguage());
                criteriaList.add(Criteria.where("device.language").is(device.getLanguage()));
            }
            if(device.getMake() != null) {
                logger.info("Creating Search Criteria with device.make : " + device.getMake());
                criteriaList.add(Criteria.where("device.make").is(device.getMake()));
            }
            if (device.getModel() != null) {
                logger.info("Creating Search Criteria with device.model : " + device.getModel());
                criteriaList.add(Criteria.where("device.model").is(device.getModel()));
            }
            if (device.getOs() != null) {
                logger.info("Creating Search Criteria with device.os : " + device.getOs());
                criteriaList.add(Criteria.where("device.os").is(device.getOs()));
            }
            if (device.getOsv() != null) {
                logger.info("Creating Search Criteria with device.osv : " + device.getOsv());
                criteriaList.add(Criteria.where("device.osv").is(device.getOsv()));
            }
            if (device.getJs() != null) {
                logger.info("Creating Search Criteria with device.js : " + device.getJs());
                criteriaList.add(Criteria.where("device.js").is(device.getJs()));
            }
            if (device.getConnectiontype() != null) {
                logger.info("Creating Search Criteria with device.connectiontype : " + device.getConnectiontype());
                criteriaList.add(Criteria.where("device.connectiontype").is(device.getConnectiontype()));
            }
            if (device.getDevicetype() != null) {
                logger.info("Creating Search Criteria with device.devicetype : " + device.getDevicetype());
                criteriaList.add(Criteria.where("devicedevicetype").is(device.getDevicetype()));
            }

            //GEO Search Start Here
            if(device.getGeo() != null) {
                AdRequest.Geo geo = device.getGeo();
                if(geo.getCountry() != null) {
                    logger.info("Creating Search Criteria with device.geo.country : " + geo.getCountry());
                    criteriaList.add(Criteria.where("device.geo.country").is(geo.getCountry()));
                }
                if(geo.getLat() != null) {
                    logger.info("Creating Search Criteria with device.geo.lat : " + geo.getLat());
                    criteriaList.add(Criteria.where("device.geo.lat").is(geo.getLat()));
                }
                if(geo.getLon() != null) {
                    logger.info("Creating Search Criteria with device.geo.lon : " + geo.getLon());
                    criteriaList.add(Criteria.where("device.geo.lon").is(geo.getLon()));
                }
                if (geo.getCity() != null) {
                    logger.info("Creating Search Criteria with device.geo.city : " + geo.getCity());
                    criteriaList.add(Criteria.where("device.geo.city").is(geo.getCity()));
                }
                if (geo.getMetro() != null) {
                    logger.info("Creating Search Criteria with device.geo.metro : " + geo.getMetro());
                    criteriaList.add(Criteria.where("device.geo.metro").is(geo.getMetro()));
                }
                if (geo.getRegion() != null) {
                    logger.info("Creating Search Criteria with device.geo.region : " + geo.getRegion());
                    criteriaList.add(Criteria.where("device.geo.region").is(geo.getRegion()));
                }
                if (geo.getZip() != null) {
                    logger.info("Creating Search Criteria with device.geo.zip : " + geo.getZip());
                    criteriaList.add(Criteria.where("device.geo.zip").is(geo.getZip()));
                }
            }
            // GEO Search Ends Here
        }
        //Device Search Ends Here

        // User Search Start Here
        if(adRequest.getUser() != null) {
            AdRequest.User user = adRequest.getUser();
            if(user.getId() != null) {
                logger.info("Creating Search Criteria with user.id : " + user.getId());
                criteriaList.add(Criteria.where("user.id").is(user.getId()));
            }
            if (user.getYob() != null) {
                logger.info("Creating Search Criteria with user.yob : " + user.getYob());
                criteriaList.add(Criteria.where("user.yob").is(user.getYob()));
            }
            if (user.getGender() != null) {
                logger.info("Creating Search Criteria with user.gender : " + user.getGender());
                criteriaList.add(Criteria.where("user.gender").is(user.getGender()));
            }
        }

        // Combine all criteria
        Criteria combinedCriteria = new Criteria();
        if(!criteriaList.isEmpty()) {
            combinedCriteria = criteriaList.stream().reduce(Criteria::and).orElse(combinedCriteria);
            logger.info("Combined Criteria : " + combinedCriteria.toString());
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
