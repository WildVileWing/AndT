package com.demostration.Andrey.service;

import com.demostration.Andrey.entity.*;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramService {

    public EntitiesService entitiesService;

    public TelegramService(EntitiesService entitiesService){
        this.entitiesService = entitiesService;
    }

    public ReplyKeyboardMarkup getCategoryAndProductMarkup(Long id){
        List<KeyboardButton> categories = entitiesService.getCategoriesByParent(id)
                .stream()
                .map(category -> new KeyboardButton(category.getName())).collect(Collectors.toList());
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(categories.toArray(KeyboardButton[]::new));
        markup.resizeKeyboard(true);
        markup.addRow(new KeyboardButton("Оформить заказ"));
        markup.addRow(new KeyboardButton("В основное меню"));
        return markup;
    }

    public ReplyKeyboardMarkup getReplyKeyboardMarkupMenu(Long id){
        List<KeyboardButton> categories = entitiesService.getCategoriesByParent(id)
                .stream()
                .map(category -> new KeyboardButton(category.getName())).collect(Collectors.toList());

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup(categories.toArray(KeyboardButton[]::new));

        markup.resizeKeyboard(true);
        markup.addRow(new KeyboardButton("Оформить заказ"));
        return markup;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup(Long id) {

        List<Product> products = entitiesService.getCategoryProducts(id);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        for (Product product : products) {
            InlineKeyboardButton button = new InlineKeyboardButton(String.format("Товар %s. Цена %.2f руб.", product.getName(), product.getPrice()))
                    .callbackData("" + product.getId());
            markup.addRow(button);
        }
        return markup;
    }



}
