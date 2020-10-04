package com.arturoeanton.openscimv2.components;

import com.arturoeanton.openscimv2.exceptions.ScimExceptionBase;
import com.arturoeanton.openscimv2.filter.ScimFilterLexer;
import com.arturoeanton.openscimv2.filter.ScimFilterParser;
import com.arturoeanton.openscimv2.model.ListResponse;
import com.arturoeanton.openscimv2.model.Schema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Component
public class MongoRepository {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    Externalizer externalizer;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Schemas schemas;


    public void save(String resourceName, Map<String, Object> newElement) throws JsonProcessingException {
        String dataJson = mapper.writeValueAsString(newElement);
        var data = Document.parse(dataJson);
        mongoTemplate.getDb().getCollection(resourceName).insertOne(data);

    }

    public Map<String, Object> get(String resourceName, String id) throws ScimExceptionBase {
        var collection = mongoTemplate.getDb().getCollection(resourceName);
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        BasicDBObject projection = new BasicDBObject();
        projection.put("_id", false);
        var count = collection.countDocuments(query);
        if (count <= 0) {
            throw new ScimExceptionBase("Not found", HttpStatus.NOT_FOUND.value());
        }
        log.info("find Query:{}  ResultCount: ({})", query.toString(), count);
        return collection.find(query)
                .projection(projection)
                .into(new ArrayList<Map<String, Object>>()).get(0);
    }

    public void update(String resourceName, Map<String, Object> internal) throws ScimExceptionBase, JsonProcessingException {
        var collection = mongoTemplate.getDb().getCollection(resourceName);
        BasicDBObject query = new BasicDBObject();
        query.put("id", internal.get("id").toString());
        var count = collection.countDocuments(query);
        if (count <= 0) {
            throw new ScimExceptionBase("Not found", HttpStatus.NOT_FOUND.value());
        }
        String dataJson = mapper.writeValueAsString(internal);
        var data = Document.parse(dataJson);
        collection.replaceOne(query, data);
        log.info("ReplaceOne Query:{}  data: {}", query.toString(), data.toString());
    }

    public void delete(String resourceName, String id) throws ScimExceptionBase {
        var collection = mongoTemplate.getDb().getCollection(resourceName);
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        var count = collection.countDocuments(query);
        if (count <= 0) {
            throw new ScimExceptionBase("Not found", HttpStatus.NOT_FOUND.value());
        }
        collection.deleteOne(query);
        log.info("DeleteOne Query:{}", query.toString());
    }

    public ListResponse getByQuery(Schema schema, List<String> sortBy, String sortOrder, String filter, Integer startIndex, Integer count) {
        BasicDBObject query = new BasicDBObject();
        if (!"".equals(filter.trim())) {
            log.info(String.format("filter nameResource:%s filter:%s", schema.getName(), filter));
            var lexer = new ScimFilterLexer(CharStreams.fromString(filter));
            var tokens = new CommonTokenStream(lexer);
            var parser = new ScimFilterParser(tokens);
            var tree = parser.scimFilter();
            query = new Filter(schemas, schema).visit(tree);

        }

        var collection = mongoTemplate.getDb().getCollection(schema.getName());
        var urnBase = schema.getId();
        int sortOrderInt = ("descending".equals(sortOrder) ? -1 : 1);

        BasicDBObject projection = new BasicDBObject();
        projection.put("_id", false);
        BasicDBObject sort = new BasicDBObject();
        final Integer sortOrderIntFinal = sortOrderInt;
        sortBy.forEach(x -> sort.put(x, sortOrderIntFinal));
        log.info(String.format("find Query:%s projection:%s", query.toString(), projection.toString()));
        var find = collection.find(query).projection(projection).sort(sort);


        if (startIndex > 1) find = find.skip(startIndex - 1);
        if (count > 0) find = find.limit(count);


        var list = find.into(new ArrayList<Map<String, Object>>());
        var externalList = new ArrayList<Map<String, Object>>();
        list.forEach(x -> externalList.add(externalizer.external(urnBase, x)));
        Long total = collection.countDocuments(query);
        ListResponse listResponse = new ListResponse(externalList, total);
        if (startIndex > 0) listResponse.setStartIndex(startIndex);
        if (count > 0) listResponse.setItemsPerPage(count);
        return listResponse;
    }
}
