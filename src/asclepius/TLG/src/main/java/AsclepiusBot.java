import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

public class AsclepiusBot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText();

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

    }

    public String getBotUsername() {
        return "asclepius_bot";
    }

    public String getBotToken() {
        return "829161229:AAEyHYgvX4dLhEaqF6o9LgD86pgp4Xrpylo";
    }
}
