package vttp2023.batch4.paf.day23emart.repositories;

public class Queries {

    public static final String SQL_GET_ALL_PO_AND_LINE_ITEMS="""
            
        select po.po_id, po.created_on,po.name,po.address,po.last_update,li.id,li.item,li.quantity
        from purchase_order as po
        join line_item as li
        on po.po_id = li.po_id
        order by po.po_id
        ;
            """;

    public static final String SQL_GET_ALL_PO="""
            select * from po
            """;
    public static final String SQL_GET_ALL_LINE_ITEM="""
        select id,item,quantity
        from line_item
            where po_id=?
            ;
            """;

    public static final String SQL_GET_PO_ID="""
        select po_id
            from purchase_order
            where po_id = ?
            """;

    public static final String SQL_ADD_PO="""
        insert into purchase_order (po_id, created_on, name, address, last_update)
        values(?,?,?,?,?)
            """;
    public static final String SQL_ADD_ITEM="""
            insert into line_item (item,quantity,po_id)
            values(?, ?, ?)
            """;
}
