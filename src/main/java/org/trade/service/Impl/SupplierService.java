package org.trade.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.SupplierMapper;
import org.trade.entity.Supplier;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierMapper supplierMapper;

    public List<Supplier> findAll() {

        return supplierMapper.findAll();
    }

    public int add(Supplier supier) {

        return supplierMapper.insert(supier);
    }

}
