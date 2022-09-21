import com.pengrad.telegrambot.request.SetWebhook;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws IOException, TelegramApiException {
        System.getProperties().put("proxySet", true);
        System.getProperties().put("socksProxyHost", "127.0.0.1");
        System.getProperties().put("socksProxyPort", 9050);
        // Initialize Api Context
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        // Register our bot
        try {
            SimpleBot bot = new SimpleBot();
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

//        ipAddress(args[0]);
//        port(Integer.parseInt(args[1]));
//
//        get("/hello", (req, res) -> "Hello World");
//        post("/test", new Test());
//        get("/test", new Test());
//
//        get("/kurs", (request, response) -> new KursBot().getAllKurs());
//        post("/kursBot", new KursBot());
//
//        BotHandler botHandler = new AqivnBot();
//        post("/" + botHandler.getToken(), botHandler);
//
//        String appSite = System.getenv("OPENSHIFT_APP_DNS");
//        botHandler.getBot().execute(new SetWebhook().url(appSite + "/" + botHandler.getToken()));
    }

    private static class Test implements Route {
        @Override
        public Object handle(Request request, Response response) throws Exception {
            return "ok, test";
        }
    }
}
