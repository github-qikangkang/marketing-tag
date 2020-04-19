package com.iflytek.mapper.es;


import com.iflytek.domain.es.MeMberTagEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface EsMappingEtlMapper extends ElasticsearchRepository<MeMberTagEs,Integer> {

}
