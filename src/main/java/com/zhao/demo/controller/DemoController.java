//package com.zhao.demo.controller;
//
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.search.SearchType;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//import org.elasticsearch.index.query.BoolQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.RangeQueryBuilder;
//import org.elasticsearch.search.SearchHit;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class DemoController {
//
//    @Autowired
//    private TransportClient client;
//
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/get/people/man")
//    @ResponseBody
//    public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
//        GetResponse result = this.client.prepareGet("people", "man", id).get();
//
//        if(!result.isExists()) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(result.getSource(), HttpStatus.OK);
//    }
//
//    @PostMapping("/add/people/man")
//    @ResponseBody
//    public ResponseEntity add(@RequestParam(name = "name") String name,
//                              @RequestParam(name = "conntry") String conntry,
//                              @RequestParam(name = "age") Integer age,
//                              @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
//        try {
//            XContentBuilder content = XContentFactory.jsonBuilder().startObject().field("name", name)
//                    .field("conntry", conntry)
//                    .field("age", age)
//                    .field("date", date.getTime())
//                    .endObject();
//            IndexResponse result = this.client.prepareIndex("people", "man").setSource(content).get();
//            return new ResponseEntity(result.getId(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/update/people/man")
//    @ResponseBody
//    public ResponseEntity update(@RequestParam(name = "id") String id,
//                                 @RequestParam(name = "name", required = false) String name,
//                                 @RequestParam(name = "conntry", required = false) String conntry,
//                                 @RequestParam(name = "age", required = false) Integer age,
//                                 @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
//        try {
//            XContentBuilder content = XContentFactory.jsonBuilder().startObject();
//            if(name != null) content.field("name", name);
//            if(conntry != null) content.field("conntry", conntry);
//            if(age != null) content.field("age", age);
//            if(date != null) content.field("date", date.getTime());
//            content.endObject();
//
//            UpdateRequest update = new UpdateRequest("people", "man", id);
//            update.doc(content);
//            UpdateResponse result = this.client.update(update).get();
//
//            // UpdateResponse result = this.client.prepareUpdate("people", "man", id).setDoc(content).get();
//            return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("query/people/man")
//    @ResponseBody
//    public ResponseEntity query(@RequestParam(name = "name", required = false) String name,
//                                @RequestParam(name = "conntry", required = false) String conntry,
//                                @RequestParam(name = "gt_age_count", required = false) Integer gtAgeCount,
//                                @RequestParam(name = "lt_age_count", required = false) Integer ltAgeCount){
//        BoolQueryBuilder boolQuery =  QueryBuilders.boolQuery();
//
//        if(name != null) {
//            boolQuery.must(QueryBuilders.matchQuery("name", name));
//        }
//
//        if(conntry != null) {
//            boolQuery.must(QueryBuilders.matchQuery("conntry", conntry));
//        }
//
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//        if(gtAgeCount != null) {
//            rangeQuery.from(gtAgeCount);
//        }
//        if(ltAgeCount != null) {
//            rangeQuery.to(ltAgeCount);
//        }
//
//        boolQuery.filter(rangeQuery);
//
//        SearchRequestBuilder builder = this.client.prepareSearch("people")
//                .setTypes("man")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(boolQuery)
//                .setFrom(0)
//                .setSize(10);
//
//        System.out.println(builder);
//
//        SearchResponse response = builder.get();
//        List<Map<String, Object>>  result = new ArrayList<>();
//        for (SearchHit hit : response.getHits()) {
//            result.add(hit.getSourceAsMap());
//        }
//
//        return new ResponseEntity(result, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/people/man")
//    @ResponseBody
//    public ResponseEntity delete(@RequestParam(name = "id") String id) {
//        DeleteResponse result = this.client.prepareDelete("people", "man", id).get();
//
//        return new ResponseEntity(result.getResult().toString(), HttpStatus.OK);
//    }
//}
