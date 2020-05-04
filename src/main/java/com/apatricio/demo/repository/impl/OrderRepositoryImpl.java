package com.apatricio.demo.repository.impl;

import com.apatricio.demo.model.mongodb.OrderEntity;
import com.apatricio.demo.model.Status;
import com.apatricio.demo.repository.OrderRepositoryCustom;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int findUpdateStatus(String id, Status taken, Status unassigned) {

        Query query = new Query(Criteria.where("status").is(unassigned));
        query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
        Update update = new Update();
        update.set("status", taken);

        UpdateResult result = mongoTemplate.updateFirst(query, update, OrderEntity.class);

        if(result!=null && result.getModifiedCount()!=0)
            return 1;
        else
            return 0;

    }
}
