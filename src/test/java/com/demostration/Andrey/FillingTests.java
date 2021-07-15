package com.demostration.Andrey;

import com.demostration.Andrey.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FillingTests {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ClientOrderRepo clientOrderRepo;

    @Autowired
    private OrderProductRepo orderProductRepo;

    public Client createClient(String address, long externalId, String name, String phone){
        Client client = new Client();
        client.setAddress(address);
        client.setExternalId(externalId);
        client.setFullName(name);
        client.setPhoneNumber(phone);
        clientRepo.save(client);
        return client;
    }

    public Category createCategory(String name, Long parent){
        Category category = new Category();
        category.setName(name);
        category.setParent(parent);
        categoryRepo.save(category);
        return category;
    }

    public Product createProduct(String name, String description, double price,Category category){
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        productRepo.save(product);
        return product;
    }

    public ClientOrder createClientOrder(Integer status, double total, Client client){
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setStatus(status);
        clientOrder.setTotal(total);
        clientOrder.setClient(client);
        clientOrderRepo.save(clientOrder);
        return clientOrder;
    }

    public OrderProduct createOrderProduct(Integer countProduct, ClientOrder clientOrder, Product product){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setCountProduct(countProduct);
        orderProduct.setClientOrder(clientOrder);
        orderProduct.setProduct(product);
        orderProductRepo.save(orderProduct);
        return orderProduct;
    }


    @Test
    public void createDataTests(){

        //Клиенты

        Client client1 = createClient("2",2,"Andrey","+79780");
        Client client2 = createClient("3",3,"Ilya","+79780");
        Client client3 = createClient("4",4,"Roman","+79567");
        Client client4 = createClient("5",5,"Anton","+7879495");

        //Заказы

        ClientOrder clientOrder1 = createClientOrder(0,200.00, client1);
        ClientOrder clientOrder2 = createClientOrder(0,3000.00, client2);
        ClientOrder clientOrder3 = createClientOrder(1,1000,client3);
        ClientOrder clientOrder4 = createClientOrder(0,5000,client4);
        ClientOrder clientOrder5 = createClientOrder(2,5000.00, client1);
        ClientOrder clientOrder6 = createClientOrder(1,350.00, client2);
        ClientOrder clientOrder7 = createClientOrder(0,500,client3);
        ClientOrder clientOrder8 = createClientOrder(0,1000,client4);

        //Основные категории

        Category category1 = createCategory("Пицца",0L);

        Category category2 = createCategory("Роллы",0L);

        Category category3 = createCategory("Бургеры",0L);

        Category category4 = createCategory("Напитки",0L);

        //Пиццы

        Category category1c1 = createCategory("Мясные пиццы", category1.getId());

        Category category1c2 = createCategory("Сладкие пиццы", category1.getId());

        Category category1c3 = createCategory("Острые пиццы", category1.getId());

        //Роллы

        Category category2c1 = createCategory("Классические роллы", category2.getId());

        Category category2c2 = createCategory("Запеченные роллы", category2.getId());

        Category category2c3 = createCategory("Сладкие роллы", category2.getId());

        Category category2c4 = createCategory("Наборы", category2.getId());

        //Бургеры

        Category category3c1 = createCategory("Классические бургеры", category3.getId());

        Category category3c2 = createCategory("Острые бургеры", category3.getId());

        Category category3c3 = createCategory("Авторские бургеры", category3.getId());

        //Напитки

        Category category4c1 = createCategory("Газированные напитки", category4.getId());

        Category category4c2 = createCategory("Энергетические напитки", category4.getId());

        Category category4c3 = createCategory("Соки", category4.getId());

        Category category4c4 = createCategory("Другие", category4.getId());

        //Продукты

        //Мясные пиццы

        Product product1 = createProduct("Беконза","Томатный соус, моцарелла, чеддер, бекон, ветчина, шампиньоны, пармезан.",355,category1c1);

        Product product2 = createProduct("Цезарь","Соус рэнч, цыплёнок, бекон, моцарелла, пармезан, черри, айсберг, соус цезарь.",375,category1c1);

        Product product3 = createProduct("Супер мясная","Томатный соус, моцарелла, пепперони, баварские колбаски, подкопчённый цыпленок, бекон, ветчина.",395,category1c1);

        //Сладкие пиццы

        Product product4 = createProduct("M&M's","Сгущеное молоко, моцарелла, ананас, брусника, M&M's.",270,category1c2);

        Product product5 = createProduct("Бананба","Соус рэнч, цыплёнок, бекон, моцарелла, пармезан, черри, айсберг, соус цезарь.",310,category1c2);

        Product product6 = createProduct("Яблочко","Яблоки, карамельный соус",250,category1c2);


        //Острые пиццы

        Product product7 = createProduct("Мексиканская","Томатный соус, острый соус, моцарелла, пепперони, цыплёнок, шампиньоны, томаты, острый халапеньо, лук.",375,category1c3);

        Product product8 = createProduct("Диабло","Шампиньоны, салями Пепперони, острые перчики Халапеньо,сыр Моцарелла, орегано, базилик, петрушка, пицца-соус.",595,category1c3);

        createProduct("Тропикано","Куриная грудка, ананасы, острые перчики Халапеньо, сыр Моцарелла, орегано, базилик, пицца-соус.",545,category1c3);


        //Классические роллы

        createProduct("Филадельфия","Лосось, сыр Cremette, огурец.",360,category2c1);

        createProduct("Маки с лососем","Лосось",160,category2c1);

        createProduct("Черный дракон","Угорь, сыр Cremette, огурец, лосось, унаги соус, кунжут белый.",440,category2c1);

        //Запеченные роллы


        createProduct("Запеченная филадельфия","Лосось, сыр Cremette, огурец, сыр Чеддар, унаги соус, кунжут.",400,category2c2);

        createProduct("Запеченный с крабом","Краб, сыр чеддар, омлет томаго, помидор, соус сырный.",170,category2c2);

        createProduct("Запеченный с тунцом","Тунец , сыр чеддар, помидор, омлет томаго, соус сырный.",230,category2c2);

        //Сладкие роллы

        createProduct("Фруктовый микс","Сливочный блинчик,сливочный крем, груша, бананы, шоколадный соус.",150,category2c3);

        createProduct("Миндальный","Сливочный блинчик, сливочный крем, киви, лепестки миндаля, клубничный соус.",160,category2c3);

        createProduct("Бананово шоколадный","Шоколадный блинчик, сливочный крем, бананы, шоколад.",180,category2c3);

        //Наборы

        createProduct("Банкай","Снежный краб, Яцуми, Запеченный с мидиями, Ролл Вулкан, Гецуга с креветкой, Суба.",1190,category2c4);

        createProduct("Сатоши","ролл Снежный краб, ролл Яцуми, ролл Цезарь с лососем, ролл Вулкан, ролл Гецуга с креветкой, ролл Сайхон.",1690,category2c4);

        createProduct("Запеченный","Кайен, Хот лосось, Хот с крабом, Хот с креветкой, Джедай.",1440,category2c4);

        //Классические бургеры

        createProduct("Сальса","Сыр, лук, помидоры, салат, котлета из говядины, фирменный соус, огурцы, сальса.",250,category3c1);

        createProduct("Джуниор","Лук, сыр, маринованные огурцы, горчица, котлета из говядины.",130,category3c1);

        createProduct("Воппер","Лук, помидор, кетчуп,  сыр, маринованные огурцы, горчица, котлета из говядины.",150,category3c1);

        //Острые бургеры

        createProduct("Огонь","Перец чили, плавленный сыр, халапеньо, морковь, огурцы, лук, чеддер, котлета из говядины, соус чилли.",350,category3c2);

        createProduct("Мексиканец","Говядина, сыр моцарелла, сальса,салат, маринованные огурцы, чили соус, чеддер.",360,category3c2);

        createProduct("Спайси","Котлета из курицы, бекон, соус чили, азиатский соус, помидор, лук, салат.",200,category3c2);

        //Авторские бургеры

        createProduct("Креветка","Креветки в хрустящей панировке, соус Цезарь, салат айсберг, свежий огурец.",240,category3c3);

        createProduct("Терминатор","Котлеты из говядины, салат, лук, сыр чеддер, сыр моцарелла, соус сырный, соус Jack Daniels, бекон, луковые кольца, помидор, соус фирменный.", 890,category3c3);

        createProduct("Юджин","Котлета из говядины, салат ромэн, помидор, огурец соленый, сыр чеддер, лук, соус фирменный.",360,category3c3);

        //Газированные напитки
        createProduct("Pepsi","Pepsi 0.5л.",100,category4c1);

        createProduct("Coca-Cola в ж/б","Coca-Cola в ж/б 0.33л.",90,category4c1);

        createProduct("Coca-Cola","Coca-Cola 1л.",150,category4c1);

        //Энергетические напитки

        createProduct("Red Bull","Red Bull 0.33л.",100,category4c2);

        createProduct("Monster","Monster 0.5л.",150,category4c2);

        createProduct("Tornado","Tornado 1л.",120,category4c2);

        //Соки

        createProduct("Сок апельсиновый","Сок апельсиновый 0.95л.",140,category4c3);

        createProduct("Сок яблочный","Сок яблочный 0.95л.",140,category4c3);

        createProduct("Сок гранатовый","Сок гранатовый 0.95л.",150,category4c3);

        //Другие

        createProduct("Морс из клюквы","Морс из клюквы 0.5л.",70,category4c4);

        createProduct("Морс из черной смородины","Морс из черной смородины 0.5л.",70,category4c4);

        createProduct("Морс из вишни","Морс из вишни 0.5л.",70,category4c4);

        //Заказ-товар

        createOrderProduct(1,clientOrder1, product1);
        createOrderProduct(1,clientOrder2, product2);
        createOrderProduct(1,clientOrder3, product3);
        createOrderProduct(1,clientOrder4, product4);
        createOrderProduct(1,clientOrder5, product5);
        createOrderProduct(1,clientOrder6, product6);
        createOrderProduct(1,clientOrder7, product7);
        createOrderProduct(1,clientOrder8, product8);


    }
}
