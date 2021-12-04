package com.bidding.auction.bundle;

import java.util.List;
import java.util.Optional;

import com.bidding.auction.exception.FieldNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BundleController {
    @Autowired
    private BundleRepository bundleRepository;

    @GetMapping(path ="/get-all-bundles")
    public List<Bundle> getAllBundles(){
        return bundleRepository.findAll();
    }
    @GetMapping(path="/get-bundle-by-id/{id}")
    public Bundle getbundleById(@PathVariable Integer id){
        Optional<Bundle> oneBundle=bundleRepository.findById(id);
        if(!oneBundle.isPresent()){
            throw new FieldNotFoundException("bundle not found");

        }
        return oneBundle.get();
    }
}
