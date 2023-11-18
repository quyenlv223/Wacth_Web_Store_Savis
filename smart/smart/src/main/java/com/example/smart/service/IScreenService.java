package com.example.smart.service;


import com.example.smart.dto.request.attribute.screen.ScreenRequest;
import com.example.smart.dto.respone.attribute.screen.ScreenReposne;

import java.util.List;

public interface IScreenService {

     List<ScreenReposne> findAllScreen();

     String save(ScreenRequest request);

     String edit(ScreenRequest request);

     ScreenReposne findById(String id);

     String delete(Long id);
}
