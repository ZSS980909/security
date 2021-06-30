package com.zss98.AuthorityResolving.Service;

import com.zss98.AuthorityResolving.Mapper.ResMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ResService {

    private ResMapper mapper;

    public ResService(ResMapper mapper){
        this.mapper = mapper;
    }

    public List getAll() {
        return mapper.getAll();
    }
}
