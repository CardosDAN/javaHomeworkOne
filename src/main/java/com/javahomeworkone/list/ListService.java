package com.javahomeworkone.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListService {

    @Autowired private ListRepository repo;

    public List<com.javahomeworkone.list.List> listAll(){
        return (List<com.javahomeworkone.list.List>) repo.findAll();
    }

    public void save(com.javahomeworkone.list.List list){
        repo.save(list);
    }

    public com.javahomeworkone.list.List get(Integer id) throws ListNotFoundException {
        Optional<com.javahomeworkone.list.List> result = repo.findById(id);
        if (result.isPresent()){
            return result.get();
        }
        throw new ListNotFoundException("Could not fond any list with the id: " + id);
    }

    public void delete(Integer id) throws ListNotFoundException{
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new ListNotFoundException("Could not fond any list with the id: " + id);
        }
        repo.deleteById(id);
    }

    public List<com.javahomeworkone.list.List> findByKeyword(String keyword){
        return repo.findByKeyword(keyword);
    }
}
