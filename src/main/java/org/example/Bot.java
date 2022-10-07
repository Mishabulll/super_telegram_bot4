package org.example;

import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "overone150_bot";
    }

    @Override
    public String getBotToken() {
        return "5361403125:AAFePkUOBFoSDfA7-n7cYka6lEzCDQzWBds";
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            List<City> list=new ArrayList<>();
            list.add(new City("Минск","53.893009","27.567444"));
            list.add(new City("Гродно","53.669353","23.813131"));
            list.add(new City("Витебск","55.187222","30.205116"));
            list.add(new City("Брест","52.097622","23.734051"));

            String lat = "", lon = "";
            for (City c:list){
                if (c.getName().equals(update.getMessage().getText())) {
            URL url=new URL("https://api.openweathermap.org/data/2.5/weather?lat="+c.getLat()+"&lon="+c.getLon()+"&appid=1a1cc72e62961bd8bd2d466bba40452c");
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(url.openStream()));
            String data= bufferedReader.readLine();
            JSONObject jsonObject=new JSONObject(data);
            String main =jsonObject.get("main").toString();
            jsonObject=new JSONObject(main);
            String temp=jsonObject.get("temp").toString();

            SendMessage sendMessage=new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText(temp);

            execute(sendMessage);
            }
            }

        } catch (Exception e) {
            e.printStackTrace();



//        SendMessage sendMessage = new SendMessage();
//        sendMessage.setChatId(update.getMessage().getChatId());
//        sendMessage.setText("Hello");
//        try {
//            execute(sendMessage);
//        } catch (Exception e) {
//            e.getMessage();
        }
    }
}