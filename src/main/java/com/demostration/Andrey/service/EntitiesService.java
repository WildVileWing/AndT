package com.demostration.Andrey.service;

import com.demostration.Andrey.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntitiesService {

    public ClientRepo clientRepo;

    public ClientOrderRepo clientOrderRepo;

    public ProductRepo productRepo;

    public CategoryRepo categoryRepo;

    public OrderProductRepo orderProductRepo;

    public EntitiesService(ClientRepo clientRepo, ClientOrderRepo clientOrderRepo,ProductRepo productRepo, CategoryRepo categoryRepo, OrderProductRepo orderProductRepo){
        this.clientRepo = clientRepo;
        this.clientOrderRepo = clientOrderRepo;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.orderProductRepo = orderProductRepo;
    }

    public List <Client> getAllClients(){
        return clientRepo.findAll();
    }

    public Client getClientById(Long id){
        return clientRepo.findById(id).orElse(null);
    }

    public Client getClientByName(String name){
        Client client = new Client();
        client.setFullName(name);
        return clientRepo.findOne(Example.of(client)).orElse(null);
    }

    public List <ClientOrder> getAllOrder(){
        return clientOrderRepo.findAll();
    }

    public ClientOrder getOrderById(Long id){
        ClientOrder clientOrder = clientOrderRepo.findById(id).orElse(null);
        return clientOrder;
    }

    public List<ClientOrder> getOrderByStatus(Integer status){
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setStatus(status);
        return clientOrderRepo.findAll(Example.of(clientOrder));
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProductById(Long id){
        Product product = productRepo.findById(id).orElse(null);
        return product;
    }

    public Product getProductByName(String name){
        Product product = new Product();
        product.setName(name);
        return productRepo.findOne(Example.of(product)).orElse(null);
    }

    public List<Product> getCategoryProducts(Long id){
        return productRepo.getCategoryProducts(id);
    }

    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    public List<ClientOrder> getClientOrderByName(String name){
        return clientOrderRepo.getClientOrderByName(name);
    }

    public List<Product> getClientProducts(Long id) {
        return productRepo.getClientProducts(id);
    }

    public List<Product> getTopPopular(Integer top){
        return productRepo.getTopPopular(top).stream().limit(top).collect(Collectors.toList());
    }

}

