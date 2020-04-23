package com.iflytek.service;


import com.iflytek.domain.es.EsTag;
import com.iflytek.domain.es.MeMberTagEs;
import com.iflytek.mapper.es.EsMappingEtlMapper;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;

import com.google.common.collect.Lists;

import java.util.List;

@Service
public class EsQueryService {
    @Autowired
    private EsMappingEtlMapper esMappingEtlMapper;

    public List<MeMberTagEs> buildQuery(List<EsTag> tags) throws IOException {
        String[] includes = {"memberId", "phone","favGoods"};
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        builder.query(boolQueryBuilder);
        builder.from(0);
        builder.size(1000);
        builder.fetchSource(includes, null);
        List<QueryBuilder> should = boolQueryBuilder.should();
        List<QueryBuilder> mustNot = boolQueryBuilder.mustNot();
        List<QueryBuilder> must = boolQueryBuilder.must();
        for (EsTag tag : tags) {
            String name = tag.getName();
            String value = tag.getValue();
            String type = tag.getType();
            if (type.equals("match")) {
                should.add(QueryBuilders.matchQuery(name, value));
            }
            if (type.equals("notMatch")) {
                mustNot.add(QueryBuilders.matchQuery(name, value));
            }
            if (type.equals("rangeBoth")) {
                String[] split = value.split("-");
                String v1 = split[0];
                String v2 = split[1];
                should.add(QueryBuilders.rangeQuery(name).lte(v2).gte(v1));
            }
            if (type.equals("rangeGte")) {

                should.add(QueryBuilders.rangeQuery(name).gte(value));
            }

            if (type.equals("rangeLte")) {
                should.add(QueryBuilders.rangeQuery(name).lte(value));
            }

            if (type.equals("exists")) {
                should.add(QueryBuilders.existsQuery(name));
            }

            Iterable<MeMberTagEs> meMberTagEs = esMappingEtlMapper.search(boolQueryBuilder);
            List<MeMberTagEs> memberTags = Lists.newArrayList();
            meMberTagEs.forEach(meMberTagEs1 -> {
                memberTags.add(meMberTagEs1);
            });
        /*SearchRequest request = new SearchRequest();
        request.indices("tags");
        request.types("_doc");
        String[] includes = {"memberId", "phone"};
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        builder.query(boolQueryBuilder);
        builder.from(0);
        builder.size(1000);
        builder.fetchSource(includes, null);
        List<QueryBuilder> should = boolQueryBuilder.should();
        List<QueryBuilder> mustNot = boolQueryBuilder.mustNot();
        List<QueryBuilder> must = boolQueryBuilder.must();

        for (EsTag tag : tags) {
            System.out.println(tag.toString());

            String name = tag.getName();
            String value = tag.getValue();
            String type = tag.getType();
            if (type.equals("match")) {
                should.add(QueryBuilders.matchQuery(name, value));
            }

            if (type.equals("notMatch")) {
                mustNot.add(QueryBuilders.matchQuery(name, value));
            }

            if (type.equals("rangeBoth")) {
                String[] split = value.split("-");
                String v1 = split[0];
                String v2 = split[1];
                should.add(QueryBuilders.rangeQuery(name).lte(v2).gte(v1));
            }

            if (type.equals("rangeGte")) {

                should.add(QueryBuilders.rangeQuery(name).gte(value));
            }

            if (type.equals("rangeLte")) {
                should.add(QueryBuilders.rangeQuery(name).lte(value));
            }

            if (type.equals("exists")) {
                should.add(QueryBuilders.existsQuery(name));
            }


        }

        String s = boolQueryBuilder.toString();
        System.out.println("DSL:" + s);
        request.source(builder);
        //RequestOptions options = RequestOptions.DEFAULT;
        List<MeMberTagEs> memberTags = new ArrayList<>();

        try {
          System.out.println("highLevelClient============="+highLevelClient);
            SearchResponse search = highLevelClient.search(request);
            SearchHits hits = search.getHits();
            Iterator<SearchHit> iterator = hits.iterator();
            while (iterator.hasNext()) {
                SearchHit next = iterator.next();
                String sourceAsString = next.getSourceAsString();
                MeMberTagEs memberTag = JSON.parseObject(sourceAsString, MeMberTagEs.class);
                memberTags.add(memberTag);
            }
            return memberTags;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
            return memberTags;
        }

        return null;
    }

}
