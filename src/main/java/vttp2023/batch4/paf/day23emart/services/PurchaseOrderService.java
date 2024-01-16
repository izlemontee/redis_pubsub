package vttp2023.batch4.paf.day23emart.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch4.paf.Utils;
import vttp2023.batch4.paf.day23emart.models.LineItem;
import vttp2023.batch4.paf.day23emart.models.PurchaseOrder;
import vttp2023.batch4.paf.day23emart.repositories.LineItemRepository;
import vttp2023.batch4.paf.day23emart.repositories.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseRepo;

    @Autowired
    LineItemRepository lineItemRepo;

    @Autowired
    @Qualifier ("poPubSub") //correspond to the topic in messageconfiguration
    private ChannelTopic topic;

    @Autowired
    @Qualifier("registrationCache")
    private RedisTemplate<String,String> template;

    public void createPurchaseOrderManualTx(PurchaseOrder po){
        // convertAndSend takes in String and obj as args. this triggers the reading
        template.convertAndSend(topic.getTopic(), po.toString());
    }



    

}
