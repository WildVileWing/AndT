package com.demostration.Andrey.rest;


import com.demostration.Andrey.entity.Category;
import com.demostration.Andrey.entity.Client;
import com.demostration.Andrey.entity.ClientOrder;
import com.demostration.Andrey.entity.Product;
import com.demostration.Andrey.service.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {


    public final EntitiesService entitiesService;

    public Controller(EntitiesService entitiesService){
        this.entitiesService = entitiesService;
    }

    @RequestMapping(value = "/rest/getAllClients")
    public List<Client> getAllClients(){
        return entitiesService.getAllClients();
    }

    @RequestMapping(value = "/rest/getClientById", params = {"id"})
    public Client getClientById(@RequestParam Long id){
        return entitiesService.getClientById(id);
    }

    @RequestMapping(value = "/rest/getClientByName", params = {"name"})
    public Client getClientByName(@RequestParam String name){
        return entitiesService.getClientByName(name);
    }

    @RequestMapping(value = "/rest/getAllOrder")
    public List <ClientOrder> getAllOrder(){
        return entitiesService.getAllOrder();
    }

    @RequestMapping(value = "/rest/getOrderById", params = {"id"})
    public ClientOrder getOrderById(@RequestParam Long id){
        return entitiesService.getOrderById(id);
    }

    @RequestMapping(value = "/rest/getOrderByStatus", params = {"status"})
    public List<ClientOrder> getOrderByStatus(@RequestParam Integer status){
        return entitiesService.getOrderByStatus(status);
    }

    @RequestMapping(value = "/rest/getAllProducts")
    public List <Product> getAllProducts(){
        return entitiesService.getAllProducts();
    }

    @RequestMapping(value = "/rest/getProductById", params = {"id"})
    public Product getProductById(Long id){
        return entitiesService.getProductById(id);
    }

    @RequestMapping(value = "/rest/getProductByName", params = {"name"})
    public Product getProductByName(String name){
        return entitiesService.getProductByName(name);
    }

    @RequestMapping(value = "/rest/getCategoryProducts", params = {"id"})
    public List<Product> getCategoryProducts(@RequestParam Long id){
        return entitiesService.getCategoryProducts(id);
    }

    @RequestMapping(value = "/rest/getAllCategories")
    public List <Category> getAllCategories(){
        return entitiesService.getAllCategories();
    }

    @RequestMapping(value = "/rest/getClientOrderByName", params = {"name"})
    public List<ClientOrder> getClientOrderByName(String name){
        return entitiesService.getClientOrderByName(name);
    }

    @RequestMapping(value = "/rest/getClientProducts", params = {"id"})
    public List<Product> getClientProducts(Long id){
        return entitiesService.getClientProducts(id);
    }

    @RequestMapping(value = "/rest/getTopPopular", params = {"top"})
    public List<Product> getTopPopular(Integer top){
        return entitiesService.getTopPopular(top);
    }






}
