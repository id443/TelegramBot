
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Example extends TelegramLongPollingBot {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botapi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botapi.registerBot(new Example());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Utils.getBotName();
    }

    @Override
    public String getBotToken() {
        return Utils.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        switch (txt) {
            case "/start":
                try {
                    sendMsg(msg, "Hello, world! This is simple bot!");
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            case "/Hi":
                try {
                    sendMsg(msg, "Nice to meet you!");
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            case "v":
                try {
                    sendMsg(msg, "Viva!");
                    break;
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
        }
    }

    private void sendMsg(Message msg, String text) throws TelegramApiException {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        execute(s);
    }
}
