package vttp2023.batch4.paf.day23emart.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch4.paf.Utils;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.services.PurchaseOrderService;

@Controller
public class EmartController {

    @Autowired
    PurchaseOrderService poService;

    @GetMapping(path = "/")
    public String getLandingPage(HttpSession session, Model model){
        //List<PurchaseOrder> poList = poService.getAllPurchaseOrders();
        session.setAttribute(Utils.SESSION_PO_DATE, new Date());
        PurchaseOrder po = Utils.getPO(session);
        List<LineItem> lineItems = po.getLineItems();
        if(po.getLineItems()== null){
            lineItems = new ArrayList<>();
            po.setLineItems(lineItems);
        }
        session.setAttribute(Utils.SESSION_LINE_ITEMS, lineItems);
        model.addAttribute(Utils.SESSION_PURCHASE_ORDER,po);

        return "index";
    }

    @PostMapping(path = "/order")
    public String processOrder(@ModelAttribute(Utils.SESSION_PURCHASE_ORDER) PurchaseOrder po
    ,@RequestBody MultiValueMap<String,Object> mvm, HttpSession session){
        session.setAttribute(Utils.SESSION_PO_UPDATE, new Date());
        List<LineItem> lineItems = Utils.getLineItems(session);
        LineItem li = new LineItem();
        li.setItem(mvm.getFirst("item").toString());
        li.setQuantity(Integer.parseInt(mvm.getFirst("quantity").toString()));
        li.setId(lineItems.size());
        lineItems.add(li);
        po.setLineItems(lineItems);
        session.setAttribute(Utils.SESSION_PURCHASE_ORDER, po);
        return "index";
    }

    @PostMapping(path = "/checkout")
    public String checkOut(HttpSession session){

        return "notok";
    }
    
}
