package com.nrlm.mclfmis.Services;


import com.nrlm.mclfmis.Entity.LocationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<String> getLocations(Long userId,String level);
}
