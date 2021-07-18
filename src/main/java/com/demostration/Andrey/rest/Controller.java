package com.demostration.Andrey.rest;


import com.demostration.Andrey.entity.*;
import com.demostration.Andrey.service.EntitiesService;
import com.demostration.Andrey.service.TelegramService;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {


    public final EntitiesService entitiesService;

    private final TelegramService telegramService;

    public Controller(EntitiesService entitiesService, TelegramService telegramService){
        this.entitiesService = entitiesService;
        this.telegramService = telegramService;
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

    @RequestMapping(value = "/rest/getCategoriesByParent", params = {"parent"})
    public List<Category> getCategoriesByParent(Long parent){
        return entitiesService.getCategoriesByParent(parent);
    }

    @RequestMapping(value = "/rest/createNewClient", params = {"fullName, phoneNumber, address, externalId"})
    public Client createNewClient(String fullName, String phoneNumber, String address, Long externalId){
        return entitiesService.createNewClient(fullName, phoneNumber, address, externalId);
    }

    @RequestMapping(value = "/rest/getClientByExternalId", params = {"externalId"})
    public Client getClientByExternalId(Long externalId){
        return entitiesService.getClientByExternalId(externalId);
    }

    @RequestMapping(value = "/rest/createNewClientOrder", params = {"status, total, client"})
    public ClientOrder createNewClientOrder(Integer status, Double total, Client client){
        return entitiesService.createNewClientOrder(status, total, client);
    }

    @RequestMapping(value = "/rest/getClientOrderByClient", params = {"externalId"})
    public ClientOrder getClientOrderByClient(Long externalId){
        return entitiesService.getClientOrderByClient(externalId);
    }

    @RequestMapping(value = "/rest/getCategoryById", params = {"id"})
    public Category getCategoryById(Long id){
        return entitiesService.getCategoryById(id);
    }

    @RequestMapping(value = "/rest/getReplyKeyboardMarkupMenu", params = {"id"})
    public ReplyKeyboardMarkup getReplyKeyboardMarkupMenu(Long id){
        return telegramService.getReplyKeyboardMarkupMenu(id);
    }

    @RequestMapping(value = "/rest/getReplyKeyboardMarkup", params = {"id"})
    public ReplyKeyboardMarkup getReplyKeyboardMarkup(Long id){
        return telegramService.getReplyKeyboardMarkup(id);
    }

    @RequestMapping(value = "/rest/getInlineKeyboardMarkup", params = {"id"})
    public InlineKeyboardMarkup getInlineKeyboardMarkup(Long id){
        return telegramService.getInlineKeyboardMarkup(id);
    }

    @RequestMapping(value = "/rest/createNewOrderProduct", params = {"clientOrder, product, count"})
    public OrderProduct createNewOrderProduct(ClientOrder clientOrder, Product product, Integer count){
        return entitiesService.createNewOrderProduct(clientOrder, product, count);
    }

    @RequestMapping(value = "/rest/addPriceToClientOrder", params = {"clientOrder, price"})
    public ClientOrder addPriceToClientOrder(ClientOrder clientOrder, Double price){
        return entitiesService.addPriceToClientOrder(clientOrder, price);
    }

    @RequestMapping(value = "/rest/getOrderProductByClientOrderAndProduct", params = {"clientOrder, product"})
    public OrderProduct getOrderProductByClientOrderAndProduct(ClientOrder clientOrder, Product product){
        return entitiesService.getOrderProductByClientOrderAndProduct(clientOrder, product);
    }

    @RequestMapping(value = "/rest/getOrderProductsByClientOrder", params = {"clientOrder"})
    public List <OrderProduct> getOrderProductsByClientOrder(ClientOrder clientOrder){
        return entitiesService.getOrderProductsByClientOrder(clientOrder);
    }
}
