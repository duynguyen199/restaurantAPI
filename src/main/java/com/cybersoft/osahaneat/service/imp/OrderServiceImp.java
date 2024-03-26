package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
