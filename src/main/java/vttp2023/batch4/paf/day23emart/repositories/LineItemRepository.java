package vttp2023.batch4.paf.day23emart.repositories;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;

@Repository
public class LineItemRepository {
    @Autowired
    JdbcTemplate template;
    public Optional<SqlRowSet> getAllLineItems(String id){
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_ALL_LINE_ITEM,id);
        if(rs.next()){
            return Optional.of(rs);
        }
        else{
            return Optional.empty();
        }

    }

    public boolean addLineItems(PurchaseOrder po){
        String id = po.getOrderId();
        List<LineItem> lineItems = po.getLineItems();
        int count = 0;
        for(LineItem li:lineItems){
            Integer itemId = li.getId();
            String name = li.getItem();
            Integer quantity = li.getQuantity();
            int increment = template.update(Queries.SQL_ADD_ITEM,name,quantity, id);
            count += increment;
        }
        return !(count == lineItems.size());
    }
}
