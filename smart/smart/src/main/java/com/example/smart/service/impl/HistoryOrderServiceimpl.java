package com.example.smart.service.impl;

import com.example.smart.repo.HistoryOrderRepo;
import com.example.smart.service.IHistoryOrderService;
import org.springframework.stereotype.Service;

@Service
public class HistoryOrderServiceimpl implements IHistoryOrderService {

    private  HistoryOrderRepo dao;
}
