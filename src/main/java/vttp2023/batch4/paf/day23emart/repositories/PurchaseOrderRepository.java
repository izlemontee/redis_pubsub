package vttp2023.batch4.paf.day23emart.repositories;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    JdbcTemplate template;

    public Optional<SqlRowSet> getAllPurchaseOrders(){
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_ALL_PO);
        if(rs.next()){
            return Optional.of(rs);
        }
        else{
            return Optional.empty();
        }
    }

    public boolean idExists(String id){
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_PO_ID,id);
        if(rs.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean addPurchaseOrder(PurchaseOrder po){
        String id = po.getOrderId();
        String name = po.getName();
        String address = po.getAddress();
        Date createdOn = po.getCreatedOn();
        Date lastUpdated = po.getLastUpdate();
        return (template.update(Queries.SQL_ADD_PO,id, createdOn, name, address, lastUpdated) > 0);
    }
}
