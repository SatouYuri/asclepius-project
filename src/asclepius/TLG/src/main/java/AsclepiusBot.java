import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AsclepiusBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText();
        String nome = update.getMessage().getFrom().getFirstName();

        if(text.equals("/start")) {
            sendText(update, "Olá, " + nome + "! Vamos iniciar a consulta?");
        }
            System.out.println(update.getMessage().getText());

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
