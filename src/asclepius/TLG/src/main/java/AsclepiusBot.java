import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AsclepiusBot extends TelegramLongPollingBot {

    private String[] dict = {"Sim", "sim", "Não", "não", "/start"};

    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        String nome = update.getMessage().getFrom().getFirstName();

        long chat_id = update.getMessage().getChatId();

        if(!inDict(text))
            sendText(update, "Me desculpe, mas não pude te compreender");

        else if(text.equals("/start")){
            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText("Vamos iniciar a consulta "+nome+"?" );
            // Create ReplyKeyboardMarkup object
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            row.add("Sim");
            // Add the first row to the keyboard
            keyboard.add(row);
            // Create another keyboard row
            row = new KeyboardRow();
            // Set each button for the second line
            row.add("Não");
            // Add the second row to the keyboard
            keyboard.add(row);
            // Set the keyboard to the markup
            keyboardMarkup.setKeyboard(keyboard);
            // Add it to the message
            message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

        System.out.println(text);
    }

    public boolean inDict(String text){
        for(int i = 0; i < dict.length; i++)
            if(text.equals(dict[i]))
                return true;
        return false;
    }

    public void sendText(Update update, String text){
        SendMessage message = new SendMessage();
        message.setText(text+"\n");
        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return;
    }


    public String getBotUsername() {
        return "asclepius_bot";
    }

    public String getBotToken() {
        File file = new File("src/main/resources/.token.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // we just need to use \n as delimiter
        sc.useDelimiter("\n");

        String token = sc.next();

        return token;
    }
}
