package com.rtb.search.repository;

import com.rtb.search.entity.AdRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRequestRepository extends ElasticsearchRepository<AdRequest, String> {

}
