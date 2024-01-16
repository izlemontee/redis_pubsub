package vttp2023.batch4.paf;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;

public class Utils {

    public static final String REDIS="redis";

    public static final String SESSION_LINE_ITEMS = "lineItems";
    public static final String SESSION_PURCHASE_ORDER = "po";
    public static final String SESSION_PO_DATE = "createdOn";
    public static final String SESSION_PO_UPDATE = "lastUpdate";

    public static List<LineItem> getLineItems(HttpSession session){
        Object itemsRaw = session.getAttribute(SESSION_LINE_ITEMS);
        if(itemsRaw == null){
            return new ArrayList<LineItem>();
        }
        else{
            return (List<LineItem>)itemsRaw;
        }

    }

    public static PurchaseOrder getPO(HttpSession session){
        Object poRaw = session.getAttribute(SESSION_PURCHASE_ORDER);
        if(poRaw == null){
            return new PurchaseOrder();
        }
        else{
            return(PurchaseOrder)poRaw;
        }
    }
    
}
