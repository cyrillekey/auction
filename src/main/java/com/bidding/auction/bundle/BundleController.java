package com.bidding.auction.bundle;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.events.Event;
import com.bidding.auction.events.EventRepository;
import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.product.Product;
import com.bidding.auction.product.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BundleController {
    @Autowired
    private BundleRepository bundleRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ProductRepository productRepository;
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
    @PostMapping(path="/create-bundle/{event_id}/product/product_id")
    public ResponseEntity<Object> createNewBundle(@Valid @PathVariable Integer event_id,@PathVariable Integer product_id,@RequestBody Bundle bundle){
        Optional<Event> event=eventRepository.findById(event_id);
        if(!event.isPresent()){
            throw new FieldNotFoundException("event does not exist");
        }
        Optional<Product> productBelonging=productRepository.findById(product_id);
        if(!productBelonging.isPresent()){
            throw new FieldNotFoundException("product not found id-"+product_id);
        }
        bundle.setOneEvent(event.get());
       // bundle.setProducts(products);
        bundleRepository.save(bundle);


        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bundle.getBundleId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
