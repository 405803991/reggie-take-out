package xjtu.demo1.dto;


import lombok.Data;
import xjtu.demo1.entity.OrderDetail;
import xjtu.demo1.entity.Orders;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
