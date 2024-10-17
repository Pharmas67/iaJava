package de.hbrs.ia.code;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.bson.Document;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ManagePersonalImpl implements ManagePersonal {

    private MongoClient client = new MongoClient("localhost", 27017);

    // Get database 'highperformance' (creates one if not available)
    private MongoDatabase supermongo = client.getDatabase("highperformanceNewTest");

    // Get Collection 'salesmen' (creates one if not available)
    private MongoCollection<Document> salesmen = supermongo.getCollection("salesmen");

    private MongoCollection<Document> socalPerformanceRecords = supermongo.getCollection("socialPerformanceRecords");
    public ManagePersonalImpl() {}

    @Override
    public void createSalesMan(SalesMan record) {
        Document input = new Document();
        input = record.toDocument();
        salesmen.insertOne(input);
    }

    @Override
    public void delSalesMan(SalesMan record) {
        Document filter = new Document("sid", record.getId());
        salesmen.deleteOne(filter);
    }

    @Override
    public SalesMan readSalesMan(int sid) {
        Document query = new Document("sid",sid);
        Document foundSalesMan = salesmen.find(query).first();

        if(foundSalesMan != null){
            SalesMan person = new SalesMan("","",0);
            person.setFirstname(foundSalesMan.getString("firstname"));
            person.setLastname(foundSalesMan.getString("lastname"));
            person.setId(foundSalesMan.getInteger("sid"));

            BasicDBList arr = (BasicDBList) foundSalesMan.get("goalIds");
            for (Object obj: arr) {
                person.addGoalId((int) obj);
            }
            
            return person;
        }
        return null;
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        FindIterable<Document> foundSalesmen = salesmen.find();
        List<SalesMan> list = new ArrayList<>();
        for (Document document:foundSalesmen) {
            SalesMan person = new SalesMan("","",0);
            person.setFirstname(document.getString("firstname"));
            person.setLastname(document.getString("lastname"));
            person.setId(document.getInteger("sid"));
            BasicDBList arr = (BasicDBList) document.get("goalIds");
            for (Object obj: arr) {
                person.addGoalId((int) obj);
            }

            list.add(person);
        }
        return list;
    }

    @Override
    public void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        Document recordData = record.toDocument();
        socalPerformanceRecords.insertOne(recordData);

        Document query = new Document("sid",salesMan.getId());

        Bson update = Updates.push("goalIds", record.getGoalId());

        salesmen.updateOne(query,update);
    }

    @Override
    public void delSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        Document recordData = record.toDocument();
        socalPerformanceRecords.deleteOne(recordData);

        Document query = new Document("sid",salesMan.getId());

        Bson update = Updates.pull("goalIds",record.getGoalId());

        salesmen.updateOne(query,update);
    }

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        Document query = salesMan.toDocument();
        Document foundSalesMan = salesmen.find(query).first();

        List<SocialPerformanceRecord> list = new ArrayList<SocialPerformanceRecord>();

        BasicDBList goalIds = (BasicDBList) foundSalesMan.get("goalIds");
        for (Object obj: goalIds) {
            Document recordQuery = new Document("goalId", (String) obj);
            Document foundRecord = socalPerformanceRecords.find(recordQuery).first();

            SocialPerformanceRecord currRecord = new SocialPerformanceRecord("","",0,0,0);
            currRecord.setGoalId(foundRecord.getString("goalId"));
            currRecord.setGoalDescr(foundRecord.getString("goalDescr"));
            currRecord.setTargetVal(foundRecord.getInteger("targetValue"));
            currRecord.setActVal(foundRecord.getInteger("actValue"));
            currRecord.setYear(foundRecord.getInteger("year"));

            list.add(currRecord);
        }

        return list;
    }

    @Override
    public List<SocialPerformanceRecord> readAllSocialPerformanceRecord() {
        List<SocialPerformanceRecord> list = new ArrayList<SocialPerformanceRecord>();

        FindIterable<Document> foundRecords = socalPerformanceRecords.find();
        for (Document record: foundRecords) {

            SocialPerformanceRecord currRecord = new SocialPerformanceRecord("","",0,0,0);
            currRecord.setGoalId(record.getString("goalId"));
            currRecord.setGoalDescr(record.getString("goalDescr"));
            currRecord.setTargetVal(record.getInteger("targetValue"));
            currRecord.setActVal(record.getInteger("actValue"));
            currRecord.setYear(record.getInteger("year"));

            list.add(currRecord);
        }
        return list;
    }
}
