package com.bidding.auction.bids;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bidding.auction.exception.DateExpiredException;
import com.bidding.auction.exception.FieldNotFoundException;
import com.bidding.auction.product.Product;
import com.bidding.auction.product.ProductRepository;
import com.bidding.auction.user.User;
import com.bidding.auction.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BidsController {
    @Autowired
    protected BidsRepository bidsRepository;
    //returns list of all bids
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ProductRepository productRepository;
    //get all bids
    @GetMapping(path="/get-all-bids")
    public List<Bid> findAllBids(){
        return bidsRepository.findAll();
    }
    //get specific bid
    @GetMapping(path="/find-bid-by-id/{id}")
    public Bid getOneBid(@PathVariable Integer id){
        Optional<Bid> oneBid=bidsRepository.findById(id);
         if(!oneBid.isPresent()){
             throw new FieldNotFoundException("not fount bid id="+id);
         }   
         return oneBid.get();
    }
    //create a new bid
    @PostMapping(path="/add-new-bid/{user_id}/product/{product_id}")
    public ResponseEntity<Object> place_bid(@Valid @PathVariable Integer user_id,@PathVariable Integer product_id ,@RequestBody Bid newbid){
       
        Optional<Product> product=productRepository.findById(product_id);
        Optional<User> user=userRepository.findById(user_id);
        if(!product.isPresent()){
            throw new FieldNotFoundException("product not found id="+product_id);
        }
        if(!user.isPresent()){
            throw new FieldNotFoundException("user not found id="+user_id);
        }
        if((product.get().getExpiry()).after(new Date())){
            throw new DateExpiredException("product expired");
        }
        newbid.setUser(user.get());
        newbid.setProduct(product.get());
        bidsRepository.save(newbid);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newbid.getBid_id()).toUri();
        return ResponseEntity.created(location).build();

    } 
    
    @GetMapping(path="/bid-by-product/{id}")
    public List<Bid> bidsByProductId(@PathVariable Integer id){
        Optional<Product> product=productRepository.findById(id);
        if(!product.isPresent()){
            throw new FieldNotFoundException("product not found id="+id);
        }
        if(product.isEmpty()){
            throw new FieldNotFoundException("bid not found for product id="+id);
        }
        return product.get().getBids();
    }
    //find bids user
    @GetMapping(path="/bid-by-user/{id}")
    public List<Bid> getBidsByUser(@PathVariable Integer id){
        Optional<User> userBids=userRepository.findById(id);
        if(!userBids.isPresent()){
            throw new FieldNotFoundException("user not found id-"+id);
        }
        return userBids.get().getBids();
    }
    //find bid by user and product
    @GetMapping(value="/bid-by-user/{id}/product/{product}")
    public List<Bid> bidByUserAndproduct(@PathVariable Integer id,@PathVariable Integer product){
        List<Bid> bids=new ArrayList<>();
        Optional<User> user=userRepository.findById(id);
        if(!user.isPresent()){
            throw new FieldNotFoundException("not found id="+id);
        }
        Optional<Product> oneproduct=productRepository.findById(product);
        if(!oneproduct.isPresent()){
            throw new FieldNotFoundException("product not found id-"+product);
        }
        List<Bid> bidsFromProduct=oneproduct.get().getBids();
            for(Bid onebid:bidsFromProduct){
                if(onebid.getProduct().getProductid()==product){
                bids.add(onebid);
                }
            }
            
        return bids;
    }
    @GetMapping(value="/get-winning-bid-by-product/{product_id}")
    public List<Bid> getMethodName(@PathVariable Integer product_id) {
        return bidsRepository.findWinningBid(product_id);
    }
    
}

