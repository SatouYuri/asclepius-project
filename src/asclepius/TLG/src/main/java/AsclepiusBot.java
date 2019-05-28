import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AsclepiusBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {


        /*
        String text = update.getMessage().getText();
        //System.out.println(text);

        String nome = update.getMessage().getFrom().getFirstName();
        SendMessage message = new SendMessage();
        message.setText(nome+": "+ text +"\n");
        message.setChatId(update.getMessage().getChatId());
        try {
            execute(message);
            //System.out.println(text);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        System.out.println(text);
         */

    }

    public String getBotUsername() {
        return "asclepius_bot";
    }

    public String getBotToken() {
        File file = new File("src/.token.txt");
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
