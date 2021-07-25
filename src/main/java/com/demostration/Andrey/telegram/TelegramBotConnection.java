package com.demostration.Andrey.telegram;


import com.demostration.Andrey.entity.*;
import com.demostration.Andrey.service.EntitiesService;
import com.demostration.Andrey.service.TelegramService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramBotConnection {

    private final TelegramService telegramService;
    private final EntitiesService entitiesService;
    private final ClientOrderRepo clientOrderRepo;
    private TelegramBot telegramBot;
    private Long chatId;

    public TelegramBotConnection(EntitiesService entitiesService, TelegramService telegramService, ClientOrderRepo clientOrderRepo){
        this.entitiesService = entitiesService;
        this.telegramService = telegramService;
        this.clientOrderRepo = clientOrderRepo;
    }

    private class TelegramBotUpdateListener implements UpdatesListener{


        @Override
        public int process(List<Update> updates){
            // обработка сообщений от бота
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;

        }


        Client newClient = null;
        ClientOrder clientOrder = null;
        Client client = null;

        private void processUpdate(Update update){
            if(update.callbackQuery() != null){
                if(clientOrder !=null) {
                    Product product = entitiesService.getProductById(Long.valueOf(update.callbackQuery().data()));
                    OrderProduct orderProduct = null;
                    //OrderProduct orderProduct = entitiesService.getOrderProductByClientOrderAndProduct(clientOrder, product);
                    if(orderProduct == null){
                        entitiesService.createNewOrderProduct(clientOrder, product, 1);
                    }
                    else{
                        orderProduct.setCountProduct(orderProduct.getCountProduct()+1);
                    }
                    entitiesService.setPriceToClientOrder(clientOrder, product.getPrice());
                    telegramBot.execute(new SendMessage(chatId,"В заказ добавлен продукт: " + product.getName()));
                    clientOrderRepo.save(clientOrder);
                    // логика по работе с callback
                }
            }

            else{
                chatId = update.message().chat().id();
                client = entitiesService.getClientByExternalId(update.message().chat().id());

                // логика по работе с сообщениями
                if(client == null) {

                    newClient = entitiesService.createNewClient(update.message().chat().firstName(), update.message().chat().username(),
                            "Тестовый адрес", update.message().chat().id());
                    clientOrder = entitiesService.createNewClientOrder(1, 0.0D, newClient);
                }
                if(this.clientOrder == null || clientOrder.getStatus() != 1){
                    clientOrder = entitiesService.createNewClientOrder(1, 0.0D, newClient);
                }

                switch(update.message().text()) {
                    case "Пицца":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Пицца").replyMarkup(telegramService.getCategoryAndProductMarkup(13L)));
                        break;
                    case "Роллы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Роллы").replyMarkup(telegramService.getCategoryAndProductMarkup(14L)));
                        break;
                    case "Бургеры":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Бургеры").replyMarkup(telegramService.getCategoryAndProductMarkup(15L)));
                        break;
                    case "Напитки":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Напитки").replyMarkup(telegramService.getCategoryAndProductMarkup(16L)));
                        break;

                    case "Мясные пиццы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Мясные пиццы").replyMarkup(telegramService.getInlineKeyboardMarkup(17L)));
                        break;
                    case "Сладкие пиццы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Сладкие пиццы").replyMarkup(telegramService.getInlineKeyboardMarkup(18L)));
                        break;
                    case "Острые пиццы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Острые пиццы").replyMarkup(telegramService.getInlineKeyboardMarkup(19L)));
                        break;

                    case "Классические роллы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Классические роллы").replyMarkup(telegramService.getInlineKeyboardMarkup(20L)));
                        break;
                    case "Запеченные роллы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Запченные роллы").replyMarkup(telegramService.getInlineKeyboardMarkup(21L)));
                        break;
                    case "Сладкие роллы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Сладкие роллы").replyMarkup(telegramService.getInlineKeyboardMarkup(22L)));
                        break;
                    case "Наборы":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Наборы").replyMarkup(telegramService.getInlineKeyboardMarkup(23L)));
                        break;

                    case "Классические бургеры":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Классические бургеры").replyMarkup(telegramService.getInlineKeyboardMarkup(24L)));
                        break;
                    case "Острые бургеры":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Острые бургеры").replyMarkup(telegramService.getInlineKeyboardMarkup(25L)));
                        break;
                    case "Авторские бургеры":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Авторские бургеры").replyMarkup(telegramService.getInlineKeyboardMarkup(26L)));
                        break;

                    case "Газированные напитки":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Газированные напитки").replyMarkup(telegramService.getInlineKeyboardMarkup(27L)));
                        break;
                    case "Энергетические напитки":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Энергетические напитки").replyMarkup(telegramService.getInlineKeyboardMarkup(28L)));
                        break;
                    case "Соки":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Соки").replyMarkup(telegramService.getInlineKeyboardMarkup(29L)));
                        break;
                    case "Другие":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Другие").replyMarkup(telegramService.getInlineKeyboardMarkup(30L)));
                        break;

                    case "Оформить заказ":
                        System.out.println("status  "  +clientOrder.getStatus());
                        if(clientOrder.getTotal() == 0.0D){
                            telegramBot.execute(new SendMessage(update.message().chat().id(), "Товары не выбраны"));
                        }
                        else{
                            System.out.println(clientOrder.getId());
                            System.out.println("entites ser " + entitiesService.getOrderProductsByClientOrder(clientOrder) );
                            List <OrderProduct> orderProducts = entitiesService.getOrderProductsByClientOrder(clientOrder);
                            System.out.println("op list " + orderProducts);
                            telegramBot.execute(new SendMessage(update.message().chat().id(), "Заказ: "));
                            for(OrderProduct orderProduct: orderProducts){
                                telegramBot.execute(new SendMessage(update.message().chat().id(), orderProduct.getProduct().getName() + ": "
                                        + orderProduct.getCountProduct() + " шт. * " + orderProduct.getProduct().getPrice()
                                        + " = " + orderProduct.getProduct().getPrice() * orderProduct.getCountProduct()));
                            }

                            telegramBot.execute(new SendMessage(update.message().chat().id(), "Итого " + clientOrder.getTotal() + " руб."));
                            telegramBot.execute(new SendMessage(update.message().chat().id(), "Заказ №" + clientOrder.getId() + " подтвержден. Курьер подъедет к адресу " +
                                    newClient.getAddress() + "."));
                            clientOrder.setStatus(2);
                            entitiesService.saveClientOrderStatus(clientOrder);
                        }

                    case "В основное меню":
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Меню").replyMarkup(telegramService.getReplyKeyboardMarkupMenu(0L)));
                        break;

                    default:
                        telegramBot.execute(new SendMessage(update.message().chat().id(), "Меню").replyMarkup(telegramService.getReplyKeyboardMarkupMenu(0L)));
                        break;
                }
            }
        }
    }

    @PostConstruct
    public void createConnection(){
        telegramBot = new TelegramBot("1946938749:AAGiX-ROH_ZgMeyKNK1ky0TyhsRjhNoXAj0");
        telegramBot.setUpdatesListener(new TelegramBotUpdateListener());
    }
}
